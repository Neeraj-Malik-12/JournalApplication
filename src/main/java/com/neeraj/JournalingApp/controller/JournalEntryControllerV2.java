package com.neeraj.JournalingApp.controller;

import com.neeraj.JournalingApp.entity.JournalEntry;
import com.neeraj.JournalingApp.service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@RestController
    @RequestMapping("/journal")
    public class JournalEntryControllerV2 {

        @Autowired
        private JournalEntryService journalEntryService;

        @GetMapping("/entries")
        public List<JournalEntry> getAll(){
            return journalEntryService.getEntries();
        }

        @PostMapping("/add")
        public boolean saveEntries(@RequestBody JournalEntry entry){
            entry.setDate(LocalDateTime.now());
            journalEntryService.saveEntries(entry);
            return true;
        }

        //get entry by id
        @GetMapping("/id/{userId}")
        public JournalEntry entriesById(@PathVariable ObjectId userId){
            return journalEntryService.getEntryById(userId).orElse(null);
        }

        @DeleteMapping("/delete/id/{userId}")
        public void deleteEntries(@PathVariable ObjectId userId){
            journalEntryService.deleteById(userId);
        }

        @PutMapping("/update/id/{userId}")
            public void updateEntries(@PathVariable ObjectId userId, @RequestBody JournalEntry newEntry){
            journalEntryService.updateEntry(userId, newEntry);
        }


    }


