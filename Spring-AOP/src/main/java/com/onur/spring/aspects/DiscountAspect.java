package com.onur.spring.aspects;

import com.onur.spring.dao.UserDao;
import com.onur.spring.domain.User;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DiscountAspect {

    @Pointcut("execution(* DiscountImpl.getDiscount(..))")
    private void getDiscount(){}

    @AfterReturning(pointcut = "getDiscount() && args(user,..)", returning = "retVal")
    public void afterSuccessfulReturn(JoinPoint joinPoint, User user, Object retVal) {
        UserDao userDao = new UserDao();
        userDao.setUserDiscountMap(user.getId());
    }

}
