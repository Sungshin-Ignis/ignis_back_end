package com.example.demo.src.Score;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.user.UserProvider;
import com.example.demo.src.user.UserService;
import com.example.demo.src.user.model.*;
import com.example.demo.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.example.demo.config.BaseResponseStatus.POST_USERS_EMPTY_EMAIL;
import static com.example.demo.config.BaseResponseStatus.POST_USERS_INVALID_EMAIL;
import static com.example.demo.utils.ValidationRegex.isRegexEmail;

@RestController
@RequestMapping("/score")
public class ScoreController {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private final ScoreProvider scoreProvider;
    @Autowired
    private final ScoreService scoreService;
    @Autowired
    private final JwtService jwtService;




    public ScoreController(ScoreProvider scoreProvider, ScoreService scoreService, JwtService jwtService){
        this.scoreProvider = scoreProvider;
        this.scoreService = scoreService;
        this.jwtService = jwtService;
    }




}
