package com.meeket.app.helper;


import com.meeket.app.models.user.User;
import com.meeket.app.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class UserLogged implements IUserLogged {

    @Autowired
    UserRepository userRepository;



    @Override
    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public String getUsername(){

        String  userName = SecurityContextHolder.getContext().getAuthentication().getName();

        User user = userRepository.findByUsername(userName);
        long userid=0;
        if(!Objects.isNull(user)){
            userid = user.getId();
            System.out.println("Tuan anh " +userid);
        }

        return userName;
    }

    public Long getId(){

        String  userLogged = SecurityContextHolder.getContext().getAuthentication().getName();

        User user = userRepository.findByUsername(userLogged);
        long userid=0;
        if(!Objects.isNull(user)){
            userid = user.getId();
            System.out.println("Tuan anh " +userid);
        }

        return userid;
    }

    public String getEmail(){

        String  userLogged = SecurityContextHolder.getContext().getAuthentication().getName();

        User user = userRepository.findByUsername(userLogged);
        String email = null;
        if(!Objects.isNull(user)){
            email = user.getEmail();
        }

        return email;
    }

    public String getPassword(){
        String  userLogged = SecurityContextHolder.getContext().getAuthentication().getName();

        User user = userRepository.findByUsername(userLogged);
        String password = null;
        if(!Objects.isNull(user)){
            password = user.getPassword();
        }

        return password;
    }

    @Override
    public User getUser() {
        String  userLogged = SecurityContextHolder.getContext().getAuthentication().getName();

        User user = userRepository.findByUsername(userLogged);

        if(!Objects.isNull(user)){
           return user;
        }
        return  null;
    }

    @Override
    public boolean getActive() {

        String  userLogged = SecurityContextHolder.getContext().getAuthentication().getName();

        User user = userRepository.findByUsername(userLogged);

        if(!Objects.isNull(user)){
            return user.isActive();
        }

        return false;
    }

}
