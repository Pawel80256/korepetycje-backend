package com.example.korepetycjebackend.models;

import com.example.korepetycjebackend.dto.ParagraphDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Entity
public class Paragraph {
    @Id
    private UUID id;
    private String title;
    private String content;
    private Integer orderr;

    public Paragraph(ParagraphDto paragraphDto, Integer orderr){
        this.id = UUID.randomUUID();
        this.title = paragraphDto.getTitle();
        this.content = paragraphDto.getContent();
        this.orderr = orderr;
    }

//    public Paragraph(String title, String content, Integer orderr){
//        this.id = UUID.randomUUID();
//        this.title = title;
//        this.content = content;
//        this.orderr = orderr;
//    }
}
