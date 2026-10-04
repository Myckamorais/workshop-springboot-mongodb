package com.myckamorais.workshop_springboot_mongodb.services;

import com.myckamorais.workshop_springboot_mongodb.domain.User;
import com.myckamorais.workshop_springboot_mongodb.repository.UserRepository;
import com.myckamorais.workshop_springboot_mongodb.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repo;

    public List<User> findAll(){
        return repo.findAll();
    }

    public User findById(String id) {
        Optional<User> user = repo.findById(id);
        return user.orElseThrow(() -> new ObjectNotFoundException("Object not found"));
    }
}
