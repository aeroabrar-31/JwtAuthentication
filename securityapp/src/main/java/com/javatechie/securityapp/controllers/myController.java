package com.javatechie.securityapp.controllers;

import com.javatechie.securityapp.Repositories.userRepo;
import com.javatechie.securityapp.models.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class myController {

    @Autowired
    public userRepo repo;


    @Autowired
    public PasswordEncoder passwordEncoder;

    @GetMapping("/welcome")
    public String Welcomepage()
    {
        return "Hello !";
    }

    @GetMapping("/products/getall")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> getAll()
    {

        return ResponseEntity.ok("All products, accessed to only ADMIN");
    }

    @GetMapping("/products/getbyid/{id}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String getByid(@PathVariable int id)
    {
        return "Only user  allowed here ! \nProduct id - "+id;
    }

    @GetMapping("/products/hradmin")
    @PreAuthorize("hasAnyRole('ROLE_HR','ROLE_ADMIN')")
//    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> onlyhradmin()
    {
        return ResponseEntity.ok("Only HR and ADMIN allowed here !");
    }


    @PostMapping("/adduser")
    public ResponseEntity<?> addUser(@RequestBody UserInfo user)
    {
//        user.setPassword(ByCrypt().encode(user.getPassword()));

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        repo.save(user);

        return ResponseEntity.ok(user+" \nadded Succesfully !!!");
    }





}
