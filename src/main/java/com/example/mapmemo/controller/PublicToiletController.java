package com.example.mapmemo.controller;

import com.example.mapmemo.entity.PublicToilet;
import com.example.mapmemo.service.PublicToiletService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/toilets")
@RequiredArgsConstructor
public class PublicToiletController {

    private final PublicToiletService toiletService;

    // 공중화장실 보기 버튼용
    @GetMapping
    public List<PublicToilet> getAll(){
        return toiletService.findAll();
    }

    @GetMapping("/nearby")
    public List<PublicToilet> near(@RequestParam double lat, @RequestParam double lng, @RequestParam(defaultValue = "0.5") double radius) {
        return toiletService.findNearBy(lat, lng, radius);
    }

    @GetMapping("/in-bounds")
    public List<PublicToilet> findInBounds(@RequestParam double minLat,
                                           @RequestParam double maxLat,
                                           @RequestParam double minLng,
                                           @RequestParam double maxLng)
    {
    return toiletService.findInBounds(minLat,maxLat,minLng,maxLng);
    }

}
