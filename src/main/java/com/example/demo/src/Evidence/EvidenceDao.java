package com.example.demo.src.Evidence;


import com.example.demo.src.Evidence.model.GetEvidenceRes;
import com.example.demo.src.user.model.GetUserRes;
import com.example.demo.src.user.model.PatchUserReq;
import com.example.demo.src.user.model.PostUserReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class EvidenceDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public GetEvidenceRes getHasHintByEvidenceIdx(int evidenceIdx){
        String getHasHintQuery = "select `hasHint` from `theJudgement_db`.`Evidence` where `evidenceIdx` = ?";
        int checkEvidenceExistParams = evidenceIdx;
        return this.jdbcTemplate.queryForObject(getHasHintQuery,
                (rs,rowNum) -> new GetEvidenceRes(
                        rs.getInt("evidenceIdx"),
                        rs.getBoolean("hasHint")
                ),
                checkEvidenceExistParams);
    }

    public int checkEvidenceExist(int evidenceIdx){
        String checkUserExistQuery = "select exists(select `evidenceIdx` from `theJudgement_db`.`Evidence` where `evidenceIdx` = ?)";
        int checkEvidenceExistParams = evidenceIdx;
        return this.jdbcTemplate.queryForObject(checkUserExistQuery,
                int.class,
                checkEvidenceExistParams);

    }

}
