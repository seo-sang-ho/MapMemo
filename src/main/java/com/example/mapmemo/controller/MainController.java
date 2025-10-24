package com.example.mapmemo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @Value("${kakao.api.key}")
    private String kakaoApikey;

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("kakaoApikey", kakaoApikey);
        return "index";
    }
}
