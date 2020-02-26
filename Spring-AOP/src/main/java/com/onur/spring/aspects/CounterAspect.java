package com.onur.spring.aspects;

import com.onur.spring.dao.EventDao;
import com.onur.spring.domain.Event;
import com.onur.spring.domain.Ticket;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Set;

@Aspect
@Component
public class CounterAspect {

    @Pointcut("execution(* EventImpl.getByName(..))")
    private void getByName(){}

    @Pointcut("execution(* BookingImpl.getTicketsPrice(..))")
    private void getTicketsPrice(){}

    @Pointcut("execution(* BookingImpl.bookTickets(..))")
    private void getBookTickets(){}

    @AfterReturning(pointcut = "getByName()", returning = "retVal")
    public void doAfterReturningTask(Object retVal) {
        Event event = (Event) retVal;
        EventDao eventDao = new EventDao();
        eventDao.setCallByNameCounter(event.getId());
    }

    @AfterReturning(pointcut = "getTicketsPrice() && args(event,..)")
    public void afterSuccessfulReturn(JoinPoint joinPoint, Event event) {
        EventDao eventDao = new EventDao();
        eventDao.setPriceQueriedCounter(event.getId());
    }

    @AfterReturning(pointcut = "getBookTickets() && args(ticketSet,..)")
    public void afterSuccessfulReturn(JoinPoint joinPoint, Set<Ticket> ticketSet) {
        EventDao eventDao = new EventDao();
        for(Ticket ticket : ticketSet){
            eventDao.setTicketsBookedCounter(ticket.getEvent().getId());
        }

    }

}
