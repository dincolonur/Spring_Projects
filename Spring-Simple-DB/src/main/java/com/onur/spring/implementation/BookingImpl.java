package com.onur.spring.implementation;

import com.onur.spring.dao.TicketDao;
import com.onur.spring.domain.Event;
import com.onur.spring.domain.EventRating;
import com.onur.spring.domain.Ticket;
import com.onur.spring.domain.User;
import com.onur.spring.service.BookingService;
import com.onur.spring.service.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Set;

@Service
public class BookingImpl implements BookingService {

  @Autowired private DiscountService discountService;

  @Autowired private TicketDao ticketDao;

  @Override
  public double getTicketsPrice(Event event, LocalDateTime dateTime, User user, Set<Long> seats) {
    double totalTicketPrice = 0.0;
    double ticketPrice = event.getBasePrice();

    if (event.getRating() == EventRating.HIGH) {
      ticketPrice *= 2;
    }
    Set<Long> vipSeats = event.getAuditoriums().get(dateTime).getVipSeats();
    for (Long seat : seats) {
      if (vipSeats.contains(seat)) {
        totalTicketPrice += (ticketPrice * 2);
      } else {
        totalTicketPrice += ticketPrice;
      }
    }
    byte discount = discountService.getDiscount(user, event, dateTime, seats.size());
    totalTicketPrice = (totalTicketPrice * ((double) (100 - discount) / 100));

    return totalTicketPrice;
  }

  @Override
  public void bookTickets(Set<Ticket> tickets) {
    ticketDao.bookTickets(tickets);
  }

  @Override
  public Set<Ticket> getPurchasedTicketsForEvent(Event event, LocalDateTime dateTime) {
    return ticketDao.getPurchasedTicketsForEvent(event, dateTime);
  }

  @Override
  public Ticket getTicket(long id) {
    return ticketDao.getTicketById(id);
  }

  @Override
  public Set<Ticket> getAll() {
    return ticketDao.getAll();
  }

  @Override
  public boolean removeTickets(Set<Ticket> ticketSet) {
    return ticketDao.removeTickets(ticketSet);
  }
}
