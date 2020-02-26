package com.onur.spring.implementation;

import com.onur.spring.domain.Event;
import com.onur.spring.domain.User;
import com.onur.spring.service.DiscountService;
import com.onur.spring.strategy.DiscountStrategy;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class DiscountImpl implements DiscountService {

  @Autowired(required = false)
  public ArrayList<DiscountStrategy> discountStrategies;

  public DiscountImpl(ArrayList<DiscountStrategy> discountStrategies) {
    this.discountStrategies = discountStrategies;
  }

  public void setDiscountStrategies(ArrayList<DiscountStrategy> discountStrategies) {
    this.discountStrategies = discountStrategies;
  }

  @Override
  public byte getDiscount(User user, Event event, LocalDateTime airDateTime, long numberOfTickets) {
    byte totalDiscount = 0;
    for (DiscountStrategy discountStrategy : discountStrategies) {
      byte discount = discountStrategy.calculateDiscount(user, event, airDateTime, numberOfTickets);
      if (discount >= totalDiscount) {
        totalDiscount = discount;
      }
    }
    return totalDiscount;
  }
}
