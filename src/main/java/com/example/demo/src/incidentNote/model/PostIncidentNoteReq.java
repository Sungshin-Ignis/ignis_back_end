package com.example.demo.src.incidentNote.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PostIncidentNoteReq {
    // userIdx, evidenceIdx 리스트로
    private int userIdx;
    private int evidenceIdx;
}
