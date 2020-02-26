package com.onur.spring;

import com.epam.spring.service.*;
import com.onur.spring.setup.Loader;
import com.onur.spring.cli.BookingOptions;
import com.onur.spring.cli.EventOptions;
import com.onur.spring.cli.UserOptions;
import com.onur.spring.service.*;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Scanner;

public class App {

    public static UserService userService;
    public static EventService eventService;
    public static AuditoriumService auditoriumService;
    public static BookingService bookingService;
    public static DiscountService discountService;

    public App(UserService userService, EventService eventService, AuditoriumService auditoriumService,
               BookingService bookingService, DiscountService discountService) {
        this.userService = userService;
        this.eventService = eventService;
        this.auditoriumService = auditoriumService;
        this.bookingService = bookingService;
        this.discountService = discountService;
    }


    public static void main(String[] args) {

        ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        printMenuOptions();

        App app = (App)ctx.getBean("app");
        Loader.load();


        Scanner in = new Scanner(System.in);
        String input = in.nextLine();

        while (!input.equalsIgnoreCase("9") && input.matches("[0-9]+")){
            switch (input){
                case "1":
                    UserOptions.userMenuOperations(in);
                    break;
                case "2":
                    EventOptions.eventMenuOperations(in);
                    break;
                case "3":
                    BookingOptions.bookingMenuOperations(in);
                    break;

            }
            printMenuOptions();
            input = in.nextLine();
        }


    }


    public static void printMenuOptions(){
        System.out.println("===============");
        System.out.println(" Please Select Menu Options:");
        System.out.println("1 - User Operations");
        System.out.println("2 - Event Operations");
        System.out.println("3 - Booking Operations");
        System.out.println("9 - Exit");
    }




}
