package com.example.mapmemo.controller;

import com.example.mapmemo.entity.Memo;
import com.example.mapmemo.service.MemoService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MainController {

    private final MemoService memoService;

    public MainController(MemoService memoService) {
        this.memoService = memoService;
    }

    @GetMapping("/")
    public String index(Model model){
        List<Memo> memos = memoService.getAllMemos();
        model.addAttribute("memos",memos);
        return "index";
    }
}
