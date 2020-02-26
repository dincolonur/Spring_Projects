package com.onur.spring.setup;

import com.epam.spring.domain.*;
import com.onur.spring.service.AuditoriumService;
import com.onur.spring.service.BookingService;
import com.onur.spring.service.EventService;
import com.onur.spring.service.UserService;
import com.onur.spring.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.TreeMap;
import java.util.TreeSet;

public class Loader {

    @Autowired
    public UserService userService;
    @Autowired
    public EventService eventService;
    @Autowired
    public AuditoriumService auditoriumService;
    @Autowired
    public BookingService bookingService;

    public  void load(){
        loadEvents();
        loadUsers();
    }

    public  void loadUsers(){


        Event eventAvengers = eventService.getById(11L);
        Event eventLoveStory = eventService.getById(22L);
        Event eventFamilyMan = eventService.getById(33L);


        User userJames = new User();
        userJames.setId(1L);
        userJames.setFirstName("James");
        userJames.setLastName("Darrel");
        userJames.setEmail("james@james.com");
        userJames.setBirthday(LocalDate.of(1990, Month.JULY, 2));

        User userMike = new User();
        userMike.setId(2L);
        userMike.setFirstName("Mike");
        userMike.setLastName("Silver");
        userMike.setEmail("mike@mike.com");
        userMike.setBirthday(LocalDate.of(1990, Month.MAY, 2));

        User userTom = new User();
        userTom.setId(3L);
        userTom.setFirstName("Tom");
        userTom.setLastName("Hawyer");
        userTom.setEmail("tom@tom.com");
        userTom.setBirthday(LocalDate.of(1990, Month.JULY, 1));

        User userHans = new User();
        userHans.setId(4L);
        userHans.setFirstName("Hans");
        userHans.setLastName("Gunter");
        userHans.setEmail("hans@hans.com");
        userHans.setBirthday(LocalDate.of(1990, Month.JUNE, 30));


        Ticket ticketJames1 = new Ticket(1111L, userJames, eventAvengers, LocalDateTime.of(2019, Month.JULY, 1, 19, 30, 00), 34);
        Ticket ticketJames2 = new Ticket(1112L, userJames, eventAvengers, LocalDateTime.of(2019, Month.JULY, 1, 19, 30, 00), 35);
        Ticket ticketJames3 = new Ticket(1113L, userJames, eventLoveStory, LocalDateTime.of(2019, Month.JULY, 2, 15, 30, 00), 22);
        Ticket ticketJames4 = new Ticket(1114L, userJames, eventLoveStory, LocalDateTime.of(2019, Month.JULY, 2, 15, 30, 00), 23);


        NavigableSet<Ticket> ticketJamesSet = new TreeSet<>();
        ticketJamesSet.add(ticketJames1);
        ticketJamesSet.add(ticketJames2);
        ticketJamesSet.add(ticketJames3);
        ticketJamesSet.add(ticketJames4);

        bookingService.bookTickets(ticketJamesSet);

        userJames.setTickets(ticketJamesSet);
        userService.saveUser(userJames);


        Ticket ticketMike1 = new Ticket(2221L, userMike, eventAvengers, LocalDateTime.of(2019, Month.JULY, 2, 19, 30, 00), 55);
        Ticket ticketMike2 = new Ticket(2222L, userMike, eventLoveStory, LocalDateTime.of(2019, Month.JULY, 3, 15, 30, 00), 55);
        Ticket ticketMike3 = new Ticket(2223L, userMike, eventLoveStory, LocalDateTime.of(2019, Month.JULY, 4, 15, 30, 00), 56);
        Ticket ticketMike4 = new Ticket(2224L, userMike, eventFamilyMan, LocalDateTime.of(2019, Month.JULY, 4, 11, 30, 00), 65);

        NavigableSet<Ticket> ticketMikeSet = new TreeSet<>();
        ticketMikeSet.add(ticketMike1);
        ticketMikeSet.add(ticketMike2);
        ticketMikeSet.add(ticketMike3);
        ticketMikeSet.add(ticketMike4);

        bookingService.bookTickets(ticketMikeSet);

        userMike.setTickets(ticketMikeSet);
        userService.saveUser(userMike);

        Ticket ticketTom1 = new Ticket(3331L, userTom, eventAvengers, LocalDateTime.of(2019, Month.JULY, 5, 19, 30, 00), 77);
        Ticket ticketTom2 = new Ticket(3332L, userTom, eventAvengers, LocalDateTime.of(2019, Month.JULY, 5, 19, 30, 00), 78);

        NavigableSet<Ticket> ticketTomSet = new TreeSet<>();
        ticketTomSet.add(ticketTom1);
        ticketTomSet.add(ticketTom2);

        bookingService.bookTickets(ticketTomSet);

        userTom.setTickets(ticketTomSet);
        userService.saveUser(userTom);


        Ticket ticketHans1 = new Ticket(4441L, userHans, eventAvengers, LocalDateTime.of(2019, Month.JULY, 3, 19, 30, 00), 44);
        Ticket ticketHans2 = new Ticket(4442L, userHans, eventLoveStory, LocalDateTime.of(2019, Month.JULY, 1, 15, 30, 00), 11);
        Ticket ticketHans3 = new Ticket(4443L, userHans, eventFamilyMan, LocalDateTime.of(2019, Month.JULY, 2, 11, 30, 00), 22);

        NavigableSet<Ticket> ticketHansSet = new TreeSet<>();
        ticketHansSet.add(ticketHans1);
        ticketHansSet.add(ticketHans2);
        ticketHansSet.add(ticketHans3);

        bookingService.bookTickets(ticketHansSet);

        userHans.setTickets(ticketHansSet);
        userService.saveUser(userHans);

    }

    public  void loadEvents(){

        Auditorium auditoriumMercury = auditoriumService.getByName("Mercury");
        Auditorium auditoriumMars = auditoriumService.getByName("Mars");


        LocalDateTime aDateTime = LocalDateTime.of(2019, Month.JULY, 1, 19, 30, 00);
        LocalDateTime bDateTime = LocalDateTime.of(2019, Month.JULY, 2, 19, 30, 00);
        LocalDateTime cDateTime = LocalDateTime.of(2019, Month.JULY, 3, 19, 30, 00);
        LocalDateTime dDateTime = LocalDateTime.of(2019, Month.JULY, 4, 19, 30, 00);
        LocalDateTime eDateTime = LocalDateTime.of(2019, Month.JULY, 5, 19, 30, 00);

        LocalDateTime fDateTime = LocalDateTime.of(2019, Month.JULY, 1, 15, 30, 00);
        LocalDateTime gDateTime = LocalDateTime.of(2019, Month.JULY, 2, 15, 30, 00);
        LocalDateTime hDateTime = LocalDateTime.of(2019, Month.JULY, 3, 15, 30, 00);
        LocalDateTime iDateTime = LocalDateTime.of(2019, Month.JULY, 4, 15, 30, 00);
        LocalDateTime jDateTime = LocalDateTime.of(2019, Month.JULY, 5, 15, 30, 00);

        LocalDateTime kDateTime = LocalDateTime.of(2019, Month.JULY, 1, 11, 30, 00);
        LocalDateTime lDateTime = LocalDateTime.of(2019, Month.JULY, 2, 11, 30, 00);
        LocalDateTime mDateTime = LocalDateTime.of(2019, Month.JULY, 3, 11, 30, 00);
        LocalDateTime nDateTime = LocalDateTime.of(2019, Month.JULY, 4, 11, 30, 00);
        LocalDateTime oDateTime = LocalDateTime.of(2019, Month.JULY, 5, 11, 30, 00);



        Event eventAvengers = new Event();
        eventAvengers.setId(11L);
        eventAvengers.setName("Avengers");
        eventAvengers.setRating(EventRating.HIGH);


        NavigableSet<LocalDateTime> avengersDatesSet = new TreeSet<>();
        avengersDatesSet.add(aDateTime);
        avengersDatesSet.add(bDateTime);
        avengersDatesSet.add(cDateTime);
        avengersDatesSet.add(dDateTime);
        avengersDatesSet.add(eDateTime);

        eventAvengers.setAirDates(avengersDatesSet);


        NavigableMap<LocalDateTime, Auditorium> avengersDatePlaceMap = new TreeMap<>();
        avengersDatePlaceMap.put(aDateTime, auditoriumMercury);
        avengersDatePlaceMap.put(bDateTime, auditoriumMercury);
        avengersDatePlaceMap.put(cDateTime, auditoriumMars);
        avengersDatePlaceMap.put(dDateTime, auditoriumMars);
        avengersDatePlaceMap.put(eDateTime, auditoriumMars);

        eventAvengers.setAuditoriums(avengersDatePlaceMap);
        eventAvengers.setBasePrice(15.00);

        eventService.save(eventAvengers);

        Event eventLoveStory = new Event();
        eventLoveStory.setId(22L);
        eventLoveStory.setName("Love Story");
        eventLoveStory.setRating(EventRating.MID);


        NavigableSet<LocalDateTime> loveStoryDatesSet = new TreeSet<>();
        loveStoryDatesSet.add(fDateTime);
        loveStoryDatesSet.add(gDateTime);
        loveStoryDatesSet.add(hDateTime);
        loveStoryDatesSet.add(iDateTime);
        loveStoryDatesSet.add(jDateTime);

        eventLoveStory.setAirDates(loveStoryDatesSet);


        NavigableMap<LocalDateTime, Auditorium> loveStoryDatePlaceMap = new TreeMap<>();
        loveStoryDatePlaceMap.put(fDateTime, auditoriumMars);
        loveStoryDatePlaceMap.put(gDateTime, auditoriumMars);
        loveStoryDatePlaceMap.put(hDateTime, auditoriumMars);
        loveStoryDatePlaceMap.put(iDateTime, auditoriumMercury);
        loveStoryDatePlaceMap.put(jDateTime, auditoriumMercury);

        eventLoveStory.setAuditoriums(loveStoryDatePlaceMap);
        eventLoveStory.setBasePrice(12.00);

        eventService.save(eventLoveStory);

        Event eventFamilyMan = new Event();
        eventFamilyMan.setId(33L);
        eventFamilyMan.setName("Family Man");
        eventFamilyMan.setRating(EventRating.LOW);


        NavigableSet<LocalDateTime> familyManDatesSet = new TreeSet<>();
        familyManDatesSet.add(kDateTime);
        familyManDatesSet.add(lDateTime);
        familyManDatesSet.add(mDateTime);
        familyManDatesSet.add(nDateTime);
        familyManDatesSet.add(oDateTime);

        eventFamilyMan.setAirDates(familyManDatesSet);


        NavigableMap<LocalDateTime, Auditorium> familyManDatePlaceMap = new TreeMap<>();
        familyManDatePlaceMap.put(kDateTime, auditoriumMercury);
        familyManDatePlaceMap.put(lDateTime, auditoriumMars);
        familyManDatePlaceMap.put(mDateTime, auditoriumMars);
        familyManDatePlaceMap.put(nDateTime, auditoriumMercury);
        familyManDatePlaceMap.put(oDateTime, auditoriumMercury);

        eventFamilyMan.setAuditoriums(familyManDatePlaceMap);
        eventFamilyMan.setBasePrice(10.00);

        eventService.save(eventFamilyMan);
    }
}
