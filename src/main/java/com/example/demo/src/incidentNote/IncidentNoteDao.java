package com.example.demo.src.incidentNote;


import com.example.demo.src.incidentNote.model.GetIncidentNoteRes;
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


    public GetIncidentNoteRes selectIncidentNote(int userIdx){
        String checkUserExistQuery = "select Evidence.evidenceIdx, evidenceName, evidenceImgUrl\n" +
                "from Evidence,IncidentNote\n" +
                "where Evidence.evidenceIdx = IncidentNote.evidenceIdx and IncidentNote.userIdx = ?\n" +
                "order by Evidence.evidenceIdx;";
        int checkUserExistParams = userIdx;
        return this.jdbcTemplate.queryForObject(checkUserExistQuery,
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

    public int createUser(PostUserReq postUserReq) {
        return 0;
    }

    public int modifyUserName(PatchUserReq patchUserReq) {
        return 0;
    }
}
