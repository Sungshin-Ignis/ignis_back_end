package com.example.demo.src.Score.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetScoreRes {
    private int userIdx;
    private int total;
}
