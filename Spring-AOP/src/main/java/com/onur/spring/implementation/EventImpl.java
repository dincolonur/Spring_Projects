package com.onur.spring.implementation;

import com.onur.spring.dao.EventDao;
import com.onur.spring.domain.Event;
import com.onur.spring.service.EventService;

import java.util.Set;

public class EventImpl implements EventService {


    @Override
    public Event getByName(String name) {
        EventDao eventDao = new EventDao();
        return eventDao.getByName(name);
    }

    @Override
    public boolean save(Event event) {
        EventDao eventDao = new EventDao();
        return eventDao.save(event);
    }

    @Override
    public boolean remove(Event event) {
        EventDao eventDao = new EventDao();
        return eventDao.remove(event);
    }

    @Override
    public Event getById(Long id) {
        EventDao eventDao = new EventDao();
        return eventDao.getById(id);
    }

    @Override
    public Set<Event> getAll() {
        EventDao eventDao = new EventDao();
        return eventDao.getAll();
    }

}
