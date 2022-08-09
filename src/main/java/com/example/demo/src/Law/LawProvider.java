package com.example.demo.src.Law;


import com.example.demo.config.BaseException;
import com.example.demo.src.Law.model.GetLawRes;
import com.example.demo.src.incidentNote.model.GetIncidentNoteRes;
import com.example.demo.src.user.UserDao;
import com.example.demo.src.user.model.GetUserRes;
import com.example.demo.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.example.demo.config.BaseResponseStatus.DATABASE_ERROR;

//Provider : Read의 비즈니스 로직 처리
@Service
public class LawProvider {

    private final LawDao lawDao;
    private final JwtService jwtService;


    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public LawProvider(LawDao lawDao, JwtService jwtService) {
        this.lawDao = lawDao;
        this.jwtService = jwtService;
    }


    public GetLawRes getLaws() throws BaseException {
        try{
            GetLawRes getLawRes = lawDao.selectLaws();
            return getLawRes;
        } catch (Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }
}
