package com.onur.spring.implementation;

import com.onur.spring.dao.TicketDao;
import com.onur.spring.dao.UserDao;
import com.onur.spring.domain.User;
import com.onur.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Set;

@Service
public class UserImpl implements UserService {

  @Autowired private UserDao userDao;

  @Autowired private TicketDao ticketDao;

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
    return userDao.removeUser(user) && ticketDao.removeTickets(user.getTickets());
  }

  @Override
  public Set<User> getAll() {
    return userDao.getAll();
  }
}
