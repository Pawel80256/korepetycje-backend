package com.example.korepetycjebackend.dto.request;

import com.example.korepetycjebackend.dto.ParagraphDto;
import lombok.Getter;

import java.util.List;


@Getter
public class AddToProfileInfoRequest {
    private List<ParagraphDto> paragraphs;
}
