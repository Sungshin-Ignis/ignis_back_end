package com.example.demo.src.incidentNote;


import com.example.demo.src.Hint.model.GetHintRes;
import com.example.demo.src.incidentNote.model.GetIncidentNoteRes;
import com.example.demo.src.incidentNote.model.GetIncidentNoteTrialRes;
import com.example.demo.src.user.model.PatchUserReq;
import com.example.demo.src.user.model.PostUserReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class IncidentNoteDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    public List<GetIncidentNoteRes> selectIncidentNote(int userIdx){
        String checkUserExistQuery = "select IncidentNote.evidenceIdx, evidenceName, evidenceImgUrl\n" +
                "from theJudgement_db.Evidence,theJudgement_db.IncidentNote\n" +
                "where Evidence.evidenceIdx = IncidentNote.evidenceIdx and IncidentNote.userIdx = ?\n" +
                "order by Evidence.evidenceIdx;";
        int checkUserExistParams = userIdx;
        return this.jdbcTemplate.query(checkUserExistQuery,
                (rs,rowNum) -> new GetIncidentNoteRes(
                        rs.getInt("evidenceIdx"),
                        rs.getString("evidenceName"),
                        rs.getString("evidenceImgUrl")
                ),
                checkUserExistParams);
    }

    public int checkUserExist(int userIdx){
        String checkUserExistQuery = "select exists(select userIdx from theJudgement_db.User where userIdx = ?)";
        int checkUserExistParams = userIdx;
        return this.jdbcTemplate.queryForObject(checkUserExistQuery,
                int.class,
                checkUserExistParams);

    }


    public int insertIncidentNote(int userIdx, int evidenceIdx) {
        String insertPostQuery = "insert into theJudgement_db.IncidentNote(userIdx, evidenceIdx) values (?,?)";
        Object []insertPostParams = new Object[] {userIdx, evidenceIdx};
        this.jdbcTemplate.update(insertPostQuery,
                insertPostParams);
        String lastInsertIdxQuery = "select last_insert_id()";
        return this.jdbcTemplate.queryForObject(lastInsertIdxQuery, int.class);
    }

    public GetIncidentNoteTrialRes selectTrialLines(int userIdx) {
        String selectTrialLinesQuery = "select case when";
        int selectTrialLinesParams = userIdx;
        return this.jdbcTemplate.queryForObject(selectTrialLinesQuery,
                (rs,rowNum) -> new GetIncidentNoteTrialRes(
                        rs.getInt("evidenceIdx"),
                        rs.getString("evidenceName"),
                        rs.getString("lines")
                ),
                selectTrialLinesParams);
    }

}
