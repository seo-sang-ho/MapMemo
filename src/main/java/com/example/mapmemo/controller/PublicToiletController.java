package com.example.mapmemo.controller;

import com.example.mapmemo.entity.PublicToilet;
import com.example.mapmemo.service.PublicToiletService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/toilets")
@RequiredArgsConstructor
public class PublicToiletController {

    private final PublicToiletService toiletService;

    @GetMapping
    public List<PublicToilet> getAll(){
        return toiletService.findAll();
    }
}
