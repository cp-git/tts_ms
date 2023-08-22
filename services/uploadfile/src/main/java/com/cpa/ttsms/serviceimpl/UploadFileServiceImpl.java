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
		// TODO Auto-generated method stub
		try {
			String uploadSubDir = folderName;

			File uploadDir = new File(basePath, uploadSubDir);
			if (!uploadDir.exists()) {
				uploadDir.mkdirs();
			}

			Path filePath = Path.of(uploadDir.getAbsolutePath(), fileName);
			Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

			return true;
		} catch (IOException ex) {
			ex.printStackTrace();

		}
		return false;
	}

}
