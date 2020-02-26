package com.onur.spring.implementation;

import com.onur.spring.dao.TicketDao;
import com.onur.spring.dao.UserDao;
import com.onur.spring.domain.User;
import com.onur.spring.service.UserService;

import java.util.Set;

public class UserImpl implements UserService {


    @Override
    public User getUserByEmail(String email) {
        UserDao userDao = new UserDao();
        return userDao.getUserByEmail(email);
    }

    @Override
    public User getById(Long id) {
        UserDao userDao = new UserDao();
        return userDao.getUserById(id);
    }

    @Override
    public boolean saveUser(User user) {
        UserDao userDao = new UserDao();
        return userDao.insertUser(user);
    }

    @Override
    public boolean removeUser(User user) {
        UserDao userDao = new UserDao();
        TicketDao ticketDao = new TicketDao();
        return userDao.removeUser(user) && ticketDao.removeTickets(user.getTickets());
    }

    @Override
    public Set<User> getAll() {
        UserDao userDao = new UserDao();
        return userDao.getAll();
    }


}
