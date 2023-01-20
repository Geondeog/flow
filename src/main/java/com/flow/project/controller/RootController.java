package com.flow.project.controller;

import com.flow.project.entity.File;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class RootController {

    @GetMapping("/")
    public String viewPage() {
        return "index.html";
    }

    @ResponseBody
    @RequestMapping("/getValue")
    public String getValue() {
        String value = "js,cmd,com,txt";
        return value;
    }

    @ResponseBody
    @RequestMapping("/uploadValue")
    public Map uploadValue(@RequestBody Map param) {
        System.out.println("path:"+param);
        return param;
    }
}
