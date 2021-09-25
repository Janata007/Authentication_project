package com.example.security;


import lombok.var;
import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

    @Override
    protected void configure(HttpSecurity http) throws Exception {
      /*
        http
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/css/**").permitAll()
                .antMatchers("/static/**").permitAll()
                .antMatchers("/", "/home").permitAll() //moze bilo koj tuka da pristapi
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .permitAll();

       */
        http
                .authorizeRequests()
                .antMatchers("/css/**").permitAll()
                .antMatchers("/static/**").permitAll()
                .antMatchers("/", "/home").permitAll()
                .antMatchers("/student/**").hasRole("USER")
                .antMatchers("/profesor/**").hasRole("ADMIN")
                .and().x509().subjectPrincipalRegex("CN=(.*?),")
                .userDetailsService(userDetailsService());

    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() {

        UserDetails user1 =
                User.withDefaultPasswordEncoder()
                        .username("181000")
                        .password("password")
                        .roles("USER")
                        .build();
        UserDetails user2 =
                User.withDefaultPasswordEncoder()
                        .username("181112")
                        .password("password")
                        .roles("USER")
                        .build();
        UserDetails user3 =
                User.withDefaultPasswordEncoder()
                        .username("Jana")
                        .password("password")
                        .roles("ADMIN")
                        .build();

       // return new InMemoryUserDetailsManager(user1,user2, user3);
        return username -> {
            if (username.equals("Jana")) {
                return new User(username, "",
                        AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_ADMIN"));
            }else{
                throw new UsernameNotFoundException("User:" + username + " not found");
            }
        };

    }

}