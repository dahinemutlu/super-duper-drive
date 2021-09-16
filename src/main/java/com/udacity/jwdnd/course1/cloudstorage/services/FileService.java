package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class FileService {
    private FileMapper fileMapper;

    public FileService(FileMapper fileMapper) {
        this.fileMapper = fileMapper;
    }

    @PostConstruct
    public void postConstruct() {
        System.out.println("Creating FileService bean");
    }

    public List<File> getUserFiles (int userId) {
        return fileMapper.getUserFiles(userId);
    }

    public void addFile (File file) {
        fileMapper.addFile(file);
    }

    public void deleteFile (int fileId) {
        fileMapper.deleteFile(fileId);
    }

    public File getFileById (int fileId) {
        return fileMapper.getFileById(fileId);
    }
}
