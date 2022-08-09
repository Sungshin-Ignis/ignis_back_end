package com.example.demo.src.Score;


import com.example.demo.config.BaseException;
import com.example.demo.src.user.UserDao;
import com.example.demo.src.user.UserProvider;
import com.example.demo.src.user.model.PatchUserReq;
import com.example.demo.src.user.model.PostUserReq;
import com.example.demo.src.user.model.PostUserRes;
import com.example.demo.utils.JwtService;
import com.example.demo.utils.SHA256;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.example.demo.config.BaseResponseStatus.*;

// Service Create, Update, Delete 의 로직 처리
@Service
public class ScoreService {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final ScoreDao scoreDao;
    private final ScoreProvider scoreProvider;
    private final JwtService jwtService;


    @Autowired
    public ScoreService(ScoreDao scoreDao, ScoreProvider scoreProvider, JwtService jwtService) {
        this.scoreDao = scoreDao;
        this.scoreProvider = scoreProvider;
        this.jwtService = jwtService;

    }


}
