package com.example.demo.src.Score;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.config.BaseResponseStatus;
import com.example.demo.src.Score.model.GetScoreRes;
import com.example.demo.src.Score.model.PatchFavorableEvidenceSCReq;
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
     * 승패 조회 API
     * [GET] /score/result
     *
     * @return BaseResponse<GetUserRes>
     */
    //Query String
    @ResponseBody
    @GetMapping("/result") // (GET) 127.0.0.1:9000/score/result?userIdx=
    public BaseResponse<GetScoreRes> getScoreTotal(@RequestParam int userIdx) {
        try{
            GetScoreRes getScoreRes = scoreProvider.getScoreByTotal(userIdx);
            return new BaseResponse<>(getScoreRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }
    /**
     *  favorableEvidenceSC 증가 API
     * [PATCH] /score/favorableEvidenceSC
     *
     * @return BaseResponse<GetUserRes>
     */
    //Query String
    @ResponseBody
    @PatchMapping("/favorableEvidenceSC") // (PATCH) 127.0.0.1:9000/score/favorableEvidenceSC
    public BaseResponse<String> addFavorableEvidenceSC(@RequestBody PatchFavorableEvidenceSCReq patchFavorableEvidenceSCReq) {
        if(patchFavorableEvidenceSCReq.getFavorableEvidenceSC() < 0){
            return new BaseResponse<>(BaseResponseStatus.PACTH_SCORE_INVALID_SCORE);
        }
        try{
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
     * @return BaseResponse<GetUserRes>
     */


    /**
     *   lawSC 증가 API
     * [PATCH] /score/lawSC
     *
     * @return BaseResponse<GetUserRes>
     */

    /**
     *  impeachmentSC 증가 API
     * [PATCH] /score/impeachmentSC
     *
     * @return BaseResponse<GetUserRes>
     */

    /**
     *  questionSC 증가 API
     * [PATCH] /score/questionSC
     *
     * @return BaseResponse<GetUserRes>
     */
}
