package com.example.demo.src.incidentNote;


import com.example.demo.config.BaseException;
import com.example.demo.src.Hint.model.GetHintRes;
import com.example.demo.src.incidentNote.model.GetIncidentNoteRes;
import com.example.demo.src.incidentNote.IncidentNoteDao;
import com.example.demo.src.incidentNote.model.GetIncidentNoteTrialRes;
import com.example.demo.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.demo.config.BaseResponseStatus.DATABASE_ERROR;
import static com.example.demo.config.BaseResponseStatus.USERS_EMPTY_USER_ID;

//Provider : Read의 비즈니스 로직 처리
@Service
public class IncidentNoteProvider {

    private final IncidentNoteDao incidentNoteDao;
    private final JwtService jwtService;


    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public IncidentNoteProvider(IncidentNoteDao incidentNoteDao, JwtService jwtService) {
        this.incidentNoteDao = incidentNoteDao;
        this.jwtService = jwtService;
    }


    public List<GetIncidentNoteRes> getIncidentNoteByIdx(int userIdxByJwt, int userIdx) throws BaseException{
        if (checkUserExist(userIdx)==0) {
            throw new BaseException(USERS_EMPTY_USER_ID);
        }
        try{
            List<GetIncidentNoteRes> getIncidentNote = incidentNoteDao.selectIncidentNote(userIdx);
            return getIncidentNote;
        }
        catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    private int checkUserExist(int userIdx) throws BaseException {
        try{
            return incidentNoteDao.checkUserExist(userIdx);
        } catch (Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }


    public int checkEmail(String email) {
        return 0;
    }

    public GetIncidentNoteTrialRes getTrialLines(int userIdx, int evidenceIdx) throws BaseException {
        try {
            GetIncidentNoteTrialRes getIncidentNoteTrialRes = incidentNoteDao.selectTrialLines(userIdx, evidenceIdx);
            return  getIncidentNoteTrialRes;
        }
        catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

}
