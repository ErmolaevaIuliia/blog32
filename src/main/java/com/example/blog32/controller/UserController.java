package com.example.blog32.controller;

import com.example.blog32.entity.ArticleEntity;
import com.example.blog32.entity.UserEntity;
import com.example.blog32.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepo userRepo;

    @PostMapping("/addUser")
    public String addUser (@ModelAttribute UserEntity user){
        userRepo.save(user);
        return "OK";
    }

    @GetMapping("/getUser")
    public ResponseEntity getUser(){
        ArrayList<UserEntity> users = (ArrayList<UserEntity>) userRepo.findAll();
        return ResponseEntity.ok(users);
    }
}
