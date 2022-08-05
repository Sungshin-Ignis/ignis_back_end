package com.example.demo.src.Hint.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetHintRes {
    private int userIdx;
    private String name;
    private String nickName;
    private String email;

}
