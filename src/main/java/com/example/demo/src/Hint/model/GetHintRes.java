package com.example.demo.src.Hint.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetHintRes {
    private int hintIdx;
    private int evidenceIdx;
    private String option1;
    private String option2;
    private String option3;
    private String correctAnswer;
    private String attorneyHint;
    private String lawyerHint;
    private String attorneyHintLines;
    private String lawyerHintLines;
}
