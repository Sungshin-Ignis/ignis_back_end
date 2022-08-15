package com.example.demo.src.Score;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.config.BaseResponseStatus;
import com.example.demo.src.Score.model.*;
import com.example.demo.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    /**
     * totalSC 조회 API
     * [GET] /score/result
     *
     */
    //Query String
    @ResponseBody
    @GetMapping("/result/{userIdx}") // (GET) 127.0.0.1:9000/score/result/:userIdx
    public BaseResponse<GetScoreRes> getScoreTotal(@PathVariable("userIdx") int userIdx) {
        try{
            int userIdxByJwt = jwtService.getUserIdx();
            if(userIdx != userIdxByJwt){
                return new BaseResponse<>(BaseResponseStatus.INVALID_USER_JWT);
            }
            GetScoreRes getScoreRes = scoreProvider.getTotalByIdx(userIdx);
            return new BaseResponse<>(getScoreRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }
    /**
     *  favorableEvidenceSC 증가 API
     * [PATCH] /score/favorableEvidenceSC
     *
     */
    //Query String
    @ResponseBody
    @PatchMapping("/favorableEvidenceSC") // (PATCH) 127.0.0.1:9000/score/favorableEvidenceSC
    public BaseResponse<String> addFavorableEvidenceSC(@RequestBody PatchFavorableEvidenceSCReq patchFavorableEvidenceSCReq) {
        if(patchFavorableEvidenceSCReq.getFavorableEvidenceSC() < 0){
            return new BaseResponse<>(BaseResponseStatus.PACTH_SCORE_INVALID_SCORE);
        }
        try{
            int userIdxByJwt = jwtService.getUserIdx();
            if(patchFavorableEvidenceSCReq.getUserIdx() != userIdxByJwt){
                return new BaseResponse<>(BaseResponseStatus.INVALID_USER_JWT);
            }
            scoreService.addFavorableEvidenceSC(patchFavorableEvidenceSCReq.getUserIdx(), patchFavorableEvidenceSCReq);
            String result="favorableEvidenceSC 점수가 증가하였습니다";
            return new BaseResponse<>(result);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }
    /**
     *   hintSC 증가 API
     * [PATCH] /score/hintSC
     *
     */
    //Query String
    @ResponseBody
    @PatchMapping("/hintSC") // (PATCH) 127.0.0.1:9000/score/hintSC
    public BaseResponse<String> addHintSC(@RequestBody PatchHintSCReq patchHintSCReq) {
        if(patchHintSCReq.getHintSC() < 0){
            return new BaseResponse<>(BaseResponseStatus.PACTH_SCORE_INVALID_SCORE);
        }
        try{
            int userIdxByJwt = jwtService.getUserIdx();
            if(patchHintSCReq.getUserIdx() != userIdxByJwt){
                return new BaseResponse<>(BaseResponseStatus.INVALID_USER_JWT);
            }
            scoreService.addHintSC(patchHintSCReq.getUserIdx(), patchHintSCReq);
            String result="hintSC 점수가 증가하였습니다";
            return new BaseResponse<>(result);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    /**
     *   lawSC 증가 API
     * [PATCH] /score/lawSC
     *
     */
    //Query String
    @ResponseBody
    @PatchMapping("/lawSC") // (PATCH) 127.0.0.1:9000/score/lawSC
    public BaseResponse<String> addLawSC(@RequestBody PatchLawSCReq patchLawSCReq) {
        if(patchLawSCReq.getLawSC() < 0){
            return new BaseResponse<>(BaseResponseStatus.PACTH_SCORE_INVALID_SCORE);
        }
        try{
            int userIdxByJwt = jwtService.getUserIdx();
            if(patchLawSCReq.getUserIdx() != userIdxByJwt){
                return new BaseResponse<>(BaseResponseStatus.INVALID_USER_JWT);
            }
            scoreService.addLawSC(patchLawSCReq.getUserIdx(), patchLawSCReq);
            String result="lawSC 점수가 증가하였습니다";
            return new BaseResponse<>(result);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    /**
     *  impeachmentSC 증가 API
     * [PATCH] /score/impeachmentSC
     *
     */
    //Query String
    @ResponseBody
    @PatchMapping("/impeachmentSC") // (PATCH) 127.0.0.1:9000/score/impeachmentSC
    public BaseResponse<String> addImpeachmentSC(@RequestBody PatchImpeachmentSCReq patchImpeachmentSCReq) {
        if(patchImpeachmentSCReq.getImpeachmentSC() < 0){
            return new BaseResponse<>(BaseResponseStatus.PACTH_SCORE_INVALID_SCORE);
        }
        try{
            int userIdxByJwt = jwtService.getUserIdx();
            if(patchImpeachmentSCReq.getUserIdx() != userIdxByJwt){
                return new BaseResponse<>(BaseResponseStatus.INVALID_USER_JWT);
            }
            scoreService.addImpeachmentSC(patchImpeachmentSCReq.getUserIdx(), patchImpeachmentSCReq);
            String result="impeachmentSC 점수가 증가하였습니다";
            return new BaseResponse<>(result);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    /**
     *  questionSC 증가 API
     * [PATCH] /score/questionSC
     *
     */
    //Query String
    @ResponseBody
    @PatchMapping("/questionSC") // (PATCH) 127.0.0.1:9000/score/questionSC
    public BaseResponse<String> addQuestionSC(@RequestBody PatchQuestionSCReq patchQuestionSCReq) {
        if(patchQuestionSCReq.getQuestionSC() < 0){
            return new BaseResponse<>(BaseResponseStatus.PACTH_SCORE_INVALID_SCORE);
        }
        try{
            int userIdxByJwt = jwtService.getUserIdx();
            if(patchQuestionSCReq.getUserIdx() != userIdxByJwt){
                return new BaseResponse<>(BaseResponseStatus.INVALID_USER_JWT);
            }
            scoreService.addQuestionSC(patchQuestionSCReq.getUserIdx(), patchQuestionSCReq);
            String result="questionSC 점수가 증가하였습니다";
            return new BaseResponse<>(result);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }
}
