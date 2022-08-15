package com.example.demo.src.Score;


import com.example.demo.config.BaseException;
import com.example.demo.src.Score.model.*;
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

    public void addFavorableEvidenceSC(int userIdx, PatchFavorableEvidenceSCReq patchFavorableEvidenceSCReq) throws BaseException{
        if (checkUserExist(userIdx)==0) {
            throw new BaseException(USERS_EMPTY_USER_ID);
        }
        try {
            int result = scoreDao.updateFavorableEvidenceSC(userIdx, patchFavorableEvidenceSCReq.getFavorableEvidenceSC());
        }
        catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public void addHintSC(int userIdx, PatchHintSCReq patchHintSCReq) throws BaseException{
        if (checkUserExist(userIdx)==0) {
            throw new BaseException(USERS_EMPTY_USER_ID);
        }
        try {
            int result = scoreDao.updateHintSC(userIdx, patchHintSCReq.getHintSC());
        }
        catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public void addLawSC(int userIdx, PatchLawSCReq patchLawSCReq) throws BaseException {
        if (checkUserExist(userIdx)==0) {
            throw new BaseException(USERS_EMPTY_USER_ID);
        }
        try {
            int result = scoreDao.updateLawSC(userIdx, patchLawSCReq.getLawSC());
        }
        catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public void addImpeachmentSC(int userIdx, PatchImpeachmentSCReq patchImpeachmentSCReq) throws BaseException {
        if (checkUserExist(userIdx)==0) {
            throw new BaseException(USERS_EMPTY_USER_ID);
        }
        try {
            int result = scoreDao.updateImpeachmentSC(userIdx, patchImpeachmentSCReq.getImpeachmentSC());
        }
        catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public void addQuestionSC(int userIdx, PatchQuestionSCReq patchQuestionSCReq) throws BaseException {
        if (checkUserExist(userIdx)==0) {
            throw new BaseException(USERS_EMPTY_USER_ID);
        }
        try {
            int result = scoreDao.updateQuestionSC(userIdx, patchQuestionSCReq.getQuestionSC());
        }
        catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    private int checkUserExist(int userIdx) throws BaseException {
        try{
            return scoreDao.checkUserExist(userIdx);
        } catch (Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }
}
