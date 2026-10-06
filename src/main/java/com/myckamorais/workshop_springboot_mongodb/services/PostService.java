package com.myckamorais.workshop_springboot_mongodb.services;

import com.myckamorais.workshop_springboot_mongodb.domain.Post;
import com.myckamorais.workshop_springboot_mongodb.domain.User;
import com.myckamorais.workshop_springboot_mongodb.dto.UserDTO;
import com.myckamorais.workshop_springboot_mongodb.repository.PostRepository;
import com.myckamorais.workshop_springboot_mongodb.repository.UserRepository;
import com.myckamorais.workshop_springboot_mongodb.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository repo;

    public Post findById(String id) {
        Optional<Post> posts = repo.findById(id);
        return posts.orElseThrow(() -> new ObjectNotFoundException("Object not found"));
    }
}
