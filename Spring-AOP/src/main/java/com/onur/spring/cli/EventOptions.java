package com.onur.spring.cli;

import com.onur.spring.domain.Auditorium;
import com.onur.spring.domain.Event;
import com.onur.spring.domain.EventRating;
import com.onur.spring.service.AuditoriumService;
import com.onur.spring.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import java.time.LocalDateTime;
import java.util.*;


public class EventOptions {

    @Autowired
    public EventService eventService;
    @Autowired
    public AuditoriumService auditoriumService;


    public void eventMenuOperations(Scanner in){

        printEventOptions();
        String eventInput = in.nextLine();
        switch (eventInput){
            case "1":
                System.out.println(eventService.getAll());
                break;
            case "2":
                System.out.println("Enter Event Name: ");
                String eventName = in.nextLine();
                Event event = eventService.getByName(eventName);
                if (event != null){
                    System.out.println(event);
                } else {
                    System.out.println("Event is not registered...");
                }
                break;
            case "3":
                System.out.println("Enter Event ID: ");
                String eventID = in.nextLine();
                if (eventID.matches("[0-9]+")){
                    event = eventService.getById(Long.parseLong(eventID));
                    if (event != null){
                        System.out.println(event);
                    } else {
                        System.out.println("Event is not registered...");
                    }
                }else {
                    System.out.println("Event ID must be Numeric...");
                }
                break;
            case "4":
                System.out.println("Removing Event. Enter Event ID: ");
                String removeEventID = in.nextLine();
                if (removeEventID.matches("[0-9]+")){
                    event = eventService.getById(Long.parseLong(removeEventID));
                    if (event != null){
                        if (eventService.remove(event)){
                            System.out.println("Event Removed Successfully : " + event);
                        } else {
                            System.out.println("Failure on Event removing process..");
                        }
                    } else {
                        System.out.println("Event is not registered...");
                    }
                }else {
                    System.out.println("Event ID must be Numeric...");
                }
                break;
            case "5":
                System.out.println("Saving New Event:");
                System.out.println("Enter new Event ID: ");
                String newEventID = in.nextLine();
                if (newEventID.matches("[0-9]+")){
                    System.out.println("Enter Event Name: ");
                    String newEventName = in.nextLine();

                    System.out.println("Enter Event Base Price (Default is 12.00): ");
                    String newEventBasePrice = in.nextLine();
                    if(newEventBasePrice.equalsIgnoreCase("") || newEventBasePrice == null){
                        newEventBasePrice = "12.00";
                    }

                    System.out.println("Enter Event Rating (LOW, MID or HIGH): ");
                    String newEventRating = in.nextLine();
                    if(newEventRating.equalsIgnoreCase("") || newEventRating == null){
                        newEventRating = "MID";
                    }
                    EventRating eventRating = EventRating.valueOf(newEventRating);

                    System.out.println("Enter Event Air dates & Place Info in format Mercury=2018-07-01T19:30:00,Mars=2018-07-02T15:30:00 : ");
                    String newEventAirInfo = in.nextLine();
                    String[] newEventAirInfos = newEventAirInfo.split(",");

                    NavigableSet<LocalDateTime> airDates = new TreeSet<>();
                    NavigableMap<LocalDateTime, Auditorium> auditoriums = new TreeMap<>();

                    for (String info : newEventAirInfos){
                        String[] details = info.split("=");
                        LocalDateTime airDate = LocalDateTime.parse(details[1]);
                        Auditorium auditorium = auditoriumService.getByName(details[0]);

                        airDates.add(airDate);
                        auditoriums.put(airDate, auditorium);
                    }

                    Event newEvent = new Event();
                    newEvent.setId(Long.parseLong(newEventID));
                    newEvent.setName(newEventName);
                    newEvent.setBasePrice(Double.parseDouble(newEventBasePrice));
                    newEvent.setRating(eventRating);
                    newEvent.setAirDates(airDates);
                    newEvent.setAuditoriums(auditoriums);

                    if (eventService.save(newEvent)){
                        System.out.println("New Event Saved Successfully: " + newEvent);
                    } else {
                        System.out.println("Failure on Event saving process..");
                    }


                }else {
                    System.out.println("Event ID must be Numeric...");
                }
                break;
        }
    }

    private static void printEventOptions(){
        System.out.println("===============");
        System.out.println(" Please Select Event Options:");
        System.out.println("1 - Get All Events");
        System.out.println("2 - Get Event By Name");
        System.out.println("3 - Get Event By Event ID");
        System.out.println("4 - Remove Event");
        System.out.println("5 - Save Event");
        System.out.println("9 - To Upper Menu");
    }
}
