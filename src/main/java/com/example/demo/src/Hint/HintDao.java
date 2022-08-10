package com.example.demo.src.Hint;


import com.example.demo.src.Hint.model.GetHintRes;
import com.example.demo.src.Score.model.GetScoreRes;
import com.example.demo.src.incidentNote.model.GetIncidentNoteRes;
import com.example.demo.src.user.model.GetUserRes;
import com.example.demo.src.user.model.PatchUserReq;
import com.example.demo.src.user.model.PostUserReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class HintDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public GetHintRes selectHint(int evidenceIdx) {
        String checkHintExistQuery = "select * from `theJudgement_db`.`Hint` where `evidenceIdx` = ?";
        int checkHintExistParams = evidenceIdx;
        return this.jdbcTemplate.queryForObject(checkHintExistQuery,
                (rs,rowNum) -> new GetHintRes(
                        rs.getInt("hintIdx"),
                        rs.getInt("evidenceIdx"),
                        rs.getString("option1"),
                        rs.getString("option2"),
                        rs.getString("option3"),
                        rs.getString("correctAnswer"),
                        rs.getString("attorneyHint"),
                        rs.getString("lawyerHint"),
                        rs.getString("attorneyHintLines"),
                        rs.getString("lawyerHintLines")
                ),
                checkHintExistParams);
    }
}

