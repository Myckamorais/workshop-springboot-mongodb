package com.myckamorais.workshop_springboot_mongodb.repository;

import com.myckamorais.workshop_springboot_mongodb.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User,String> {


}
