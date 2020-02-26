package com.onur.spring.service;

import com.onur.spring.domain.Event;
import com.onur.spring.domain.Ticket;
import com.onur.spring.domain.User;

import java.time.LocalDateTime;
import java.util.Set;

public interface BookingService {

  /**
   * Getting price when buying all supplied seats for particular event
   *
   * @param event Event to get base ticket price, vip seats and other information
   * @param dateTime Date and time of event air
   * @param user User that buys ticket could be needed to calculate discount. Can be <code>null
   *     </code>
   * @param seats Set of seat numbers that user wants to buy
   * @return total price
   */
  public double getTicketsPrice(Event event, LocalDateTime dateTime, User user, Set<Long> seats);

  /**
   * Books tickets in internal system. If user is not <code>null</code> in a ticket then booked
   * tickets are saved with it
   *
   * @param tickets Set of tickets
   */
  public void bookTickets(Set<Ticket> tickets);

  /**
   * Getting all purchased tickets for event on specific air date and time
   *
   * @param event Event to get tickets for
   * @param dateTime Date and time of airing of event
   * @return set of all purchased tickets
   */
  public Set<Ticket> getPurchasedTicketsForEvent(Event event, LocalDateTime dateTime);

  public Ticket getTicket(long id);

  public Set<Ticket> getAll();

  public boolean removeTickets(Set<Ticket> ticketSet);
}
