package com.example.demo.src.Score;


import com.example.demo.config.BaseException;
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

import static com.example.demo.config.BaseResponseStatus.DATABASE_ERROR;

@Repository
public class ScoreDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public GetScoreRes selectScore(int userIdx) {
        String checkResultExistQuery = "select sum(favorableEvidenceSC+hintSC+lawSC+impeachmentSC+questionSC) from Score where userIdx = ?";
        int checkScoreExistParams = userIdx;
        return this.jdbcTemplate.queryForObject(checkResultExistQuery,
                (rs,rowNum) -> new GetScoreRes(
                        rs.getInt("userIdx"),
                        rs.getInt("total")
                ),
                checkScoreExistParams);
    }

    public int updateFavorableEvidenceSC(int userIdx, int favorableEvidenceSC) {
        String updateFavorableEvidenceSCExistQuery = "update theJudgement_db.Score set favorableEvidenceSC = ? where userIdx = ?";
        Object []updateFavorableEvidenceSCParams = new Object[] {favorableEvidenceSC, userIdx};
        this.jdbcTemplate.update(updateFavorableEvidenceSCExistQuery, updateFavorableEvidenceSCParams);

        String lastInsertIdxQuery = "select last_insert_id()";
        return this.jdbcTemplate.queryForObject(lastInsertIdxQuery, int.class);
    }


    public int updateHintSC(int userIdx, int hintSC) {
        String updateHintSCExistQuery = "update theJudgement_db.Score set hintSC = ? where userIdx = ?";
        Object []updateHintSCParams = new Object[] {hintSC, userIdx};
        this.jdbcTemplate.update(updateHintSCExistQuery, updateHintSCParams);

        String lastInsertIdxQuery = "select last_insert_id()";
        return this.jdbcTemplate.queryForObject(lastInsertIdxQuery, int.class);
    }

    public int updateLawSC(int userIdx, int lawSC) {
        String updateLawSCExistQuery = "update theJudgement_db.Score set lawSC = ? where userIdx = ?";
        Object []updateLawSCParams = new Object[] {lawSC, userIdx};
        this.jdbcTemplate.update(updateLawSCExistQuery, updateLawSCParams);

        String lastInsertIdxQuery = "select last_insert_id()";
        return this.jdbcTemplate.queryForObject(lastInsertIdxQuery, int.class);
    }

    public int updateImpeachmentSC(int userIdx, int impeachmentSC) {
        String updateImpeachmentSCExistQuery = "update theJudgement_db.Score set impeachmentSC = ? where userIdx = ?";
        Object []updateImpeachmentSCParams = new Object[] {impeachmentSC, userIdx};
        this.jdbcTemplate.update(updateImpeachmentSCExistQuery, updateImpeachmentSCParams);

        String lastInsertIdxQuery = "select last_insert_id()";
        return this.jdbcTemplate.queryForObject(lastInsertIdxQuery, int.class);
    }

    public int updateQuestionSC(int userIdx, int questionSC) {
        String updateQuestionSCExistQuery = "update theJudgement_db.Score set questionSC = ? where userIdx = ?";
        Object []updateQuestionSCParams = new Object[] {questionSC, userIdx};
        this.jdbcTemplate.update(updateQuestionSCExistQuery, updateQuestionSCParams);

        String lastInsertIdxQuery = "select last_insert_id()";
        return this.jdbcTemplate.queryForObject(lastInsertIdxQuery, int.class);
    }
}
