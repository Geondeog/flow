package com.flow.project.service;

import com.flow.project.entity.CheckFile;
import com.flow.project.entity.UseFile;
import com.flow.project.repository.CheckFileRepository;
import com.flow.project.repository.FileRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class FileService {

    @Autowired
    private FileRepository fileRepository;

    @Autowired
    private CheckFileRepository checkFileRepository;

    public Optional<UseFile> findFile(Map param){
        Optional<UseFile> file = fileRepository.findTopByIpOrderByNoDesc((String)param.get("ip"));
        if(!file.isPresent()){
            UseFile newFile = new UseFile();
            newFile.setIp((String)param.get("ip"));
            fileRepository.save(newFile);
            file = fileRepository.findTopByIpOrderByNoDesc((String)param.get("ip"));
        }
        return file;
    }

    public UseFile uploadFile(Map param){
        UseFile file = new UseFile();
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
            checkFile.setPw("1234");
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
            checkFile.setFile(newValue);
            checkFileRepository.save(checkFile);
        }
        return checkFile;
    }
}
