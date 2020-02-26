package com.onur.spring.strategy;

import com.onur.spring.domain.Event;
import com.onur.spring.domain.User;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class BirthdayDiscountStrategy implements DiscountStrategy{


    @Override
    public byte calculateDiscount(User user, Event event, LocalDateTime airDateTime, long numberOfTickets) {
        LocalDate userBirthday = user.getBirthday();
        int yearDif = airDateTime.getYear() - userBirthday.getYear();
        userBirthday = userBirthday.plusYears(yearDif);
        int dayDif = airDateTime.getDayOfYear() - userBirthday.getDayOfYear();

        if ( -5 <= dayDif && dayDif <= 5){
            return 5;
        }
        return 0;
    }


}
