package com.example.mapmemo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemoUpdateDto {
    private String title;
    private String content;
    private Category category;
}
