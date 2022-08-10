package com.example.demo.src.Hint;


import com.example.demo.config.BaseException;
import com.example.demo.src.Hint.model.GetHintRes;
import com.example.demo.src.Score.model.GetScoreRes;
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
public class HintProvider {

    private final HintDao hintDao;
    private final JwtService jwtService;


    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public HintProvider(HintDao hintDao, JwtService jwtService) {
        this.hintDao = hintDao;
        this.jwtService = jwtService;
    }

    public GetHintRes getHintByOptions(int evidenceIdx) throws BaseException{
        try {
            GetHintRes getHintRes = hintDao.selectHint(evidenceIdx);
            return  getHintRes;
        }
        catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

}
