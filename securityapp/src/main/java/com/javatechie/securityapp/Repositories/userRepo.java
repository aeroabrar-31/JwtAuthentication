package com.javatechie.securityapp.Repositories;

import com.javatechie.securityapp.models.UserInfo;
//import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface userRepo extends MongoRepository<UserInfo,String> {

    Optional<UserInfo> findByUsername(String username);
}
