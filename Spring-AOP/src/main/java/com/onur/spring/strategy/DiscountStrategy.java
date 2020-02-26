package com.onur.spring.strategy;

import com.onur.spring.domain.Event;
import com.onur.spring.domain.User;

import java.time.LocalDateTime;

public interface DiscountStrategy {

    public byte calculateDiscount(User user, Event event, LocalDateTime airDateTime, long numberOfTickets);
}
