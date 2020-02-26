package com.onur.spring.dao;

import com.onur.spring.domain.Auditorium;
import com.onur.spring.domain.Event;
import com.onur.spring.domain.EventRating;
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
public class EventDao {

  JdbcTemplate jdbcTemplate;

  @Autowired
  public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public Event getByName(String name) {
    String sql = "SELECT * FROM events WHERE name='" + name + "'";
    return getEventBySQL(sql);
  }

  public boolean save(Event event) {
    int control = -1;
    long id = event.getId();
    String name = event.getName();
    NavigableSet<LocalDateTime> airDates = event.getAirDates();

    String airDatesFormatted = "";
    for (LocalDateTime ldt : airDates) {
      airDatesFormatted += ldt.toString() + ",";
    }
    airDatesFormatted = airDatesFormatted.replaceFirst(".$", "");

    String basePrice = Double.toString(event.getBasePrice());
    String rating = event.getRating().toString();
    NavigableMap<LocalDateTime, Auditorium> auditoriums = event.getAuditoriums();

    String auditoriumsFormatted = "";
    for (LocalDateTime dateTime : auditoriums.keySet()) {
      Auditorium auditorium = auditoriums.get(dateTime);
      String vipSeatsFormatted = "";
      for (Long seat : auditorium.getVipSeats()) {
        vipSeatsFormatted += Long.toString(seat) + "-";
      }
      vipSeatsFormatted = vipSeatsFormatted.replaceFirst(".$", "");

      auditoriumsFormatted +=
          dateTime.toString()
              + ";"
              + auditorium.getName()
              + ";"
              + Long.toString(auditorium.getNumberOfSeats())
              + ";"
              + vipSeatsFormatted
              + "|";
    }
    auditoriumsFormatted = auditoriumsFormatted.replaceFirst(".$", "");
    control =
        jdbcTemplate.update(
            "INSERT INTO events VALUES(?,?,?,?,?,?)",
            id,
            name,
            airDatesFormatted,
            basePrice,
            rating,
            auditoriumsFormatted);

    if (control != -1) {
      return true;
    }
    return false;
  }

  public boolean remove(Event event) {
    int control = -1;
    long id = event.getId();
    control = jdbcTemplate.update("DELETE FROM events WHERE id = ?", Long.toString(id));
    if (control != -1) {
      return true;
    }
    return false;
  }

  public Event getById(Long id) {
    String sql = "SELECT * FROM events WHERE id=" + id;
    return getEventBySQL(sql);
  }

  public Set<Event> getAll() {
    String sql = "SELECT * FROM events";
    List<Event> eventList =
        jdbcTemplate.query(
            sql,
            new RowMapper<Event>() {
              @Override
              public Event mapRow(ResultSet rs, int rowNum) throws SQLException {
                Integer id = rs.getInt("id");
                String name = rs.getString("name");
                String airDates = rs.getString("airDates");
                String basePrice = rs.getString("basePrice");
                String rating = rs.getString("rating");
                String auditoriums = rs.getString("auditoriums");

                Event event = new Event();
                event.setId((long) id);
                event.setName(name);
                event.setAirDates(convertAirDates(airDates));
                event.setBasePrice(Double.parseDouble(basePrice));
                event.setRating(EventRating.valueOf(rating));
                event.setAuditoriums(convertAuditoriums(auditoriums));
                return event;
              }
            });
    Set<Event> eventSet = eventList.stream().collect(Collectors.toSet());
    return eventSet;
  }

  public int getCallByNameCounter(Long id) {
    String sql =
        "SELECT callByNameCounter FROM event_counters where eventID = " + Long.toString(id);
    int nameCounter = jdbcTemplate.queryForObject(sql, Integer.class);
    return nameCounter;
  }

  public int getPriceQueriedCounter(Long id) {
    String sql = "SELECT priceQueriedCounter FROM event_counters where eventID = " + id;
    int priceQueriedCounter = jdbcTemplate.queryForObject(sql, Integer.class);
    return priceQueriedCounter;
  }

  public int getTicketsBookedCounter(Long id) {
    String sql = "SELECT ticketsBookedCounter FROM event_counters where eventID = " + id;
    int ticketsBookedCounter = jdbcTemplate.queryForObject(sql, Integer.class);
    return ticketsBookedCounter;
  }

  public void setCallByNameCounter(Long id) {
    int newNameCounter = getCallByNameCounter(id) + 1;
    jdbcTemplate.update(
        "UPDATE event_counters SET callByNameCounter=? WHERE eventID = ?", newNameCounter, id);
  }

  public void setPriceQueriedCounter(Long id) {
    int newPriceQueriedCounter = getPriceQueriedCounter(id) + 1;
    jdbcTemplate.update(
        "UPDATE event_counters SET priceQueriedCounter=? WHERE eventID = ?",
        newPriceQueriedCounter,
        id);
  }

  public void setTicketsBookedCounter(Long id) {
    int newTicketsBookedCounter = getTicketsBookedCounter(id) + 1;
    jdbcTemplate.update(
        "UPDATE event_counters SET ticketsBookedCounter=? WHERE eventID = ?",
        newTicketsBookedCounter,
        id);
  }

  private NavigableSet<LocalDateTime> convertAirDates(String airDatesStr) {
    NavigableSet<LocalDateTime> airDates = new TreeSet<>();
    String[] arr = airDatesStr.split(",");
    for (String data : arr) {
      LocalDateTime airDate = LocalDateTime.parse(data);
      airDates.add(airDate);
    }
    return airDates;
  }

  private NavigableMap<LocalDateTime, Auditorium> convertAuditoriums(String auditoriumsStr) {
    NavigableMap<LocalDateTime, Auditorium> auditoriums = new TreeMap<>();
    String[] arr = auditoriumsStr.split("\\|");
    for (String data : arr) {
      String[] inner_data = data.split(";");
      LocalDateTime airDate = LocalDateTime.parse(inner_data[0]);
      String name = inner_data[1];
      String numSeats = inner_data[2];
      String vipSeats = inner_data[3];
      Set<Long> vipSeatSet = new HashSet<>();
      String[] vipSeatsArr = vipSeats.split("-");
      for (String seat : vipSeatsArr) {
        vipSeatSet.add(Long.parseLong(seat));
      }
      Auditorium auditorium = new Auditorium();
      auditorium.setName(name);
      auditorium.setNumberOfSeats(Long.parseLong(numSeats));
      auditorium.setVipSeats(vipSeatSet);
      auditoriums.put(airDate, auditorium);
    }
    return auditoriums;
  }

  private Event getEventBySQL(String sql) {
    try {
      Event resultEvent =
          jdbcTemplate.queryForObject(
              sql,
              new RowMapper<Event>() {
                @Override
                public Event mapRow(ResultSet rs, int rowNum) throws SQLException {
                  Integer id = rs.getInt("id");
                  String name = rs.getString("name");
                  String airDates = rs.getString("airDates");
                  String basePrice = rs.getString("basePrice");
                  String rating = rs.getString("rating");
                  String auditoriums = rs.getString("auditoriums");

                  Event event = new Event();
                  event.setId((long) id);
                  event.setName(name);
                  event.setAirDates(convertAirDates(airDates));
                  event.setBasePrice(Double.parseDouble(basePrice));
                  event.setRating(EventRating.valueOf(rating));
                  event.setAuditoriums(convertAuditoriums(auditoriums));
                  return event;
                }
              });
      return resultEvent;
    } catch (Exception e) {
      return null;
    }
  }
}
