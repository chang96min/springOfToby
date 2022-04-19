package com.toby.springoftoby;

import com.toby.springoftoby.user.dao.CountingConnectionMaker;
import com.toby.springoftoby.user.dao.CountingDaoFactory;
import com.toby.springoftoby.user.dao.DaoFactory;
import com.toby.springoftoby.user.dao.UserDao;
import com.toby.springoftoby.user.domain.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;

@SpringBootApplication
public class SpringOfTobyApplication {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
//        ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
//
//        UserDao dao = context.getBean("userDao", UserDao.class);
//
//        User user = new User();
//        user.setId("whiteship");
//        user.setName("백기선");
//        user.setPassword("married");
//
//        dao.add(user);
//
//        System.out.println(user.getId() + "등록 성공");
//
//        User user2 = dao.get(user.getId());
//        System.out.println(user2.getName());
//        System.out.println(user2.getPassword());
//        System.out.println(user2.getId()+"조회 성공");

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(CountingDaoFactory.class);

        UserDao dao = context.getBean("userDao",UserDao.class);

        CountingConnectionMaker ccm = context.getBean("connectionMaker", CountingConnectionMaker.class);
        System.out.println("Connection counter : "+ccm.getCounter());
    }

}
