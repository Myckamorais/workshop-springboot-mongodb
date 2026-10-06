package com.myckamorais.workshop_springboot_mongodb.resources;

import com.myckamorais.workshop_springboot_mongodb.domain.Post;
import com.myckamorais.workshop_springboot_mongodb.domain.User;
import com.myckamorais.workshop_springboot_mongodb.dto.AuthorDto;
import com.myckamorais.workshop_springboot_mongodb.dto.UserDTO;
import com.myckamorais.workshop_springboot_mongodb.services.PostService;
import com.myckamorais.workshop_springboot_mongodb.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/posts")
public class PostResource {

    @Autowired
    private PostService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Post> findById(@PathVariable String id) {
        Post obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }
}
