package com.onur.spring.dao;

import com.onur.spring.domain.Event;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class EventDao {

    public static HashMap<Long, Event> eventMap = new HashMap<Long, Event>();

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

}
