package com.flow.project.service;

import com.flow.project.entity.SaveFile;
import com.flow.project.entity.UseFile;
import com.flow.project.repository.FileRepository;
import com.flow.project.repository.SaveFileRepository;
import org.apache.commons.io.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.*;


@Service
public class UploadService {

    @Autowired
    private FileRepository fileRepository;

    @Autowired
    private SaveFileRepository saveFileRepository;

    public void upload(UseFile param, List<MultipartFile> multipartFile, String realPath) {
        String value = param.getFile();
        try {
            if (multipartFile.size() > 0 && !multipartFile.get(0).getOriginalFilename().equals("")) {

                for (MultipartFile file : multipartFile) {
                    String fileRoot = realPath + "file/upload/";
                    String originalFileName = file.getOriginalFilename();
                    String extension = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
                    boolean isExist = isExist(value, extension);
                    if (isExist) {
                        new Exception("첨부 불가 확장자가 존재합니다.");
                        break;
                    }
                    String savedFileName = UUID.randomUUID() + extension;
                    String savedFilePath = fileRoot + savedFileName;

                    File targetFile = new File(savedFilePath);
                    try {
                        InputStream fileStream = file.getInputStream();
                        FileUtils.copyInputStreamToFile(fileStream, targetFile); //파일 저장

                        SaveFile saveFile = new SaveFile();
                        saveFile.setFile(param);
                        saveFile.setName(originalFileName);
                        saveFile.setSavedFileName(savedFileName);
                        saveFile.setSavedFilePath(savedFilePath);
                        saveFile.setIp(param.getIp());
                        saveFileRepository.save(saveFile);

                    } catch (Exception e) {
                        //파일삭제
                        FileUtils.deleteQuietly(targetFile);    //저장된 현재 파일 삭제
                        e.printStackTrace();
                        break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public List<SaveFile> getfile(UseFile param) {
       List<SaveFile> fileList = saveFileRepository.findAllByIp(param.getIp());
        Optional<UseFile> check = fileRepository.findTopByIpOrderByNoDesc(param.getIp());
        for (SaveFile file : fileList) {
            String value = check.get().getFile();
            String extension = file.getName().substring(file.getName().lastIndexOf(".") + 1);
            boolean isExist = isExist(value, extension);
            if (isExist) {
                File targetFile = new File(file.getSavedFilePath());
                targetFile.delete();
                fileList.removeIf((item) -> item.getUno() == file.getUno());
                saveFileRepository.deleteByUno(file.getUno());
            }
        }

        return fileList;
    }

    private boolean isExist(String check, String extension) {
        if (check != null) {
            String[] checkArr = check.split(",");
            return Arrays.stream(checkArr).anyMatch(extension::equals);
        }
        return false;
    }

    public void remove(SaveFile removeFile) {
        File targetFile = new File(removeFile.getSavedFilePath());
        targetFile.delete();
        saveFileRepository.deleteByUno(removeFile.getUno());
    }
}
