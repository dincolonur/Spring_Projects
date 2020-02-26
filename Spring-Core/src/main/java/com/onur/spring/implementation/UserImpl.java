package com.onur.spring.implementation;

import com.onur.spring.dao.UserDao;
import com.onur.spring.domain.User;
import com.onur.spring.service.UserService;

import java.util.Set;

public class UserImpl implements UserService {

    public static  UserDao userDao = new UserDao();

    @Override
    public User getUserByEmail(String email) {
        return userDao.getUserByEmail(email);
    }

    @Override
    public User getById(Long id) {
        return userDao.getUserById(id);
    }

    @Override
    public boolean saveUser(User user) {
        return userDao.insertUser(user);
    }

    @Override
    public boolean removeUser(User user) {
        return userDao.removeUser(user) && BookingImpl.ticketDao.removeTickets(user.getTickets());
    }

    @Override
    public Set<User> getAll() {
        return userDao.getAll();
    }


}
