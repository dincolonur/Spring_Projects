package com.onur.spring.dao;

import com.onur.spring.domain.User;
import java.util.*;

public class UserDao {

    public static HashMap<Long, User> userMap = new HashMap<Long, User>();
    public static HashMap<Long, Integer> userDiscountMap = new HashMap<Long, Integer>();

    public boolean insertUser(User user){
        int size = userMap.size();
        userMap.put(user.getId(), user);

        if (userMap.size() == size + 1){
            return true;
        }
        return false;
    }

    public User getUserById(Long id){
        return userMap.get(id);
    }

    public User getUserByEmail(String email){
        for (Long id : userMap.keySet()){
            User user = userMap.get(id);
            if (user.getEmail().equalsIgnoreCase(email)){
                return user;
            }
        }
        return null;
    }

    public boolean removeUser(User user){
        int size = userMap.size();
        userMap.remove(user.getId());
        if (userMap.size() == size - 1){
            return true;
        }
        return false;
    }

    public Set<User> getAll(){
        Set<User> userSet = new HashSet<User>();
        for (Long id : userMap.keySet()){
            User user = userMap.get(id);
            userSet.add(user);
        }

        return userSet;
    }

    public void setUserDiscountMap(Long id){
        if (userDiscountMap.get(id) != null){
            userDiscountMap.put(id, userDiscountMap.get(id) + 1);
        } else {
            userDiscountMap.put(id, 1);
        }
    }

    public Integer getTotalUserDiscount(Long id){
        if (userDiscountMap.get(id) != null){
            return userDiscountMap.get(id);
        } else {
            return 0;
        }

    }
}
