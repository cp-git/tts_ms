package com.cpa.ttsms.controller;

import java.io.File;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cpa.ttsms.exception.CPException;
import com.cpa.ttsms.helper.ResponseHandler;
import com.cpa.ttsms.service.UploadFileService;

@CrossOrigin
@RestController
@RequestMapping("/ttsms")
public class UploadFileController {

    @Autowired
    private UploadFileService uploadFileService;

    // The ResourceBundle is used to retrieve localized messages.
    private ResourceBundle resourceBundle;

    // The logger is used for logging messages related to this class.
    private static Logger logger;

    // Inject the value of 'file.base-path' from application.yml file
    @Value("${file.base-path}")
    private String basePath;

    // Constructor
    UploadFileController() {
        // Initialize the ResourceBundle for error messages with the US locale
        resourceBundle = ResourceBundle.getBundle("ErrorMessage", Locale.US);
        
        // Initialize the logger for this class
        logger = Logger.getLogger(UploadFileController.class);
    }

    // Handling file upload
    @PostMapping("/upload")
    public ResponseEntity<Object> uploadFile(@RequestParam("filename") String fileName,
                                             @RequestParam("file") MultipartFile file,
                                             @RequestParam("folder") String folderName) throws CPException {
        try {
            boolean isFileCreated = false;

            // Call the uploadFileService to upload the file to the specified folder
            isFileCreated = uploadFileService.uploadFile(basePath, folderName, fileName, file);
            
            if (isFileCreated) {
                // Generate a success response if the file was uploaded successfully
                return ResponseHandler.generateResponse(HttpStatus.OK, "msg001");
            } else {
                // Generate an internal server error response if the file upload failed
                return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, "err001");
            }
        } catch (Exception ex) {
            // Log the error and throw a custom CPException with an error code and message.
            logger.error("Failed to upload " + ex.getMessage());
            throw new CPException("err001", resourceBundle.getString("err001"));
        }
    }

    // Handling file download
    @GetMapping("/downloadfile")
    public ResponseEntity<Resource> downloadFile(@RequestParam("foldername") String type,
                                                 @RequestParam("filename") String filename) {
        try {
            // Determine the subdirectory based on the type (employee, company, task)
            String uploadSubDir = determineUploadSubDir(type);

            // Resolve the complete file path using the specified type and filename
            Path filePath = Paths.get(basePath, uploadSubDir, filename);

            // Create a Resource from the file's URI
            Resource resource = new UrlResource(filePath.toUri());

            // Prepare the response with appropriate headers for file download
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                    .body(resource);
        } catch (MalformedURLException e) {
            // If the file's URL cannot be formed, respond with a not found status
            return ResponseEntity.notFound().build();
        }
    }

    // Get a list of files by type
    @GetMapping("/downloadfile/getfiles/{type}")
    public ResponseEntity<List<String>> getFilesByType(@PathVariable String type) {
        // Create a list to store file names
        List<String> fileNames = new ArrayList<>();

        // Determine the subdirectory based on the type (employee, company, task)
        String uploadSubDir = determineUploadSubDir(type);

        // Create a File object for the specified subdirectory
        File uploadDir = new File(basePath, uploadSubDir);

        // Check if the subdirectory exists and is a directory
        if (uploadDir.exists() && uploadDir.isDirectory()) {
            // List all files in the subdirectory
            File[] files = uploadDir.listFiles();

            // Iterate through the files and add file names to the list
            if (files != null) {
                for (File file : files) {
                    // Check if the current item is a regular file
                    if (file.isFile()) {
                        // Add the name of the file to the list
                        fileNames.add(file.getName());
                    }
                }
            }
        }

        // Respond with the list of file names as a ResponseEntity
        return ResponseEntity.ok(fileNames);
    }

    // Determine the upload subdirectory based on the upload type
    private String determineUploadSubDir(String uploadType) {
        switch (uploadType) {
            case "employee":
                return "employee";
            case "company":
                return "company";
            case "task":
                return "task";
            default:
                return uploadType;
        }
    }
}
