package com.example.demo.src.user;


import com.example.demo.src.user.model.GetUserRes;
import com.example.demo.src.user.model.PatchUserReq;
import com.example.demo.src.user.model.PostUserReq;
import com.example.demo.src.user.model.PostUserRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class UserDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    public GetUserRes getUsersByIdx(int userIdx){
        String getUsersByIdxQuery = "select userIdx,name,isLawyer from theJudgement_db.User where userIdx=?";
        int getUsersByIdxParams = userIdx;
        return this.jdbcTemplate.queryForObject(getUsersByIdxQuery,
                (rs, rowNum) -> new GetUserRes(
                        rs.getInt("userIdx"),
                        rs.getString("name"),
                        rs.getBoolean("isLawyer")),
                getUsersByIdxParams);
    }

    // 유저 추가
    public int createUser(PostUserReq postUserReq){
        String createUserQuery = "insert into theJudgement_db.User (id, pw, name, email) VALUES (?,?,?,?)";
        Object[] createUserParams = new Object[]{postUserReq.getId(), postUserReq.getPw(),postUserReq.getName(), postUserReq.getEmail()};
        this.jdbcTemplate.update(createUserQuery, createUserParams);

        String lastInsertIdQuery = "select last_insert_id()";
        return this.jdbcTemplate.queryForObject(lastInsertIdQuery,int.class);
    }

    // 유저 추가 시 사건노트 기본값, score 행 삽입
    public void insertUser(int userIdx){
        String createUserQuery = "insert into theJudgement_db.Score(userIdx) values(?);\n" +
                "insert into theJudgement_db.IncidentNote(userIdx, evidenceIdx) values(?,10);";
        Object[] createUserParams = new Object[]{userIdx,userIdx};
        this.jdbcTemplate.update(createUserQuery, createUserParams);
    }

    // 이메일 중복확인 쿼리
    public int checkEmail(String email){
        String checkEmailQuery = "select exists(select email from theJudgement_db.User where email = ?)";
        String checkEmailParams = email;
        return this.jdbcTemplate.queryForObject(checkEmailQuery,
                int.class,
                checkEmailParams);

    }




}
