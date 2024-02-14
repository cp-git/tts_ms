package com.cpa.ttsms.serviceimpl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cpa.ttsms.service.UploadFileService;

@Service
public class UploadFileServiceImpl implements UploadFileService {

	@Override
	public boolean uploadFile(String basePath, String folderName, String fileName, MultipartFile file) {
		try {
			// Define the subdirectory where the file will be uploaded
			String uploadSubDir = folderName;

			// Create a File object representing the directory where the file will be
			// uploaded
			File uploadDir = new File(basePath, uploadSubDir);

			// If the directory does not exist, create it
			if (!uploadDir.exists()) {
				uploadDir.mkdirs();
			}

			// Create a Path object representing the complete file path
			Path filePath = Path.of(uploadDir.getAbsolutePath(), fileName);

			// Copy the content of the uploaded file to the specified file path, replacing
			// if it already exists
			Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

			// Return true to indicate that the file was successfully uploaded
			return true;
		} catch (IOException ex) {
			// If an IOException occurs during the file upload, print the exception details
			ex.printStackTrace();
		}
		// Return false to indicate that the file upload was not successful
		return false;
	}
}
