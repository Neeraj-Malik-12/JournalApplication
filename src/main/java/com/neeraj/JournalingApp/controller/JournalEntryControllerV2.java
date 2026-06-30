package com.neeraj.JournalingApp.controller;

import com.neeraj.JournalingApp.entity.JournalEntry;
import com.neeraj.JournalingApp.entity.UserEntry;
import com.neeraj.JournalingApp.service.JournalEntryService;
import com.neeraj.JournalingApp.service.UserEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@RestController
    @RequestMapping("/journal")
    public class JournalEntryControllerV2 {

        @Autowired
        private JournalEntryService journalEntryService;

        @Autowired
        private UserEntryService userEntryService;

        @GetMapping("all_entries")
        public ResponseEntity<?> getAll(){
            List<JournalEntry> everything = journalEntryService.getEntries();
            if(everything != null){
                return new ResponseEntity<>(everything, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        @GetMapping("/entries_of/{username}")
        public ResponseEntity<?> getAll(@PathVariable String username){
            UserEntry user = userEntryService.getUserByUsername(username);
            List<JournalEntry> all = user.getJournalEntries();
            if(all != null){
                return new ResponseEntity<>(all,HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        @PostMapping("/add_entries_for/{username}")
        public boolean saveEntries(@PathVariable String username, @RequestBody JournalEntry entry){
            entry.setDate(LocalDateTime.now());
            JournalEntry savedEntries = journalEntryService.saveEntries(entry);
            UserEntry user = userEntryService.getUserByUsername(username);
            user.getJournalEntries().add(savedEntries);
            userEntryService.saveUser(user);
            return true;
        }

        //get entry by id
        @GetMapping("/id/{userId}")
        public JournalEntry entriesById(@PathVariable ObjectId userId){
            return journalEntryService.getEntryById(userId).orElse(null);
        }

        @DeleteMapping("/delete_id/{userId}/from_{username}")
        public String deleteEntries(@PathVariable String username, @PathVariable ObjectId userId){
            UserEntry user = userEntryService.getUserByUsername(username);
            Optional<JournalEntry> entryToBeDeleted = journalEntryService.getEntryById(userId);
            boolean isdeleted = user.getJournalEntries().removeIf(x -> x.getId().equals(userId));
            userEntryService.saveUser(user);
            journalEntryService.deleteById(userId);
            if(isdeleted){
                return "Journal Deleted Successfully.";
            }
            return "Deletion not happen.";
        }

        @PutMapping("/update/id/{userId}/of_{username}")
            public void updateEntries(@PathVariable ObjectId userId, @RequestBody JournalEntry newEntry){
            journalEntryService.updateEntry(userId, newEntry);
        }


    }


