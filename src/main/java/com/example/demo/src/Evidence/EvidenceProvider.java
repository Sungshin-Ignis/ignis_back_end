package com.example.demo.src.Evidence;


import com.example.demo.config.BaseException;
import com.example.demo.src.Evidence.model.GetEvidenceRes;
import com.example.demo.src.user.model.GetUserRes;
import com.example.demo.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.example.demo.config.BaseResponseStatus.DATABASE_ERROR;

//Provider : Read의 비즈니스 로직 처리
@Service
public class EvidenceProvider {

    private final EvidenceDao evidenceDao;
    private final JwtService jwtService;


    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public EvidenceProvider(EvidenceDao evidenceDao, JwtService jwtService) {
        this.evidenceDao = evidenceDao;
        this.jwtService = jwtService;
    }


    public GetEvidenceRes getHasHintByEvidenceIdx(int evidenceIdx) throws BaseException{
        try{
            GetEvidenceRes getEvidenceRes = evidenceDao.getHasHintByEvidenceIdx(evidenceIdx);
            return getEvidenceRes;
        }
        catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    private int checkUserExist(int evidenceIdx) throws BaseException {
        try{
            return evidenceDao.checkEvidenceExist(evidenceIdx);
        } catch (Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }
}
