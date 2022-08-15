package com.example.demo.src.incidentNote.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PatchGetHintReq {
    private int userIdx;
    private int evidenceIdx;
    private int getHint;
}


