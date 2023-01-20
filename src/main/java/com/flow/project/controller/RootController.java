package com.flow.project.controller;

import com.flow.project.entity.File;
import com.flow.project.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class RootController {

    @Autowired
    FileService fileService;

    @GetMapping("/")
    public String viewPage() {
        return "index.html";
    }

    @ResponseBody
    @RequestMapping("/getValue")
    public ResponseEntity getValue(@RequestBody Map param) {
        return new ResponseEntity<>(fileService.findFile((String)param.get("ip")),HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping("/uploadValue")
    public ResponseEntity uploadValue(@RequestBody Map param) {
        File file = new File();
        file.setFile((String)param.get("file"));
        file.setIp((String)param.get("ip"));
        return new ResponseEntity<>(fileService.uploadFile(file),HttpStatus.OK);
    }
}
