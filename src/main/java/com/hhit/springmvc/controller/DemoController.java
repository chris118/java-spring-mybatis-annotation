package com.hhit.springmvc.controller;


import com.hhit.springmvc.bean.User;
import com.hhit.springmvc.db.DBUtils;
import org.apache.ibatis.session.SqlSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public ResponseEntity<User> getUser(@PathVariable("id") long id) {

        SqlSession sqlSession = null;
        try {
            sqlSession = DBUtils.openSqlSession();
            User user = sqlSession.selectOne("getUser", id);
            System.out.println(user.toString());
            sqlSession.commit();

            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}