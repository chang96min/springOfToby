package com.toby.springoftoby.user.dao;

import com.toby.springoftoby.user.domain.Level;
import com.toby.springoftoby.user.domain.User;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.dao.EmptyResultDataAccessException;

import java.sql.*;

public class UserDao {
    private ConnectionMaker connectionMaker;

    public void setConnectionMaker(ConnectionMaker connectionMaker) {
        this.connectionMaker = connectionMaker;
    }

    public void add(User user) throws ClassNotFoundException, SQLException {
        Connection c = connectionMaker.makeConnection();
        PreparedStatement ps = c.prepareStatement(
                "insert into users(id, name, password, level, login, recommend) values(?,?,?,?,?,?)");
        ps.setString(1, user.getId());
        ps.setString(2, user.getName());
        ps.setString(3, user.getPassword());
        ps.setInt(4, (user.getLevel().intValue()));
        ps.setInt(5, user.getLogin());
        ps.setInt(6, user.getRecommend());

        ps.executeUpdate();

        ps.close();
        c.close();
    }



    public User get(String id) throws ClassNotFoundException, SQLException {
        Connection c = connectionMaker.makeConnection();
        PreparedStatement ps = c.prepareStatement(
                "select * from users where id = ?");
        ps.setString(1, id);

        ResultSet rs = ps.executeQuery();
        User user = null;
        if(rs.next()) {
            user = new User();
            user.setId(rs.getString("id"));
            user.setName(rs.getString("name"));
            user.setPassword(rs.getString("password"));
            user.setLevel(Level.valueOf(rs.getInt("level")));
            user.setLogin(rs.getInt("login"));
            user.setRecommend(rs.getInt("recommend"));
        }

        rs.close();
        ps.close();
        c.close();

        if(user == null) throw new EmptyResultDataAccessException(1);

        return user;
    }

    public void deleteAll() throws SQLException, ClassNotFoundException {
        Connection c = connectionMaker.makeConnection();
        PreparedStatement ps = c.prepareStatement(
                "delete from users");

        ps.executeUpdate();

        ps.close();
        c.close();
    }

    public int getCount() throws SQLException, ClassNotFoundException {
        Connection c = connectionMaker.makeConnection();
        PreparedStatement ps = c.prepareStatement(
                "select count(*) from users");

        ResultSet rs = ps.executeQuery();

        rs.next();
        int count = rs.getInt(1);

        rs.close();;
        ps.close();
        c.close();

        return count;
    }

    public void update(User user) throws SQLException, ClassNotFoundException {
        Connection c = connectionMaker.makeConnection();
        PreparedStatement ps = c.prepareStatement(
                "update users set name = ?, password = ?, level = ?, login = ?, recommend = ? where id = ?");
        ps.setString(6, user.getId());
        ps.setString(1, user.getName());
        ps.setString(2, user.getPassword());
        ps.setInt(3, (user.getLevel().intValue()));
        ps.setInt(4, user.getLogin());
        ps.setInt(5, user.getRecommend());

        ps.executeUpdate();

        ps.close();
        c.close();
    }
}
