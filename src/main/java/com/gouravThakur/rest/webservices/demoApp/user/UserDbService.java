package com.gouravThakur.rest.webservices.demoApp.user;


import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Component
public class UserDbService {

    private static int usersCount= 3;
    private static List<User> usersList = new ArrayList<>();

    static {
        usersList.add(new User(1,"alex",new Date()));
        usersList.add(new User(2,"adam",new Date()));
        usersList.add(new User(3,"ben",new Date()));
        usersList.add(new User(4,"james",new Date()));
        usersList.add(new User(5,"henry",new Date()));
        usersList.add(new User(6,"tom",new Date()));
    }


    public List<User> findAllUser() {
        return usersList;
    }


    public User findOneUser(int id){
        for (User user : usersList) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;

    }

    public static User saveUser(User user) {
        if (user.getId() == null) {
        user.setId(++usersCount);
    }
		usersList.add(user);
		return user;
    }


    public User deleteById(int id){
        Iterator<User> iterator = usersList.iterator();

        while(iterator.hasNext()) {
            User users = iterator.next();
            if (users.getId() == id) {
                iterator.remove();
                return users;
            }
        }
        return null;

    }
}
