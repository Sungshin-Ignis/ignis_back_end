package com.example.demo.src.Law;

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
@RequestMapping("/law")
public class LawController {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private final LawProvider lawProvider;
    @Autowired
    private final LawService lawService;
    @Autowired
    private final JwtService jwtService;




    public LawController(LawProvider lawProvider, LawService lawService, JwtService jwtService){
        this.lawProvider = lawProvider;
        this.lawService = lawService;
        this.jwtService = jwtService;
    }



}
