package com.example.demo.src.Score;


import com.example.demo.config.BaseException;
import com.example.demo.src.user.UserDao;
import com.example.demo.src.user.model.GetUserRes;
import com.example.demo.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.example.demo.config.BaseResponseStatus.DATABASE_ERROR;

//Provider : Read의 비즈니스 로직 처리
@Service
public class ScoreProvider {

    private final ScoreDao scoreDao;
    private final JwtService jwtService;


    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public ScoreProvider(ScoreDao scoreDao, JwtService jwtService) {
        this.scoreDao = scoreDao;
        this.jwtService = jwtService;
    }




}
