package com.example.demo.src.incidentNote;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.config.BaseResponseStatus;
import com.example.demo.src.incidentNote.model.*;
import com.example.demo.src.user.model.PostUserReq;
import com.example.demo.src.user.model.PostUserRes;
import com.example.demo.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.example.demo.config.BaseResponseStatus.*;
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
    @GetMapping("") // (GET) 127.0.0.1:9000/IncidentNote?userIdx=
    public BaseResponse<GetIncidentNoteRes> getUsers(@RequestParam int userIdx) {
        try{
            GetIncidentNoteRes getIncidentNoteRes = incidentNoteProvider.getIncidentNoteByIdx(userIdx, userIdx);
            return new BaseResponse<>(getIncidentNoteRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    /**
     * 사건 노트에 증거 저장 API
     * [POST] /incidentNote/evidence
     * @return BaseResponse<PostIncidentNoteRes>
     */
    // Body
    @ResponseBody
    @PostMapping("/evidence") // (POST) 127.0.0.1:9000/incidentNote/evidence
    public BaseResponse<PostIncidentNoteRes> createIncidentNote(@RequestBody PostIncidentNoteReq postIncidentNoteReq) {
        if (postIncidentNoteReq.getEvidenceIdx() > 12 && postIncidentNoteReq.getEvidenceIdx() < 0) {
            return new BaseResponse<>(BaseResponseStatus.POST_INCIDENTNOTE_NONEXISTS_EVIDENCE);
        }
        try{
            PostIncidentNoteRes postIncidentNoteRes = incidentNoteService.createIncidentNote(postIncidentNoteReq.getUserIdx(), postIncidentNoteReq);
            return new BaseResponse<>(postIncidentNoteRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    /**
     * 증거 선택 API
     * [PUT] /incidentNote?userIdx=
     * @return BaseResponse<PostIncidentNoteRes>
     */
    // Body
    @ResponseBody
    @PutMapping("") // (POST) 127.0.0.1:9000/incidentNote/evidence?userIdx=
    public BaseResponse<PostIncidentNoteRes> PickIncidentNote(@RequestParam int userIdx, @RequestBody PostIncidentNoteReq postIncidentNoteReq) {
        if (postIncidentNoteReq.getEvidenceIdx() > 12 && postIncidentNoteReq.getEvidenceIdx() < 0) {
            return new BaseResponse<>(BaseResponseStatus.POST_INCIDENTNOTE_NONEXISTS_EVIDENCE);
        }
        try{
            PostIncidentNoteRes postIncidentNoteRes = incidentNoteService.createIncidentNote(postIncidentNoteReq.getUserIdx(), postIncidentNoteReq);
            return new BaseResponse<>(postIncidentNoteRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }
}
