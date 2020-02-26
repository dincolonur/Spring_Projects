package com.onur.spring.dao;

import com.onur.spring.domain.Event;
import com.onur.spring.domain.Ticket;
import com.onur.spring.domain.User;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class TicketDao {

    public static HashMap<Long, Ticket> ticketMap = new HashMap<Long, Ticket>();

    public void bookTickets(Set<Ticket> tickets) {
        for(Ticket ticket: tickets){
            ticketMap.put(ticket.getId(), ticket);
            User user = ticket.getUser();
            user.getTickets().add(ticket);
        }
    }

    public Set<Ticket> getPurchasedTicketsForEvent(Event event, LocalDateTime dateTime) {
        Set<Ticket> purchasedTicketsSet = new HashSet<>();
        for(Long id : ticketMap.keySet()){
            Ticket ticket = ticketMap.get(id);
            if(event.equals(ticket.getEvent()) && dateTime.equals(ticket.getDateTime())){
                purchasedTicketsSet.add(ticket);
            }
        }
        return purchasedTicketsSet;
    }

    public Set<Ticket> getAll(){
        Set<Ticket> ticketsSet = new HashSet<>();
        for(Long id : ticketMap.keySet()){
            Ticket ticket = ticketMap.get(id);
            ticketsSet.add(ticket);
        }
        return ticketsSet;
    }

    public boolean removeTickets( Set<Ticket> ticketSet){
        int setSize = ticketSet.size();
        int mapSize = ticketMap.size();
        for(Ticket ticket : ticketSet){
            long id = ticket.getId();
            ticketMap.remove(id);
        }
        if(mapSize == (setSize + ticketMap.size())){
            return true;
        }
        return false;
    }

    public boolean removeTicket( Ticket ticket){
        long id = ticket.getId();
        int size = ticketMap.size();
        ticketMap.remove(id);

        if (ticketMap.size() == size - 1){
            return true;
        }
        return false;
    }

    public Ticket getTicket(long id){
        return ticketMap.get(id);
    }
}
