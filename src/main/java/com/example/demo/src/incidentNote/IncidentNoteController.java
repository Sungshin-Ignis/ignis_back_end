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

import java.util.List;

import static com.example.demo.config.BaseResponseStatus.*;
import static com.example.demo.utils.ValidationRegex.isRegexEmail;

@RestController
@RequestMapping("/incidentNote")
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
    public BaseResponse<List<GetIncidentNoteRes>> getUsers(@RequestParam int userIdx) {
        try{
            int userIdxByJwt = jwtService.getUserIdx();
            if(userIdx != userIdxByJwt){
                return new BaseResponse<>(BaseResponseStatus.INVALID_USER_JWT);
            }
            List<GetIncidentNoteRes> getIncidentNoteRes = incidentNoteProvider.getIncidentNoteByIdx(userIdx, userIdx);
            return new BaseResponse<>(getIncidentNoteRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    /**
     * 사건 노트에 증거 저장 API
     * [POST] /incidentNote
     * @return BaseResponse<PostIncidentNoteRes>
     */
    // Body
    @ResponseBody
    @PostMapping("/evidence") // (POST) 127.0.0.1:9000/incidentNote/evidence?userIdx=
    public BaseResponse<PostIncidentNoteRes> createIncidentNote(@RequestBody PostIncidentNoteReq postIncidentNoteReq) {
        if (postIncidentNoteReq.getEvidenceIdx() > 12 && postIncidentNoteReq.getEvidenceIdx() < 0) {
            return new BaseResponse<>(BaseResponseStatus.POST_INCIDENTNOTE_NONEXISTS_EVIDENCE);
        }
        try{
            int userIdxByJwt = jwtService.getUserIdx();
            if(postIncidentNoteReq.getUserIdx() != userIdxByJwt){
                return new BaseResponse<>(BaseResponseStatus.INVALID_USER_JWT);
            }
            PostIncidentNoteRes postIncidentNoteRes = incidentNoteService.createIncidentNote(postIncidentNoteReq.getUserIdx(), postIncidentNoteReq);
            return new BaseResponse<>(postIncidentNoteRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }
    /**
     * 재판 대사 API
     * [GET] /incidentNote/trial?userIdx=
     *
     * @return BaseResponse<GetIncidentNoteTrialRes>
     */
    //Query String
    @ResponseBody
    @GetMapping("/trial") // (GET) 127.0.0.1:9000/incidentNote/trial?userIdx=&evidenceIdx=
    public BaseResponse<GetIncidentNoteTrialRes> getTrialLines(@RequestParam int userIdx, @RequestParam int evidenceIdx) {
        if (evidenceIdx >= 12 || evidenceIdx < 0) {
            return new BaseResponse<>(BaseResponseStatus.POST_INCIDENTNOTE_NONEXISTS_EVIDENCE);
        }
        try{
            int userIdxByJwt = jwtService.getUserIdx();
            if(userIdx != userIdxByJwt){
                return new BaseResponse<>(BaseResponseStatus.INVALID_USER_JWT);
            }
            GetIncidentNoteTrialRes getIncidentNoteTrialRes = incidentNoteProvider.getTrialLines(userIdx, evidenceIdx);
            return new BaseResponse<>(getIncidentNoteTrialRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    /**
     *  getHint 힌트 얻었는지 API
     * [PATCH] /IncidentNote/getHint
     *
     */
    //Query String
    @ResponseBody
    @PatchMapping("/getHint") // (PATCH) 127.0.0.1:9000/incidentNote/getHint
    public BaseResponse<String> getHint(@RequestBody PatchGetHintReq patchGetHintReq) {
        try{
            int userIdxByJwt = jwtService.getUserIdx();
            if(patchGetHintReq.getUserIdx() != userIdxByJwt){
                return new BaseResponse<>(BaseResponseStatus.INVALID_USER_JWT);
            }
            incidentNoteService.getHint(patchGetHintReq.getUserIdx(), patchGetHintReq);
            String result="getHint 힌트를 저장했습니다.";
            return new BaseResponse<>(result);
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
    @PutMapping("/evidence") // (POST) 127.0.0.1:9000/incidentNote/evidence?userIdx=
    public BaseResponse<PutPickEvidenceRes> PickEvidence(@RequestBody PutPickEvidenceReq putPickEvidenceReq) {
        try{
            int userIdxByJwt = jwtService.getUserIdx();
            if(putPickEvidenceReq.getUserIdx() != userIdxByJwt){
                return new BaseResponse<>(BaseResponseStatus.INVALID_USER_JWT);
            }
            PutPickEvidenceRes putPickEvidenceRes = incidentNoteService.pickEvidence(putPickEvidenceReq.getUserIdx(), putPickEvidenceReq);
            return new BaseResponse<>(putPickEvidenceRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }
}
