package com.flow.project.service;

import com.flow.project.entity.File;
import com.flow.project.repository.FileRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class FileService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private FileRepository fileRepository;

    public Optional<File> findFile(Map param){
        Optional<File> file = fileRepository.findTopByIpOrderByNoDesc((String)param.get("ip"));
        if(!file.isPresent()){
            File newFile = new File();
            newFile.setIp((String)param.get("ip"));
            fileRepository.save(newFile);
            file = fileRepository.findTopByIpOrderByNoDesc((String)param.get("ip"));
        }
        return file;
    }

    public File uploadFile(Map param){
        File file = new File();
        file.setNo((Integer)param.get("no"));
        file.setFile((String)param.get("file"));
        file.setIp((String)param.get("ip"));
        fileRepository.save(file);
        return file;
    }
}
