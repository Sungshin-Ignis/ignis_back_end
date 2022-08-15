package com.example.demo.src.Evidence;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.Evidence.model.GetEvidenceRes;
import com.example.demo.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.example.demo.config.BaseResponseStatus.POST_USERS_EMPTY_EMAIL;
import static com.example.demo.config.BaseResponseStatus.POST_USERS_INVALID_EMAIL;
import static com.example.demo.utils.ValidationRegex.isRegexEmail;

@RestController
@RequestMapping("/evidence")
public class EvidenceController {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private final EvidenceProvider evidenceProvider;
    @Autowired
    private final EvidenceService evidenceService;
    @Autowired
    private final JwtService jwtService;




    public EvidenceController(EvidenceProvider evidenceProvider, EvidenceService evidenceService, JwtService jwtService){
        this.evidenceProvider = evidenceProvider;
        this.evidenceService = evidenceService;
        this.jwtService = jwtService;
    }



    /**
     * 힌트 여부 조회 API
     * [GET] /evidence/hasHint/:evidenceIdx
     * @return BaseResponse<GetUserRes>
     */
    //Query String
    @ResponseBody
    @GetMapping("/hasHint/{evidenceIdx}") // (GET) 127.0.0.1:9000/evidence/hasHint/:evidenceIdx
    public BaseResponse<GetEvidenceRes> getEvidence(@PathVariable("evidenceIdx") int evidenceIdx) {
        try{
            GetEvidenceRes getEvidenceRes = evidenceProvider.getHasHintByEvidenceIdx(evidenceIdx);
            return new BaseResponse<>(getEvidenceRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

}
