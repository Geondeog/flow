package com.flow.project.service;

import com.flow.project.entity.File;
import com.flow.project.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FileService {

    @Autowired
    private FileRepository fileRepository;

    public Optional<File> findFile(String ip){
        return fileRepository.findTopByIpOrderByUpdatedateDesc(ip);
    }

    public File uploadFile(File file){
        fileRepository.save(file);
        return file;
    }
}
