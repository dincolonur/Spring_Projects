package com.onur.spring.dao;

import com.onur.spring.domain.Event;
import com.onur.spring.domain.Ticket;
import com.onur.spring.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class TicketDao {

  @Autowired private UserDao userDao;

  @Autowired private EventDao eventDao;

  JdbcTemplate jdbcTemplate;

  @Autowired
  public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public void bookTickets(Set<Ticket> tickets) {
    Map<User, NavigableSet<Ticket>> userTicketsMap = new HashMap<>();
    for (Ticket ticket : tickets) {
      long id = ticket.getId();
      long userID = ticket.getUserID();
      long eventID = ticket.getEvent().getId();
      String eventDateTime = ticket.getDateTime().toString();
      long seat = ticket.getSeat();
      User user = userDao.getUserById(userID);
      if (userTicketsMap.get(user) != null) {
        NavigableSet<Ticket> ticketsOfUser = userTicketsMap.get(user);
        ticketsOfUser.add(ticket);
        userTicketsMap.put(user, ticketsOfUser);
      } else {
        NavigableSet<Ticket> ticketsOfUser = new TreeSet<>();
        ticketsOfUser.add(ticket);
        userTicketsMap.put(user, ticketsOfUser);
      }

      jdbcTemplate.update(
          "INSERT INTO tickets VALUES(?,?,?,?,?)", id, userID, eventID, eventDateTime, seat);
    }
    for (User user : userTicketsMap.keySet()) {
      NavigableSet<Ticket> ticketsOfUser = userTicketsMap.get(user);
      userDao.updateUserTickets(user, ticketsOfUser);
    }
  }

  public Set<Ticket> getPurchasedTicketsForEvent(Event event, LocalDateTime dateTime) {
    String sql =
        "SELECT * FROM tickets WHERE eventID="
            + event.getId()
            + " and eventDateTime = '"
            + dateTime.toString()
            + "'";
    return getTicketsBySQL(sql);
  }

  public Set<Ticket> getAll() {
    String sql = "SELECT * FROM tickets";
    return getTicketsBySQL(sql);
  }

  public boolean removeTickets(Set<Ticket> ticketSet) {
    int control = -1;
    String ticketIDs = "";
    for (Ticket ticket : ticketSet) {
      control =
          jdbcTemplate.update("DELETE FROM tickets WHERE id = ?", Long.toString(ticket.getId()));
      if (control == -1) {
        return false;
      }
    }
    return true;
  }

  public Ticket getTicketById(long id) {
    String sql = "SELECT * FROM tickets WHERE id=" + id;
    return getTicketBySQL(sql);
  }

  private Ticket getTicketBySQL(String sql) {
    Ticket resultTicket =
        jdbcTemplate.queryForObject(
            sql,
            new RowMapper<Ticket>() {
              @Override
              public Ticket mapRow(ResultSet rs, int rowNum) throws SQLException {
                Long id = (long) rs.getInt("id");
                Long userID = (long) rs.getInt("userID");
                Long eventID = (long) rs.getInt("eventID");
                String eventDateTime = rs.getString("eventDateTime");
                Long seat = (long) rs.getInt("seat");

                LocalDateTime airDate = LocalDateTime.parse(eventDateTime);

                Ticket ticket = new Ticket(id, userID, eventDao.getById(eventID), airDate, seat);
                return ticket;
              }
            });
    return resultTicket;
  }

  private Set<Ticket> getTicketsBySQL(String sql) {
    try {
      List<Ticket> ticketList =
          jdbcTemplate.query(
              sql,
              new RowMapper<Ticket>() {
                @Override
                public Ticket mapRow(ResultSet rs, int rowNum) throws SQLException {
                  Long id = (long) rs.getInt("id");
                  Long userID = (long) rs.getInt("userID");
                  Long eventID = (long) rs.getInt("eventID");
                  String eventDateTime = rs.getString("eventDateTime");
                  Long seat = (long) rs.getInt("seat");

                  LocalDateTime airDate = LocalDateTime.parse(eventDateTime);

                  Ticket ticket = new Ticket(id, userID, eventDao.getById(eventID), airDate, seat);
                  return ticket;
                }
              });
      Set<Ticket> ticketSet = ticketList.stream().collect(Collectors.toSet());
      return ticketSet;
    } catch (Exception e) {
      return null;
    }
  }
}
