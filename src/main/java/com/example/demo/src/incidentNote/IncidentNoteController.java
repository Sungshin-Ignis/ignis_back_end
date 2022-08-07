package com.example.demo.src.incidentNote;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.incidentNote.IncidentNoteProvider;
import com.example.demo.src.incidentNote.IncidentNoteService;
import com.example.demo.src.incidentNote.model.*;
import com.example.demo.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.example.demo.config.BaseResponseStatus.POST_USERS_EMPTY_EMAIL;
import static com.example.demo.config.BaseResponseStatus.POST_USERS_INVALID_EMAIL;
import static com.example.demo.utils.ValidationRegex.isRegexEmail;

@RestController
@RequestMapping("/IncidentNote")
public class IncidentNoteController {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private final IncidentNoteProvider incidentNoteProvider;
    @Autowired
    private final IncidentNoteService incidentNoteService;
    @Autowired
    private final JwtService jwtService;




    public IncidentNoteController(IncidentNoteProvider incidentNoteProvider, IncidentNoteService incidentNoteService, JwtService jwtService){
        this.incidentNoteProvider = incidentNoteProvider;
        this.incidentNoteService = incidentNoteService;
        this.jwtService = jwtService;
    }



    /**
     * 사건노트 조회 API
     * [GET] /IncidentNote
     *
     * @return BaseResponse<GetUserRes>
     */
    //Query String
    @ResponseBody
    @GetMapping("/{userIdx}") // (GET) 127.0.0.1:9000/IncidentNote/:userIdx
    public BaseResponse<GetIncidentNoteRes> getUsers(@PathVariable("userIdx")int userIdx) {
        try{
            GetIncidentNoteRes getIncidentNoteRes = incidentNoteProvider.getIncidentNoteByIdx(userIdx, userIdx);
            return new BaseResponse<>(getIncidentNoteRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

}
