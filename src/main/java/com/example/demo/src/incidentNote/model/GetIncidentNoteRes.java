package com.example.demo.src.incidentNote.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetIncidentNoteRes {
    // 사진, 이름
    private int evidenceIdx;
    private String evidenceName;
    private String evidenceImgUrl;
}