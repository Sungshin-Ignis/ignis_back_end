package com.example.demo.src.incidentNote;


import com.example.demo.src.Hint.model.GetHintRes;
import com.example.demo.src.incidentNote.model.GetIncidentNoteRes;
import com.example.demo.src.incidentNote.model.GetIncidentNoteTrialRes;
import com.example.demo.src.incidentNote.model.PutPickEvidenceReq;
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
        String checkUserExistQuery = "select IncidentNote.evidenceIdx, evidenceName, evidenceImgUrl \n" +
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
        String checkUserExistQuery = "select exists(select userIdx from User where userIdx = ?)";
        int checkUserExistParams = userIdx;
        return this.jdbcTemplate.queryForObject(checkUserExistQuery,
                int.class,
                checkUserExistParams);

    }


    public int insertIncidentNote(int userIdx, int evidenceIdx) {
        String insertPostQuery = "insert into IncidentNote(userIdx, evidenceIdx) values (?,?)";
        Object []insertPostParams = new Object[] {userIdx, evidenceIdx};
        this.jdbcTemplate.update(insertPostQuery,
                insertPostParams);
        String lastInsertIdxQuery = "select last_insert_id()";
        return this.jdbcTemplate.queryForObject(lastInsertIdxQuery, int.class);
    }

    public GetIncidentNoteTrialRes selectTrialLines(int userIdx, int evidenceIdx) {
        String selectTrialLinesQuery = "select\n" +
                "\tcase IncidentNote.getHint\n" +
                "\t\t\twhen 1\n" +
                "            then (select (if((select User.isLawyer from theJudgement_db.User where User.userIdx = ?)= 1,Hint.lawyerHintLines,Hint.attorneyHintLines)) from theJudgement_db.Hint where evidenceIdx =?)\n" +
                "            when 0\n" +
                "            then (select (if((select User.isLawyer from theJudgement_db.User where User.userIdx = ?)= 1,Evidence.lawyerLines,Evidence.attorneyLines)) from theJudgement_db.Evidence where evidenceIdx = ?)\n" +
                "\t\tEND AS \"lines\"\n" +
                "from theJudgement_db.IncidentNote\n" +
                "order by IncidentNote.evidenceIdx asc limit 1;";
        Object []selectTrialLinesParams = new Object[] {userIdx, evidenceIdx,userIdx, evidenceIdx};
        return this.jdbcTemplate.queryForObject(selectTrialLinesQuery,
                (rs,rowNum) -> new GetIncidentNoteTrialRes(
                        rs.getString("lines")
                ),
                selectTrialLinesParams);
    }

    public int updateGetHint(int userIdx, int evidenceIdx, int getHint) {
        String updateGetHintQuery = "update theJudgement_db.IncidentNote set getHint = ? where userIdx = ? and evidenceIdx = ?";
        Object []updateGetHintParams = new Object[] {getHint, userIdx, evidenceIdx};
        this.jdbcTemplate.update(updateGetHintQuery, updateGetHintParams);

        String lastInsertIdxQuery = "select last_insert_id()";
        return this.jdbcTemplate.queryForObject(lastInsertIdxQuery, int.class);
    }


    public int putPickEvidence(int userIdx) {
        String putPickEvidenceQuery = "DELETE FROM theJudgement_db.IncidentNote WHERE userIdx = ?";
        int putPickEvidenceParams = userIdx;
        this.jdbcTemplate.update(putPickEvidenceQuery, putPickEvidenceParams);

        String lastInsertIdxQuery = "select last_insert_id()";
        return this.jdbcTemplate.queryForObject(lastInsertIdxQuery, int.class);
    }

    public int pickEvidence(int userIdx,  Integer evidenceIdx) {
        String pickEvidenceQuery = "INSERT INTO theJudgement_db.IncidentNote(userIdx, evidenceIdx) VALUES (?,?)";
        Object []pickEvidenceParams = new Object[] {userIdx, evidenceIdx};
        this.jdbcTemplate.update(pickEvidenceQuery, pickEvidenceParams);

        String lastInsertIdxQuery = "select last_insert_id()";
        return this.jdbcTemplate.queryForObject(lastInsertIdxQuery, int.class);

    }
}
