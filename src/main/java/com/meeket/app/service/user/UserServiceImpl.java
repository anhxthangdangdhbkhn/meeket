package com.meeket.app.service.user;


import com.meeket.app.models.user.Role;
import com.meeket.app.models.user.User;
import com.meeket.app.repository.user.RoleRepository;
import com.meeket.app.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;


@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {

    private  final UserRepository userRepository;
    private  final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username);

        if(user == null){
            log.error("User not found in the database");
            throw new UsernameNotFoundException("User not found in the database");
        }else{
            log.info("User found in the database: {}",username);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> {
            System.out.println(role.getName());
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        System.out.println("Author ok " );
        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),authorities);
    }


    @Override
    public void test() {
        System.out.println("Void test");
    }

    @Override
    public User saveUser(User user) {
        log.info("Saving new user {} to the database",user.getEmail());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public Role saveRole(Role role) {
        log.info("Saving new role  {} to the database",role.getName());
        return roleRepository.save(role);
    }

    @Override
    public List<String> getAllRole() {
        log.info("Get all role from database");
        return roleRepository.getAllRole();
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        log.info("Adding role {} to user {}",roleName,username);
        User user = userRepository.getUserByName(username);
        Role role= roleRepository.getRoleByName(roleName);
        System.out.println("Role " + role.toString());



            user.getRoles().add(role);



    }

    @Override
    public User getUser(String username) {
        log.info("Fetching user",username);
        return userRepository.findByUsername(username);
    }

    @Override
    public List<User> getUsers() {
        log.info("Fetching all users");
        return userRepository.findAll();
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.getUserByEmail(email);
    }

    @Override
    public User getUserById(Long id) {
        log.info("Get user at id ",id);
        return userRepository.getUserById(id);
    }

    @Override
    public User deleteUserById(Long id) {
        log.info("delete user at id ",id);
        return userRepository.deleteUserById(id);
    }

    @Override
    public User updateEmail(String newEmail, String oldEmail) {
        log.info("update email for " + oldEmail);
        User user  = userRepository.updateEmail(newEmail,oldEmail);
        if(Objects.isNull(user)){
            return  null;
        }
        return user;
    }



    @Override
    public User updateUserName(String newUserName, String oldUserName) {
        User user = userRepository.updateUserName(newUserName,oldUserName);

        if(Objects.isNull(user)){
            return  null;
        }
        return user;
    }

    @Override
    public User updatePasswordByEmail(String email, String password) {
        User user = userRepository.updatePasswordByEmail(email,password);

        if(Objects.isNull(user)){
            return  null;
        }
        return user;
    }

    @Override
    public boolean userExits(String email, String username) {
        User user = userRepository.userExits(email,username);
        if(Objects.isNull(user)){
            log.info("User not exist email {} username {}",email,username);
            return false;
        }else return true;
    }

    @Override
    public boolean userExitsByEmail(String email) {
        User user = userRepository.userExitsByEmail(email);
        if(Objects.isNull(user)){
            log.info("User not exist email {}",email);
            return false;
        }else return true;
    }

    @Override
    public boolean userExitsByUsername(String username) {
        User user = userRepository.userExitsByUsername(username);
        if(Objects.isNull(user)){
            log.info("User not exist username {}",username);
            return false;
        }else return true;
    }

    @Override
    public boolean roleExits(String name) {
       Role role = roleRepository.roleExits(name);
        if(!Objects.isNull(role)){
            log.info("Role not exist {}",name);
            return false;
        }else return true;
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        log.info("get page user ");
        return  userRepository.findAll(pageable);
    }



}
