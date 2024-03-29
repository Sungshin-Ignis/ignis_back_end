package com.example.demo.src.incidentNote;


import com.example.demo.config.BaseException;
import com.example.demo.src.Score.model.PatchImpeachmentSCReq;
import com.example.demo.src.incidentNote.model.*;
import com.example.demo.src.user.UserDao;
import com.example.demo.src.user.UserProvider;
import com.example.demo.src.user.model.PatchUserReq;
import com.example.demo.src.user.model.PostUserReq;
import com.example.demo.src.user.model.PostUserRes;
import com.example.demo.utils.JwtService;
import com.example.demo.utils.SHA256;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.demo.config.BaseResponseStatus.*;

// Service Create, Update, Delete 의 로직 처리
@Service
public class IncidentNoteService {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final IncidentNoteDao incidentNoteDao;
    private final IncidentNoteProvider incidentNoteProvider;
    private final JwtService jwtService;


    @Autowired
    public IncidentNoteService(IncidentNoteDao incidentNoteDao, IncidentNoteProvider incidentNoteProvider, JwtService jwtService) {
        this.incidentNoteDao = incidentNoteDao;
        this.incidentNoteProvider = incidentNoteProvider;
        this.jwtService = jwtService;

    }

    public PostIncidentNoteRes createIncidentNote(int userIdx, PostIncidentNoteReq postIncidentNoteReq) throws BaseException {
        try {
            int incidentNoteIdx = incidentNoteDao.insertIncidentNote(userIdx, postIncidentNoteReq.getEvidenceIdx());

            return new PostIncidentNoteRes(incidentNoteIdx);
        }
        catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public void getHint(int userIdx, PatchGetHintReq patchGetHintReq) throws BaseException  {
        try {
            int result = incidentNoteDao.updateGetHint(userIdx, patchGetHintReq.getEvidenceIdx(), patchGetHintReq.getGetHint());

        }
        catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public PutPickEvidenceRes pickEvidence(int userIdx, PutPickEvidenceReq putPickEvidenceReq) throws BaseException {
        try {
            int idx = incidentNoteDao.putPickEvidence(userIdx);
            for(int i = 0; i<putPickEvidenceReq.getEvidenceIdx().size(); i++){
                incidentNoteDao.pickEvidence(userIdx, putPickEvidenceReq.getEvidenceIdx().get(i));
            }
            return new PutPickEvidenceRes(idx);
        }
        catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }
}
