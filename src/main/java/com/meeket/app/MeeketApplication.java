package com.meeket.app;

import com.meeket.app.models.user.Role;
import com.meeket.app.models.user.User;
import com.meeket.app.service.user.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class MeeketApplication {

	public static void main(String[] args) {
		SpringApplication.run(MeeketApplication.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder(){
		return  new BCryptPasswordEncoder();
	}

	@Bean
	CommandLineRunner run(UserService userService){
		return args -> {
//			userService.saveRole(new Role(null,"ROLE_USER"));
//			userService.saveRole(new Role(null,"ROLE_MANAGER"));
//			userService.saveRole(new Role(null,"ROLE_ADMIN"));
//			userService.saveRole(new Role(null,"ROLE_SUPER_ADMIN"));

//			userService.saveUser(new User(null,"anhxthangdang","thang","26071993",true,new ArrayList<>()));
//			userService.saveUser(new User(null,"admin","admin","26071993",true,new ArrayList<>()));
//			userService.saveUser(new User(null,"tiendung","dung@gmail.com","26071993",true,new ArrayList<>()));
//
//			userService.addRoleToUser("admin","ROLE_SUPER_ADMIN");
//			userService.addRoleToUser("anhxthangdang","ROLE_ADMIN");
//			userService.addRoleToUser("tiendung","ROLE_USER");


		};

	}

}
