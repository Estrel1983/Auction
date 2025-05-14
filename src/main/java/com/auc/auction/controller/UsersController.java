package com.auc.auction.controller;

import com.auc.auction.model.Users;
import com.auc.auction.service.UsersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/users")
public class UsersController {
    @Autowired
    UsersService usersService;
    @GetMapping
    public List<Users> getAllUsers(){
        return usersService.getAllUsers();
    }

    @GetMapping
    @RequestMapping("/{id}")
    public Users getUser(@PathVariable Integer id){
        return  usersService.getUserById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,
                "User with id = " + id + " not found"));
    }

    @PostMapping
    public ResponseEntity<Void> addUser(@RequestBody Users user, UriComponentsBuilder ucb){
        Users savedUser = usersService.addUser(user);
        log.info(savedUser.getId().toString());
        URI locationOfNewUser = ucb.path("/users/{id}").buildAndExpand(savedUser.getId()).toUri();
        log.info(locationOfNewUser.getPath());
        return ResponseEntity.created(locationOfNewUser).build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id){
        if(usersService.getUserById(id).isPresent()) {
            usersService.deleteUser(id);
            return ResponseEntity.noContent().build();
        }
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                "User with id = " + id + " not found");
    }
}
