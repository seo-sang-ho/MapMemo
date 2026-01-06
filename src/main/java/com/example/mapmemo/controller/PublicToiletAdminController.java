package com.example.mapmemo.controller;

import com.example.mapmemo.service.PublicToiletService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/admin/toilets")
@RequiredArgsConstructor
public class PublicToiletAdminController {

    private final PublicToiletService excelService;

    @PostMapping(value = "/upload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void upload(@RequestParam("file") MultipartFile file) throws Exception{
        System.out.println("🔥 upload controller called");
        excelService.save(file);
    }
}
