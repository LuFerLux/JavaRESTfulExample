/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.luferlux.database.service;

import com.luferlux.database.dao.UserDao;
import com.luferlux.database.entity.User;
import java.util.List;

/**
 *
 * @author LuFerLux
 */
public class UserService
{

    private UserDao userDao;

    public UserService()
    {
        userDao = new UserDao();
    }

    public List<User> getAll()
    {
        return userDao.getAll();
    }

    public User get(User user)
    {
        return userDao.get(user.getUsername(), user.getDescription());
    }

    public void create(User user) throws Exception
    {
        if (user == null)
            throw new NullPointerException("Usuario no puede ser null");

        if (user.getUsername() == null || user.getUsername().length() == 0)
            throw new Exception("Nombre de usuario no contiene caracteres: '" + user.getUsername() + "'");

        if (user.getDescription() == null || user.getDescription().length() == 0)
            throw new Exception("Descripcion del usuario no contiene caracteres: '" + user.getDescription() + "'");

        userDao.addUser(user);
    }

    public void update(User user) throws Exception
    {
        if (user == null)
            throw new NullPointerException("Usuario no puede ser null");

        if (user.getUsername() == null || user.getUsername().length() == 0)
            throw new Exception("Nombre de usuario no contiene caracteres: '" + user.getUsername() + "'");

        if (user.getDescription() == null || user.getDescription().length() == 0)
            throw new Exception("Descripcion del usuario no contiene caracteres: '" + user.getDescription() + "'");

        userDao.updateUser(user);
    }

    public void delete(User user) throws Exception
    {
        if (user == null)
            throw new NullPointerException("Usuario no puede ser null");
        
        if (user.getId() < 1)
            throw new Exception("Id de usuario inválido: '" + user.getId() + "'.");

        userDao.deleteUser(user);
    }
}
