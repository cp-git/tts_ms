package com.cpa.ttsms.service;

import org.springframework.web.multipart.MultipartFile;

public interface UploadFileService {

	boolean uploadFile(String basePath, String folderName, String fileName, MultipartFile file);
}
