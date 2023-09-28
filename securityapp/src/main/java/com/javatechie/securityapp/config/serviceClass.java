package com.javatechie.securityapp.config;

import com.javatechie.securityapp.Repositories.userRepo;
import com.javatechie.securityapp.models.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class serviceClass implements UserDetailsService {

    @Autowired
    private userRepo repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        System.out.println("In the loadUserByUserName");
       Optional<UserInfo> userInfo= repo.findByUsername(username);

//       try
//       {
//           if(userInfo.isEmpty())
//           {
//               System.out.println(userInfo);
//               throw new UsernameNotFoundException("User not found !!");
//           }
//
//       }
//       catch (Exception e){}
//
//        System.out.println("In the line 36 and serviceClass");
//       return userInfo.map(UserInfoUserDetails::new).get();

        userInfo.ifPresent(System.out::println);

        return userInfo.map(UserInfoUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("user not found " + username));

    }
}
