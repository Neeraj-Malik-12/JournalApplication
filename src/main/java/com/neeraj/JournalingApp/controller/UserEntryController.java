package com.neeraj.JournalingApp.controller;

import com.neeraj.JournalingApp.entity.UserEntry;
import com.neeraj.JournalingApp.service.UserEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
    @RequestMapping("/user")
    public class UserEntryController {

    @Autowired
    private UserEntryService userEntryService;


    //mappings

    //get All
    @GetMapping("/users")
    public List<UserEntry> getAllUsers(){
        return userEntryService.getAllUsers();
    }

    //get By ID
    @GetMapping("/id/{userId}")
    public UserEntry getUserById(@PathVariable ObjectId userId){
        return userEntryService.getUserById(userId).orElse(null);
    }

    //save user
    @PostMapping("/add_user")
    public UserEntry addNewUser(@RequestBody UserEntry newUser){
        userEntryService.saveUser(newUser);
        return newUser;
    }

    //update user
    @PutMapping("/update/{userId}")
    public boolean updateUser(@PathVariable ObjectId userId, @RequestBody UserEntry newUser){
        userEntryService.updateUser(userId, newUser);
        return true;
    }

    //delete user
    @DeleteMapping("/delete/{userId}")
        public boolean deleteUser(@PathVariable ObjectId userId){
            userEntryService.deleteUser(userId);
            return true;
        }


    }


