package com.flow.project.controller;

import com.flow.project.entity.SaveFile;
import com.flow.project.entity.UseFile;
import com.flow.project.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class UploadController {

    @Autowired
    UploadService uploadService;

    @ResponseBody
    @RequestMapping("/getFiles")
    public ResponseEntity getFiles(@RequestBody UseFile param) {
        Map resultMap = new HashMap<>();
        resultMap.put("files", uploadService.getfile(param));
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping("/fileUpload")
    public ResponseEntity fileUpload(@RequestPart(value = "attach_file", required = false) List<MultipartFile> multipartFile,
                                     @RequestPart("file") UseFile param,
                                     HttpServletRequest request) {
        String realPath = request.getSession().getServletContext().getRealPath("/");
        if (multipartFile != null) uploadService.upload(param, multipartFile, realPath);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping("/removeFile")
    public ResponseEntity removeFile(@RequestBody SaveFile param) {
        uploadService.remove(param);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
