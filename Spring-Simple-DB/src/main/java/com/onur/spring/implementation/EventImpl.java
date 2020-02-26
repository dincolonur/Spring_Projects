package com.onur.spring.implementation;

import com.onur.spring.dao.EventDao;
import com.onur.spring.domain.Event;
import com.onur.spring.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Set;

@Service
public class EventImpl implements EventService {

  @Autowired private EventDao eventDao;

  @Override
  public Event getByName(String name) {
    return eventDao.getByName(name);
  }

  @Override
  public boolean save(Event event) {
    return eventDao.save(event);
  }

  @Override
  public boolean remove(Event event) {
    return eventDao.remove(event);
  }

  @Override
  public Event getById(Long id) {
    return eventDao.getById(id);
  }

  @Override
  public Set<Event> getAll() {
    return eventDao.getAll();
  }
}
