package com.auc.auction.service;

import com.auc.auction.model.Users;
import com.auc.auction.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersService {
    @Autowired
    UsersRepository usersRepository;

    public List<Users> getAllUsers(){
        return usersRepository.findAll();
    }
    public Users addUser(Users user){
        return usersRepository.save(user);
    }}
