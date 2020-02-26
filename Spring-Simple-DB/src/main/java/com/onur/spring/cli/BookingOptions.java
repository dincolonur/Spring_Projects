package com.onur.spring.cli;

import com.onur.spring.domain.Event;
import com.onur.spring.domain.Ticket;
import com.onur.spring.domain.User;
import com.onur.spring.service.BookingService;
import com.onur.spring.service.EventService;
import com.onur.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class BookingOptions {

  @Autowired public UserService userService;
  @Autowired public EventService eventService;
  @Autowired public BookingService bookingService;

  public void bookingMenuOperations(Scanner in) {

    printBookingOptions();
    String bookingInput = in.nextLine();
    switch (bookingInput) {
      case "1":
        System.out.println("Enter Event Name: ");
        String eventName = in.nextLine();
        Event event = eventService.getByName(eventName);
        if (event != null) {
          System.out.println(
              "Enter Event Date on format YYYY-MM-DDTHH:MM:SS (Ex. 2019-07-01T19:30:00) : ");
          String eventDate = in.nextLine();

          LocalDateTime airDate = LocalDateTime.parse(eventDate);

          System.out.println("Enter User ID: ");
          String userID = in.nextLine();

          if (userID.matches("[0-9]+")) {
            User user = userService.getById(Long.parseLong(userID));
            if (user != null) {
              System.out.println("Enter Seat Numbers comma seperated (Ex: 55,56,57): ");
              String seats = in.nextLine();
              if (seats.split(",").length > 0) {
                Set<Long> seatsSet =
                    (new ArrayList<String>(Arrays.asList(seats.split(","))))
                        .stream().map(Long::valueOf).collect(Collectors.toSet());

                double ticketsPrice =
                    bookingService.getTicketsPrice(event, airDate, user, seatsSet);
                System.out.println(
                    "Tickets Price: "
                        + ticketsPrice
                        + " Event: "
                        + eventName
                        + " User: "
                        + user.getFirstName()
                        + " "
                        + user.getLastName()
                        + " AirDate: "
                        + airDate
                        + " Seats: "
                        + seatsSet);
                System.out.println("===============");
              } else {
                System.out.println("Enter Seat Numbers comma seperated (Ex: 55,56,57)");
              }
            } else {
              System.out.println("User is not registered.");
            }
          } else {
            System.out.println("User ID must be Numeric...");
          }
        } else {
          System.out.println("Event is not registered.");
        }
        break;
      case "2":
        Set<Ticket> ticketSet = new HashSet<>();
        String checker = "Y";

        while (checker.equalsIgnoreCase("Y")) {
          System.out.println(
              "Enter Booking Ticket Info on format Ticket Id, User Id, Event Id, Air Date, Seat Number:");
          System.out.println("Ex. 21,2,22,2019-07-03T15:30:00,55");

          String ticketInfo = in.nextLine();
          String[] ticketInfoArr = ticketInfo.split(",");
          event = eventService.getById(Long.parseLong(ticketInfoArr[2]));
          LocalDateTime airDate = LocalDateTime.parse(ticketInfoArr[3]);
          long seat = Long.parseLong(ticketInfoArr[4]);

          Ticket ticket =
              new Ticket(
                  Long.parseLong(ticketInfoArr[0]),
                  Long.parseLong(ticketInfoArr[1]),
                  event,
                  airDate,
                  seat);
          ticketSet.add(ticket);
          System.out.println("Add more Ticket, Enter Y or N: ");
          checker = in.nextLine();
        }
        bookingService.bookTickets(ticketSet);
        System.out.println("Ticket Booking is completed for Tickets: " + ticketSet);
        System.out.println("===============");
        break;
      case "3":
        System.out.println("Enter Event Name: ");
        eventName = in.nextLine();
        event = eventService.getByName(eventName);
        if (event != null) {
          System.out.println(
              "Enter Event Date on format YYYY-MM-DDTHH:MM:SS (Ex. 2019-07-01T19:30:00) : ");
          String eventDate = in.nextLine();
          try {
            LocalDateTime airDate = LocalDateTime.parse(eventDate);
            ticketSet = bookingService.getPurchasedTicketsForEvent(event, airDate);
            if (ticketSet != null) {
              System.out.println("Purchased Tickets for Event: " + eventName + " " + ticketSet);
            } else {
              System.out.println(
                  "There is no registered ticket for " + eventName + " on " + airDate.toString());
            }
          } catch (Exception e) {
            System.out.println(
                "There is no registered ticket for " + eventName + " with these options");
          }

        } else {
          System.out.println("Event is not registered.");
        }
        break;
      case "4":
        System.out.println(bookingService.getAll());
        break;
    }
  }

  private static void printBookingOptions() {
    System.out.println("===============");
    System.out.println(" Please Select Booking Options:");
    System.out.println("1 - Get Tickets Price");
    System.out.println("2 - Book Ticket");
    System.out.println("3 - Get Purchased Tickets for Event");
    System.out.println("4 - Get All Tickets");
    System.out.println("9 - To Upper Menu");
  }
}
