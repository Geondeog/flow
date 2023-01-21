package com.flow.project.service;

import com.flow.project.entity.CheckFile;
import com.flow.project.entity.File;
import com.flow.project.repository.CheckFileRepository;
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

    @Autowired
    private CheckFileRepository checkFileRepository;

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

    public Optional<CheckFile> getCheck() {
        Optional<CheckFile> file = checkFileRepository.findCheck();
        if(!file.isPresent()){
            CheckFile checkFile = new CheckFile();
            checkFile.setFile("bat,cmd,com,cpl,exe,scr,js");
            checkFileRepository.save(checkFile);
            file = checkFileRepository.findCheck();
        }
        return file;
    }

    public Object updateCheck(Map param) {
        String newValue = (String)param.get("data");
        Optional<CheckFile> file = checkFileRepository.findCheck();
        CheckFile checkFile = file.get();
        String value = checkFile.getFile();
        String[] checkArr = value.split(",");
        if(!checkArr.equals(newValue.split(","))) {
            logger.info(newValue);
            checkFile.setFile(newValue);
            checkFileRepository.save(checkFile);
        }
        return checkFile;
    }
}
