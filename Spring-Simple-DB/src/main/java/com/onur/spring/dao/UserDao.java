package com.onur.spring.dao;

import com.onur.spring.domain.Ticket;
import com.onur.spring.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class UserDao {

  @Autowired private TicketDao ticketDao;

  JdbcTemplate jdbcTemplate;

  @Autowired
  public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public boolean insertUser(User user) {
    int control = -1;
    long id = user.getId();
    String firstName = user.getFirstName();
    String lastName = user.getLastName();
    String email = user.getEmail();
    NavigableSet<Ticket> ticketSet = user.getTickets();
    String localDateBirthday = user.getBirthday().toString();
    String tickets = "";
    for (Ticket ticket : ticketSet) {
      tickets += Long.toString(ticket.getId()) + ";";
    }
    tickets = tickets.replaceFirst(".$", "");
    control =
        jdbcTemplate.update(
            "INSERT INTO users VALUES(?,?,?,?,?,?)",
            id,
            firstName,
            lastName,
            email,
            tickets,
            localDateBirthday);

    if (control != -1) {
      return true;
    }
    return false;
  }

  public User getUserById(Long id) {
    String sql = "SELECT * FROM users WHERE id=" + Long.toString(id);
    return getUserBySQL(sql);
  }

  public User getUserByEmail(String email) {
    String sql = "SELECT * FROM users WHERE email='" + email + "'";
    return getUserBySQL(sql);
  }

  public boolean removeUser(User user) {
    int control = -1;
    long id = user.getId();
    control = jdbcTemplate.update("DELETE FROM users WHERE id = ?", id);
    if (control != -1) {
      return true;
    }
    return false;
  }

  public Set<User> getAll() {
    String sql = "SELECT * FROM users";
    List<User> userList =
        jdbcTemplate.query(
            sql,
            new RowMapper<User>() {
              @Override
              public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                Integer id = rs.getInt("id");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                String email = rs.getString("email");
                String tickets = rs.getString("tickets");
                String birthday = rs.getString("birthday");

                LocalDate localDateBirthday = LocalDate.parse(birthday);
                NavigableSet<Ticket> ticketSet = new TreeSet<>();

                if (!tickets.equalsIgnoreCase("")) {
                  String[] arr = tickets.split(";");
                  if (arr.length > 0) {
                    for (String ticketIdStr : arr) {
                      if (ticketDao.getTicketById(Long.parseLong(ticketIdStr)) != null) {
                        Ticket ticket = ticketDao.getTicketById(Long.parseLong(ticketIdStr));
                        ticketSet.add(ticket);
                      }
                    }
                  }
                }
                User user = new User();
                user.setId((long) id);
                user.setFirstName(firstName);
                user.setLastName(lastName);
                user.setEmail(email);
                user.setTickets(ticketSet);
                user.setBirthday(localDateBirthday);
                return user;
              }
            });
    Set<User> userSet = userList.stream().collect(Collectors.toSet());
    return userSet;
  }

  public boolean updateUserTickets(User user, NavigableSet<Ticket> newTickets) {
    int control = -1;
    long id = user.getId();
    String tickets = "";
    NavigableSet<Ticket> allTickets = user.getTickets();
    allTickets.addAll(newTickets);
    for (Ticket ticket : allTickets) {
      tickets += Long.toString(ticket.getId()) + ";";
    }
    tickets = tickets.replaceFirst(".$", "");

    control = jdbcTemplate.update("UPDATE users SET tickets=? WHERE id = ?", tickets, id);
    if (control != -1) {
      return true;
    }
    return false;
  }

  public void setUserDiscountMap(Long id, Integer discount) {
    int newTotalDiscount = getTotalUserDiscount(id) + 1;
    jdbcTemplate.update(
        "UPDATE user_discount SET totalDiscount=? WHERE userID = ?", newTotalDiscount, id);
  }

  public Integer getTotalUserDiscount(Long id) {
    String sql = "SELECT totalDiscount FROM user_discount where userID = " + Long.toString(id);
    int totalDiscount = jdbcTemplate.queryForObject(sql, Integer.class);
    return totalDiscount;
  }

  private User getUserBySQL(String sql) {
    try {
      User resultUser =
          jdbcTemplate.queryForObject(
              sql,
              new RowMapper<User>() {
                @Override
                public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                  Integer id = rs.getInt("id");
                  String firstName = rs.getString("firstName");
                  String lastName = rs.getString("lastName");
                  String email = rs.getString("email");
                  String tickets = rs.getString("tickets");
                  String birthday = rs.getString("birthday");

                  LocalDate localDateBirthday = LocalDate.parse(birthday);
                  NavigableSet<Ticket> ticketSet = new TreeSet<>();

                  if (!tickets.equalsIgnoreCase("")) {
                    String[] arr = tickets.split(";");
                    if (arr.length > 0) {
                      for (String ticketIdStr : arr) {
                        if (ticketDao.getTicketById(Long.parseLong(ticketIdStr)) != null) {
                          Ticket ticket = ticketDao.getTicketById(Long.parseLong(ticketIdStr));
                          ticketSet.add(ticket);
                        }
                      }
                    }
                  }
                  User user = new User();
                  user.setId((long) id);
                  user.setFirstName(firstName);
                  user.setLastName(lastName);
                  user.setEmail(email);
                  user.setTickets(ticketSet);
                  user.setBirthday(localDateBirthday);
                  return user;
                }
              });
      return resultUser;
    } catch (Exception e) {
      return null;
    }
  }
}
