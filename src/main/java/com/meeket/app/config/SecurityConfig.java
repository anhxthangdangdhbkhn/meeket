package com.meeket.app.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
       http.csrf().disable();
       http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
       http.authorizeRequests().anyRequest().authenticated().and().httpBasic();
 //      http.authorizeRequests().antMatchers(HttpMethod.POST,"/login").permitAll();
//       http.authorizeRequests().antMatchers(HttpMethod.GET,"/v1/api/users").hasAnyAuthority("ROLE_SUPER_ADMIN","ROLE_USER","ROLE_MANAGER");
//       http.authorizeRequests().antMatchers(HttpMethod.GET,"/v1/api/users/{userId}").permitAll();
//       http.authorizeRequests().antMatchers(HttpMethod.GET,"/v1/api/users/pages/{pageId}").permitAll();
//       http.authorizeRequests().antMatchers(HttpMethod.POST,"/v1/api/users").permitAll();
//       http.authorizeRequests().antMatchers(HttpMethod.DELETE,"/v1/api/users/{userId}").hasAnyAuthority("ROLE_SUPER_ADMIN","ROLE_ADMIN","ROLE_MANAGER");
//       http.authorizeRequests().antMatchers(HttpMethod.PUT,"/v1/api/users/email").hasAnyAuthority("ROLE_USER");
//       http.authorizeRequests().antMatchers(HttpMethod.PUT,"/v1/api/users/block/{userId}").hasAnyAuthority("ROLE_SUPER_ADMIN","ROLE_ADMIN","ROLE_MANAGER");
//       http.addFilter(new CustomAuthenticationFilter(authenticationManagerBean()));
//       http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
