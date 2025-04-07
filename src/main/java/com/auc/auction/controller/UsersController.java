package com.auc.auction.controller;

import com.auc.auction.model.Users;
import com.auc.auction.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {
    @Autowired
    UsersService usersService;
    @GetMapping
    public List<Users> getAllUsers(){
        return usersService.getAllUsers();
    }

    @PostMapping
    public ResponseEntity<Void> addUser(@RequestBody Users user, UriComponentsBuilder ucb){
        Users savedUser = usersService.addUser(user);
        URI locationOfNewUser = ucb.path("/users/{id}").buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(locationOfNewUser).build();
    }
}
