package com.example.demo.src.incidentNote.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetIncidentNoteTrialRes {
    private int evidenceIdx;
    private String evidenceName;
    private String lines;
}