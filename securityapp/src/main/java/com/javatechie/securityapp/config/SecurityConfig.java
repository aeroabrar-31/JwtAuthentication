package com.javatechie.securityapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig  {


    //authentication
    @Bean
    public UserDetailsService userDetailsService()
    {


//        UserDetails admin = User
//                .withUsername("Abrar")
//                .password(getPswd().encode("p1"))
//                .roles("ADMIN")
//                .build();
//
//        UserDetails normaluser=User
//                .withUsername("Kalam")
//                .password(getPswd().encode("p2"))
//                .roles("USER")
//                .build();
//
//
//        return new InMemoryUserDetailsManager(admin,normaluser);

        return new serviceClass();


    }

    //Authorization

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

         http.csrf( c->c.disable())

                .authorizeHttpRequests( a-> a.requestMatchers("/api/welcome","/api/adduser").permitAll())


                .authorizeHttpRequests( a-> a.requestMatchers("/api/products/**").authenticated())

                 .httpBasic(Customizer.withDefaults());

//                 .formLogin(a->a.permitAll());

         return http.build();


    }

//    return http.csrf()
//            .disable()
//               .authorizeHttpRequests()
//               .requestMatchers("/api/welcome")
//               .permitAll()
//               .and()
//
//               .authorizeHttpRequests()
//               .requestMatchers("/api/products/**")
//               .authenticated()
//               .and()
//               .formLogin()
//               .and()
//               .build();


//
//    return http.csrf( c->c.disable())
//
//            .authorizeHttpRequests( a-> a.requestMatchers("/api/welcome").permitAll())
//
//
//            .authorizeHttpRequests( a-> a.requestMatchers("/api/products/**").authenticated())
//
//
//            .formLogin(a-> a.build());
//
    @Bean
    public PasswordEncoder getPswd()
    {
        return new BCryptPasswordEncoder();
    }


    @Autowired
    public UserDetailsService s;

    @Bean
    public AuthenticationProvider get()
    {
         DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
         authenticationProvider.setUserDetailsService(s);
         authenticationProvider.setPasswordEncoder(getPswd());

         return authenticationProvider;
    }


}
