package com.example.demo.src.Law;


import com.example.demo.src.Law.model.GetLawRes;
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
public class LawDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public GetLawRes selectLaws() {
        String checkLawExistQuery = "SELECT * FROM theJudgement_db.Law;";
        return this.jdbcTemplate.queryForObject(checkLawExistQuery,
                (rs,rowNum) -> new GetLawRes(
                        rs.getInt("lawIdx"),
                        rs.getBoolean("isCriminal"),
                        rs.getInt("paragraph"),
                        rs.getString("content"),
                        rs.getBoolean("isCorrect")
                ));
    }
}
