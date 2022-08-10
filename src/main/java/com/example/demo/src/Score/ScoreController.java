package com.example.demo.src.Score;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.Score.model.GetScoreRes;
import com.example.demo.src.Score.model.PatchFavorableEvidenceSCRes;
import com.example.demo.src.Score.model.PatchScoreReq;
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
     *  favorableEvidenceSC 증가 api API
     * [PATCH] /score/favorableEvidenceSC
     *
     * @return BaseResponse<GetUserRes>
     */
    //Query String
    @ResponseBody
    @PatchMapping("/{favorableEvidenceSC1}") // (PATCH) 127.0.0.1:9000/score/favorableEvidenceSC
    public BaseResponse<String> addFavorableEvidenceSC(@PathVariable ("favorableEvidenceSC1") int favorableEvidenceSC1, @RequestBody PatchScoreReq patchScoreReq) {
        try{
            scoreService.addFavorableEvidenceSC(patchScoreReq.getUserIdx(), favorableEvidenceSC1, patchScoreReq);
            String result="favorableEvidenceSC 점수 증가";
            return new BaseResponse<>(result);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

}
