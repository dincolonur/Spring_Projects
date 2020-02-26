package com.onur.spring;

import com.onur.spring.cli.BookingOptions;
import com.onur.spring.cli.UserOptions;
import com.onur.spring.config.AppConfig;
import com.onur.spring.dao.EventDao;
import com.onur.spring.dao.UserDao;
import com.onur.spring.cli.EventOptions;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import java.util.Scanner;

public class App {

  public static void main(String[] args) {

    ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
    printMenuOptions();

    Scanner in = new Scanner(System.in);
    String input = in.nextLine();

    while (!input.equalsIgnoreCase("9") && input.matches("[0-9]+")) {
      switch (input) {
        case "1":
          UserOptions userOptions = ctx.getBean(UserOptions.class);
          userOptions.userMenuOperations(in);
          break;
        case "2":
          EventOptions eventOptions = ctx.getBean(EventOptions.class);
          eventOptions.eventMenuOperations(in);
          break;
        case "3":
          BookingOptions bookingOptions = ctx.getBean(BookingOptions.class);
          bookingOptions.bookingMenuOperations(in);
          break;
      }
      printMenuOptions();
      input = in.nextLine();
    }
    EventDao eventDao = ctx.getBean(EventDao.class);
    UserDao userDao = ctx.getBean(UserDao.class);
    System.out.println("Avengers called " + eventDao.getCallByNameCounter(11L));
    System.out.println("Avengers price checked  " + eventDao.getPriceQueriedCounter(11L));
    System.out.println("Avengers booked  " + eventDao.getTicketsBookedCounter(11L));
    System.out.println("James total discount " + userDao.getTotalUserDiscount(1L));
  }

  public static void printMenuOptions() {
    System.out.println("===============");
    System.out.println(" Please Select Menu Options:");
    System.out.println("1 - User Operations");
    System.out.println("2 - Event Operations");
    System.out.println("3 - Booking Operations");
    System.out.println("9 - Exit");
  }
}
