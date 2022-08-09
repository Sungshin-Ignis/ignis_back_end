package com.example.demo.src.Law.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetLawRes {
    private int lawIdx;
    private Boolean isCriminal;
    private int paragraph;
    private String content;
    private Boolean isCorrect;
}
