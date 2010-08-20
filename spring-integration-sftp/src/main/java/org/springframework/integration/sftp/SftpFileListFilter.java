/*
 * Copyright 2010 the original author or authors
 *
 *     Licensed under the Apache License, Version 2.0 (the "License");
 *     you may not use this file except in compliance with the License.
 *     You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 *     Unless required by applicable law or agreed to in writing, software
 *     distributed under the License is distributed on an "AS IS" BASIS,
 *     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *     See the License for the specific language governing permissions and
 *     limitations under the License.
 */
package org.springframework.integration.sftp;

import com.jcraft.jsch.ChannelSftp;

import java.util.List;

/**
 * Filters out all the {@link com.jcraft.jsch.ChannelSftp.LsEntry} taken in a scan of the remote mount
 * and returns the balance. These are then sync'd to the local directory.
 *
 * @author Josh Long
 */                 @Deprecated
public interface SftpFileListFilter {
    List<ChannelSftp.LsEntry> filterFiles (ChannelSftp.LsEntry [] files);
}
