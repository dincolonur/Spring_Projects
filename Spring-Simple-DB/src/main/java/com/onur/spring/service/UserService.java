package com.onur.spring.service;

import com.onur.spring.domain.User;

import java.util.Set;
// import javax.annotation.Nonnull;
// import javax.annotation.Nullable;

public interface UserService extends AbstractDomainObjectService<User> {

  /**
   * Finding user by email
   *
   * @param email Email of the user
   * @return found user or <code>null</code>
   */
  public User getUserByEmail(String email);

  public User getById(Long id);

  public boolean saveUser(User user);

  public boolean removeUser(User user);

  public Set<User> getAll();
}
