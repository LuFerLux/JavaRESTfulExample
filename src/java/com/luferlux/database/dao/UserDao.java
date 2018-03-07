/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.luferlux.database.dao;

import com.luferlux.database.entity.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author LuFerLux
 */
public class UserDao
{

    public List<User> getAll()
    {
        List<User> users = new ArrayList<User>();

        try
        {
            Connection connection = Dao.getConnection();

            String query = "select * from user;";

            PreparedStatement ps = connection.prepareStatement(query);

            ResultSet rs = ps.executeQuery();

            User user;

            while (rs.next())
            {
                user = new User(rs.getInt("id"), rs.getNString("username"), rs.getNString("name"));

                users.add(user);
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return users;
    }

    public User get(String username, String name)
    {
        User user = new User();

        try
        {
            Connection connection = Dao.getConnection();

            String query;

            query = "select * from user ";

            query += " where username='" + username + "' and name='" + name + "';";

            PreparedStatement ps = connection.prepareStatement(query);

            ResultSet rs = ps.executeQuery();

            if (rs.next())
                user = new User(rs.getInt("id"), rs.getNString("username"), rs.getNString("name"));
        }
        catch (SQLException ex)
        {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return user;
    }

    public void addUser(User user)
    {
        try
        {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

            Connection connection = Dao.getConnection();

            String query = null;

            query = "insert into user(username, name, fecha) ";

            query += " values(";

            query += "'" + user.getUsername() + "',";

            query += "'" + user.getDescription() + "', ";

            query += "'" + sdf.format(new Date()) + "');";

            PreparedStatement ps = connection.prepareStatement(query);

            ps.execute(query);
        }
        catch (SQLException ex)
        {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void updateUser(User user)
    {
        try
        {
            Connection connection = Dao.getConnection();

            String query = "update user set ";

            query += " username = '" + user.getUsername() + "',";

            query += " name='" + user.getDescription() + "' ";

            query += " where id=" + user.getId();

            PreparedStatement ps = connection.prepareStatement(query);

            ps.execute(query);
        }
        catch (SQLException ex)
        {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void deleteUser(User user)
    {
        try
        {
            Connection connection = Dao.getConnection();

            String query = " delete from user ";

            query += " where id=" + user.getId()+"; ";

            PreparedStatement ps = connection.prepareStatement(query);

            ps.execute(query);
        }
        catch (SQLException ex)
        {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
