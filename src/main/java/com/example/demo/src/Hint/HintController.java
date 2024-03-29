package com.example.demo.src.Hint;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.Hint.model.GetHintRes;
import com.example.demo.src.incidentNote.model.GetIncidentNoteRes;
import com.example.demo.src.user.UserProvider;
import com.example.demo.src.user.UserService;
import com.example.demo.src.user.model.*;
import com.example.demo.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.example.demo.config.BaseResponseStatus.POST_USERS_EMPTY_EMAIL;
import static com.example.demo.config.BaseResponseStatus.POST_USERS_INVALID_EMAIL;
import static com.example.demo.utils.ValidationRegex.isRegexEmail;

@RestController
@RequestMapping("/Hint")
public class HintController {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private final HintProvider hintProvider;
    @Autowired
    private final HintService hintService;
    @Autowired
    private final JwtService jwtService;




    public HintController(HintProvider hintProvider, HintService hintService, JwtService jwtService){
        this.hintProvider = hintProvider;
        this.hintService = hintService;
        this.jwtService = jwtService;
    }


    /**
     * 힌트 선택지 & 답 조회 API
     * [GET] /hint/options?evidenceIdx=
     *
     * @return BaseResponse<GetHintRes>
     */
    //Query String
    @ResponseBody
    @GetMapping("/options") // (GET) 127.0.0.1:9000/hint/options?evidenceIdx=
    public BaseResponse<GetHintRes> getHintOptions(@RequestParam int evidenceIdx) {
        try{
            GetHintRes getHintRes = hintProvider.getHintByOptions(evidenceIdx);
            return new BaseResponse<>(getHintRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }
}
