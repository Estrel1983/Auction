package com.auc.auction.service;

import com.auc.auction.model.Users;
import com.auc.auction.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsersService {
    @Autowired
    UsersRepository usersRepository;

    public List<Users> getAllUsers(){
        return usersRepository.findAll();
    }
    public Optional<Users> getUserById(Integer id){
        return usersRepository.findById(id);
    }
    public Users addUser(Users user){
        return usersRepository.save(user);
    }
    public void deleteUser(Integer id){
        usersRepository.deleteById(id);
    }
}
