package com.onur.spring.config;

import com.onur.spring.db.DerbyDataSource;
import com.onur.spring.domain.Auditorium;
import com.epam.spring.implementation.*;
import com.epam.spring.service.*;
import com.onur.spring.implementation.AuditoriumImpl;
import com.onur.spring.implementation.DiscountImpl;
import com.onur.spring.service.AuditoriumService;
import com.onur.spring.service.DiscountService;
import com.onur.spring.strategy.BirthdayDiscountStrategy;
import com.onur.spring.strategy.DiscountStrategy;
import com.onur.spring.strategy.TenTicketStrategy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jdbc.core.JdbcTemplate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@ComponentScan({"com.epam.spring"})
@Configuration
@EnableAspectJAutoProxy
@PropertySource("classpath:auditorium.properties")
public class AppConfig {

  @Bean
  public DerbyDataSource getDataSource() {
    return new DerbyDataSource();
  }

  @Bean
  public JdbcTemplate getJdbcTemplate() {
    return new JdbcTemplate(getDataSource().dataSource());
  }

  @Bean
  public AuditoriumService getAuditoriumService() {
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
  public Auditorium getMercury() {
    return new Auditorium(name, Long.parseLong(numberOfSeats), vipSeats);
  }

  @Bean
  public Auditorium getMars() {
    return new Auditorium(name2, Long.parseLong(numberOfSeats2), vipSeats2);
  }

  @Bean
  public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
    return new PropertySourcesPlaceholderConfigurer();
  }
}
