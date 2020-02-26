package com.onur.spring.domain;

import java.time.LocalDateTime;
import java.util.Objects;

public class Ticket extends DomainObject implements Comparable<Ticket> {

  private Long userID;

  private Event event;

  private LocalDateTime dateTime;

  private long seat;

  public Ticket(Long id, Long userID, Event event, LocalDateTime dateTime, long seat) {
    this.setId(id);
    this.userID = userID;
    this.event = event;
    this.dateTime = dateTime;
    this.seat = seat;
  }

  public Long getUserID() {
    return userID;
  }

  public Event getEvent() {
    return event;
  }

  public LocalDateTime getDateTime() {
    return dateTime;
  }

  public long getSeat() {
    return seat;
  }

  @Override
  public int hashCode() {
    return Objects.hash(dateTime, event, seat);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    Ticket other = (Ticket) obj;
    if (dateTime == null) {
      if (other.dateTime != null) {
        return false;
      }
    } else if (!dateTime.equals(other.dateTime)) {
      return false;
    }
    if (event == null) {
      if (other.event != null) {
        return false;
      }
    } else if (!event.equals(other.event)) {
      return false;
    }
    if (seat != other.seat) {
      return false;
    }
    return true;
  }

  @Override
  public int compareTo(Ticket other) {
    if (other == null) {
      return 1;
    }
    int result = dateTime.compareTo(other.getDateTime());

    if (result == 0) {
      result = event.getName().compareTo(other.getEvent().getName());
    }
    if (result == 0) {
      result = Long.compare(seat, other.getSeat());
    }
    return result;
  }

  @Override
  public String toString() {
    return "\nTicket{"
        + "id="
        + this.getId()
        + ", user="
        + userID
        + ", event="
        + event.getName()
        + ", dateTime="
        + dateTime.toString()
        + ", seat="
        + seat
        + '}';
  }
}
