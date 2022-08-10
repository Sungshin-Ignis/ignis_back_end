package com.example.demo.src.Score;


import com.example.demo.config.BaseException;
import com.example.demo.src.Score.model.PatchFavorableEvidenceSCRes;
import com.example.demo.src.Score.model.PatchScoreReq;
import com.example.demo.utils.JwtService;
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

    public void addFavorableEvidenceSC(int userIdx, int favorableEvidenceSC1, PatchScoreReq patchScoreReq) throws BaseException{
        try {
            int addFavorableEvidenceSC1 = scoreDao.updateFavorableEvidenceSC(favorableEvidenceSC1, patchScoreReq.getFavorableEvidenceSC());

        }
        catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }
}
