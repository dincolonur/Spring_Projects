package com.onur.spring.config;

import com.onur.spring.aspects.CounterAspect;
import com.onur.spring.aspects.DiscountAspect;
import com.onur.spring.aspects.LuckyWinnerAspect;
import com.onur.spring.cli.BookingOptions;
import com.onur.spring.cli.EventOptions;
import com.onur.spring.cli.UserOptions;
import com.onur.spring.domain.Auditorium;
import com.epam.spring.implementation.*;
import com.epam.spring.service.*;
import com.onur.spring.setup.Loader;
import com.onur.spring.strategy.BirthdayDiscountStrategy;
import com.onur.spring.strategy.DiscountStrategy;
import com.onur.spring.strategy.TenTicketStrategy;
import com.onur.spring.implementation.*;
import com.onur.spring.service.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Configuration
@EnableAspectJAutoProxy
@PropertySource("classpath:auditorium.properties")
public class AppConfig {

    @Bean
    public CounterAspect getCounterAspect(){
        return new CounterAspect();
    }

    @Bean
    public DiscountAspect getDiscountAspect(){
        return new DiscountAspect();
    }

    @Bean
    public LuckyWinnerAspect getLuckyWinnerAspect(){
        return new LuckyWinnerAspect();
    }

    @Bean
    public Loader getLoader(){
        return new Loader();
    }

    @Bean
    public UserService getUserService(){
        return new UserImpl();
    }

    @Bean
    public EventService getEventService(){
        return new EventImpl();
    }

    @Bean
    public BookingService getBookingService(){
        return new BookingImpl();
    }


    @Bean
    public AuditoriumService getAuditoriumService(){
        Set<Auditorium> auditoriumSet = new HashSet<>();
        auditoriumSet.add(getMercury());
        auditoriumSet.add(getMars());
        return new AuditoriumImpl(auditoriumSet);
    }

    @Bean
    public DiscountStrategy getBirthdayDiscount() {
        return new BirthdayDiscountStrategy();
    }

    @Bean
    public DiscountStrategy getTenTicketDiscount() {
        return new TenTicketStrategy();
    }

    @Bean
    public DiscountService getDiscountImpl() {
        ArrayList<DiscountStrategy> discountSet = new ArrayList<DiscountStrategy>();
        discountSet.add(getBirthdayDiscount());
        discountSet.add(getTenTicketDiscount());
        return new DiscountImpl(discountSet);
    }

    @Bean
    public UserOptions getUserOptions(){
        return new UserOptions();
    }

    @Bean
    public EventOptions getEventOptions(){
        return new EventOptions();
    }

    @Bean
    public BookingOptions getBookingOptions(){
        return new BookingOptions();
    }

    @Value("${name}")
    private String name;

    @Value("${name2}")
    private String name2;

    @Value("${numberOfSeats}")
    private String numberOfSeats;

    @Value("${numberOfSeats2}")
    private String numberOfSeats2;

    @Value("${vipSeats}")
    private String vipSeats;

    @Value("${vipSeats2}")
    private String vipSeats2;

    @Bean
    public Auditorium getMercury(){
        return new Auditorium(name, Long.parseLong(numberOfSeats), vipSeats);
    }

    @Bean
    public Auditorium getMars(){
        return new Auditorium(name2, Long.parseLong(numberOfSeats2), vipSeats2);
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
