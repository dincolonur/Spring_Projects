package com.onur.spring.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Random;

@Aspect
@Component
public class LuckyWinnerAspect {

    @Around("execution(* BookingImpl.getTicketsPrice(..))")
    public double aroundGetTicketsPrice(ProceedingJoinPoint joinpoint){
        double ticketPrice = 0.0;
        try {
            boolean lucky = checkLucky();
            System.out.println("This user is lucky?: " + lucky);
            if(!lucky){
                ticketPrice=(double)joinpoint.proceed();
            }
        }catch (Throwable e){
            e.printStackTrace();
        }
        return ticketPrice;
    }


    public boolean checkLucky(){
        Random rand = new Random();
        int number = rand.nextInt(500);
        if (number % 2 == 0){
            return true;
        }
        return false;
    }
}
