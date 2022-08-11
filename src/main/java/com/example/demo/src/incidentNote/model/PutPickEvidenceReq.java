package com.example.demo.src.incidentNote.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class PutPickEvidenceReq {
    private int userIdx;
    private List<Integer> evidenceIdx;
}
