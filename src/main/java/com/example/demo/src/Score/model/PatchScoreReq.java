package com.example.demo.src.Score.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PatchScoreReq {
    private int userIdx;
    private int favorableEvidenceSC;
    private int hintSC;
    private int lawSC;
    private int impeachmentSC;
    private int questionSC;
}
