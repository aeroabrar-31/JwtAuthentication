package com.javatechie.securityapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class SecurityappApplication {

	public static void main(String[] args) {

		BCryptPasswordEncoder ps=new BCryptPasswordEncoder();
		String a=ps.encode("Abrar");
		System.out.println(a);
		System.out.println(a.length()+" on main");
//		System.out.println();


		SpringApplication.run(SecurityappApplication.class, args);
	}

}
