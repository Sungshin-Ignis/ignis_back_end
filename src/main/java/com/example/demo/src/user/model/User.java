package com.example.demo.src.user.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class User {
    private int userIdx;
    private String id;
    private String pw;
    private String name;
    private String email;
    private boolean isLawyer;
}
