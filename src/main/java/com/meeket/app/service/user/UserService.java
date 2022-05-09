package com.meeket.app.service.user;


import com.meeket.app.models.user.Role;
import com.meeket.app.models.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface UserService {

    void test();

    User saveUser(User user);
    Role saveRole(Role role);
    List<String>getAllRole();
    void addRoleToUser(String username,String roleName);
    User getUser(String username);
    List<User> getUsers();
    User getUserByEmail(String email);
    User getUserById(Long id);
    User deleteUserById(Long id);
    User updateEmail(String newEmail,String oldEmail);
    User updateUserName(String newUserName,String oldUserName);
    User updatePasswordByEmail(String email,String password);
    boolean userExits(String email,String username);
    boolean userExitsByEmail(String email);
    boolean userExitsByUsername(String username);
    Page<User> findAll(Pageable pageable);
    boolean roleExits(String name);
}
