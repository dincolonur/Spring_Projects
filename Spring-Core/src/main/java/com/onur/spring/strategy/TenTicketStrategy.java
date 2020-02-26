package com.onur.spring.strategy;

import com.onur.spring.domain.Event;
import com.onur.spring.domain.User;

import java.time.LocalDateTime;

public class TenTicketStrategy implements DiscountStrategy {

    @Override
    public byte calculateDiscount(User user, Event event, LocalDateTime airDateTime, long numberOfTickets) {
        if (numberOfTickets >= 10){
            int discountTickets = (int)(numberOfTickets / 10);
            int notDiscountTickets = (int)(numberOfTickets - discountTickets);
            double totalDiscountDouble = 1 - ((double)((discountTickets*5) + (notDiscountTickets*10))/(double) (numberOfTickets*10));
            byte totalDiscount = (byte)(totalDiscountDouble*100);
            if (totalDiscount >= 100){
                return 100;
            } else {
                return totalDiscount;
            }
        }
        return 0;
    }

}
