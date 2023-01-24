package com.flow.project.controller;

import com.flow.project.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    @RequestMapping("/getCheck")
    public ResponseEntity getCheck() {
        return new ResponseEntity<>(fileService.getCheck(), HttpStatus.OK);

    }

    @ResponseBody
    @RequestMapping("/updateCheck")
    public ResponseEntity updateCheck(@RequestBody Map param) {
        return new ResponseEntity<>(fileService.updateCheck(param), HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping("/getValue")
    public ResponseEntity getValue(@RequestBody Map param) {
        return new ResponseEntity<>(fileService.findFile(param), HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping("/uploadValue")
    public ResponseEntity uploadValue(@RequestBody Map param) {
        return new ResponseEntity<>(fileService.uploadFile(param), HttpStatus.OK);
    }

}
