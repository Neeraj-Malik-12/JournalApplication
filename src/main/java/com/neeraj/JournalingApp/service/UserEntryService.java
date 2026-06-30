package com.neeraj.JournalingApp.service;

import com.neeraj.JournalingApp.entity.UserEntry;
import com.neeraj.JournalingApp.repository.UserEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserEntryService {

    @Autowired
    private UserEntryRepository userEntryRepository;

    public List<UserEntry> getAllUsers(){
        return userEntryRepository.findAll();
    }

    public Optional<UserEntry> getUserById(ObjectId userId){
        return userEntryRepository.findById(userId);
    }

    public UserEntry getUserByUsername(String username){
        return userEntryRepository.findByUsername(username).orElse(null);
    }

    public void saveUser(UserEntry newUser){
        userEntryRepository.save(newUser);
    }

    public void updateUser(ObjectId userId, UserEntry newUser){
        UserEntry oldUser = userEntryRepository.findById(userId).orElse(null);
        if(oldUser != null){
            oldUser.setUsername(!(newUser.getUsername().isBlank()) ? newUser.getUsername() : oldUser.getUsername());
            oldUser.setPassword(!(newUser.getPassword().isBlank()) ? newUser.getPassword() : oldUser.getPassword());
        }
        userEntryRepository.save(oldUser);
    }

    public void deleteUser(ObjectId userId){
        userEntryRepository.deleteById(userId);
    }
}
