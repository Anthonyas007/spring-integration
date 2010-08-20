/*
 * Copyright 2002-2008 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.integration.file;

import static org.mockito.Mockito.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.springframework.integration.file.filters.CompositeFileListFilter;
import org.springframework.integration.file.filters.FileListFilter;

/**
 * @author Iwein Fuld
 */
public class CompositeFileListFilterTests {

	private FileListFilter  fileFilterMock1 = mock( FileListFilter.class);

	private FileListFilter fileFilterMock2 = mock(FileListFilter.class);

	private File fileMock = mock(File.class);

	@Test
	public void forwardedToFilters() throws Exception {
		CompositeFileListFilter compositeFileFilter = new CompositeFileListFilter(fileFilterMock1, fileFilterMock2);
		List<File> returnedFiles = Arrays.asList(new File[] { fileMock });
		when(fileFilterMock1.filterFiles(isA(File[].class))).thenReturn(returnedFiles);
		when(fileFilterMock2.filterFiles(isA(File[].class))).thenReturn(returnedFiles);
		assertEquals(returnedFiles, compositeFileFilter.filterFiles(new File[]{fileMock}));
		verify(fileFilterMock1).filterFiles(isA(File[].class));
		verify(fileFilterMock2).filterFiles(isA(File[].class));
	}

	@Test
	public void forwardedToAddedFilters() throws Exception {
		CompositeFileListFilter compositeFileFilter = new CompositeFileListFilter();
        compositeFileFilter.addFilter(fileFilterMock1, fileFilterMock2);
		List<File> returnedFiles = Arrays.asList(new File[] { fileMock });
		when(fileFilterMock1.filterFiles(isA(File[].class))).thenReturn(returnedFiles);
		when(fileFilterMock2.filterFiles(isA(File[].class))).thenReturn(returnedFiles);
		assertEquals(returnedFiles, compositeFileFilter.filterFiles(new File[]{fileMock}));
		verify(fileFilterMock1).filterFiles(isA(File[].class));
		verify(fileFilterMock2).filterFiles(isA(File[].class));
	}

	@Test
	public void negative() throws Exception {
		CompositeFileListFilter compositeFileFilter = new CompositeFileListFilter(fileFilterMock1, fileFilterMock2);
		when(fileFilterMock2.filterFiles(isA(File[].class))).thenReturn(new ArrayList<File>());
		when(fileFilterMock1.filterFiles(isA(File[].class))).thenReturn(new ArrayList<File>());
		assertTrue(compositeFileFilter.filterFiles(new File[]{fileMock}).isEmpty());
		verify(fileFilterMock1).filterFiles(isA(File[].class));
		verify(fileFilterMock2).filterFiles(isA(File[].class));
	}
}
