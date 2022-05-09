package com.meeket.app.helper;

import com.meeket.app.models.user.User;
import org.springframework.security.core.Authentication;

public interface IUserLogged {
    Authentication getAuthentication();
    String getUsername();
    Long getId();
    String getEmail();
    String getPassword();
    User getUser();
    boolean getActive();
}
