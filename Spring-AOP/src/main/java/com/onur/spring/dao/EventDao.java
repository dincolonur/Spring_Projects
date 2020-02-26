package com.onur.spring.dao;

import com.onur.spring.domain.Event;
import com.onur.spring.domain.EventCounters;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class EventDao {

    public static HashMap<Long, Event> eventMap = new HashMap<Long, Event>();
    public static HashMap<Long, EventCounters> eventCountersMap = new HashMap<Long, EventCounters>();

    public Event getByName(String name) {
        for (Long id : eventMap.keySet()){
            Event event = eventMap.get(id);
            if (event.getName().equalsIgnoreCase(name)){
                return event;
            }
        }
        return null;
    }

    public boolean save(Event event){
        int size = eventMap.size();
        eventMap.put(event.getId(), event);
        if (eventMap.size() == size + 1){
            return true;
        }
        return false;
    }

    public boolean remove(Event event) {
        int size = eventMap.size();
        eventMap.remove(event.getId());
        if (eventMap.size() == size - 1){
            return true;
        }
        return false;
    }

    public Event getById(Long id) {
        return eventMap.get(id);
    }

    public Set<Event> getAll() {
        Set<Event> eventSet = new HashSet<>();

        for (Long id : eventMap.keySet()){
            Event event = eventMap.get(id);
            eventSet.add(event);
        }
        return eventSet;
    }

    public int getCallByNameCounter(Long id){
        if (eventCountersMap.get(id) != null){
            return eventCountersMap.get(id).getCallByNameCounter();
        }
        return 0;
    }

    public int getPriceQueriedCounter(Long id){
        if (eventCountersMap.get(id) != null){
            return eventCountersMap.get(id).getPriceQueriedCounter();
        }
        return 0;
    }

    public int getTicketsBookedCounter(Long id){
        if (eventCountersMap.get(id) != null){
            return eventCountersMap.get(id).getTicketsBookedCounter();
        }
        return 0;
    }
    public void setCallByNameCounter(Long id){
        EventCounters eventCounters = eventCountersMap.get(id);
        if (eventCounters == null) {
            eventCounters = new EventCounters();
        }
        int callByNameCounter = eventCounters.getCallByNameCounter() + 1;
        eventCounters.setCallByNameCounter(callByNameCounter);
        eventCountersMap.put(id, eventCounters);
    }

    public void setPriceQueriedCounter(Long id){
        EventCounters eventCounters = eventCountersMap.get(id);
        if (eventCounters == null) {
            eventCounters = new EventCounters();
        }
        int priceQueriedCounter = eventCounters.getPriceQueriedCounter() + 1;
        eventCounters.setPriceQueriedCounter(priceQueriedCounter);
        eventCountersMap.put(id, eventCounters);
    }

    public void setTicketsBookedCounter(Long id){
        EventCounters eventCounters = eventCountersMap.get(id);
        if (eventCounters == null) {
            eventCounters = new EventCounters();
        }
        int ticketsBookedCounter = eventCounters.getTicketsBookedCounter() + 1;
        eventCounters.setTicketsBookedCounter(ticketsBookedCounter);
        eventCountersMap.put(id, eventCounters);
    }
}
