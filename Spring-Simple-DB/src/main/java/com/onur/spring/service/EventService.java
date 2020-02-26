package com.onur.spring.service;

import com.onur.spring.domain.Event;

import java.util.Set;

public interface EventService extends AbstractDomainObjectService<Event> {

  /**
   * Finding event by name
   *
   * @param name Name of the event
   * @return found event or <code>null</code>
   */
  public Event getByName(String name);

  //    /**
  //     * Finding all events that air on specified date range
  //     *
  //     * @param from Start date
  //     *
  //     * @param to End date inclusive
  //     *
  //     * @return Set of events
  //     */
  //     public Set<Event> getForDateRange(LocalDate from, LocalDate to);
  //
  //    /**
  //     * Return events from 'now' till the the specified date time
  //     *
  //     * @param to End date time inclusive
  //     * s
  //     * @return Set of events
  //     */
  //     public Set<Event> getNextEvents(LocalDateTime to);

  public boolean save(Event event);

  public boolean remove(Event event);

  public Event getById(Long id);

  public Set<Event> getAll();
}
