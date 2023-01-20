package com.flow.project.service;

import com.flow.project.entity.File;
import com.flow.project.repository.FileRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FileService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private FileRepository fileRepository;

    public Optional<File> findFile(String ip){
        Optional<File> file = fileRepository.findTopByIpOrderByNoDesc(ip);
        return file;
    }

    public File uploadFile(File file){
        fileRepository.save(file);
        return file;
    }
}
