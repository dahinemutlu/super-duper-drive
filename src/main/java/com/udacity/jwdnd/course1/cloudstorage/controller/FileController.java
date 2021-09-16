package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/file")
public class FileController {

    private FileService fileService;
    private UserService userService;

    public FileController(FileService fileService, UserService userService) {
        this.fileService = fileService;
        this.userService = userService;
    }

    @PostMapping("/upload")
    private String addFile (MultipartFile fileUpload, Authentication auth, RedirectAttributes ra) {

        if(fileUpload.isEmpty()){
            ra.addFlashAttribute("errorMessage", "No file chosen");
        }else {
            File newFile = new File();
            newFile.setUserId(userService.getUserId(auth.getName()));
            newFile.setFileName(fileUpload.getOriginalFilename());
            newFile.setContentType(fileUpload.getContentType());
            newFile.setFileSize(fileUpload.getSize()+"");

            try {
                newFile.setFileData(fileUpload.getBytes());
            } catch (IOException e) {
                ra.addFlashAttribute("errorMessage", "There was an error saving your file");
                e.printStackTrace();
            }
            fileService.addFile(newFile);
            ra.addFlashAttribute("successMessage", "The file has been saved successfully");
        }

        return "redirect:/home#nav-files";
    }

    @GetMapping("/download/{fileId}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable Integer fileId, HttpServletResponse response, HttpServletRequest request) {
        File file = fileService.getFileById(fileId);
        byte[] fileContents = file.getFileData();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.parseMediaType(file.getContentType()));
        String fileName = file.getFileName();
        httpHeaders.setContentDispositionFormData(fileName, fileName);
        httpHeaders.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        ResponseEntity<byte[]> serverResponse = new ResponseEntity<byte[]>(fileContents, httpHeaders, HttpStatus.OK);
        return serverResponse;
    }

    @GetMapping("/delete/{fileId}")
    public String deleteNote(@PathVariable Integer fileId, RedirectAttributes ra){
        ra.addFlashAttribute("successMessage", "The file has been deleted successfully");
        fileService.deleteFile(fileId);
        return "redirect:/home#nav-files";
    }
}
