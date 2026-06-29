package com.neeraj.JournalingApp.controller;

import com.neeraj.JournalingApp.entity.JournalEntry;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


    @RestController
    @RequestMapping("/journal")
    public class JournalEntryController {

        private HashMap<Long, JournalEntry> journalEntries = new HashMap<>();
        // This hashmap will act as a temporary database

        @GetMapping("/entries")
        public List<JournalEntry> getAll(){
            return new ArrayList<>(journalEntries.values());
        }

        @PostMapping("/add")
        public boolean addEntries(@RequestBody JournalEntry entry){
            journalEntries.put(entry.getId(), entry);
            return true;
        }

        //get entry by id
        @GetMapping("/id/{myId}")
        public JournalEntry getJournalEntryById (@PathVariable Long myId){
            return journalEntries.get(myId);

        }

        @DeleteMapping("/delete/{myId}")
        public JournalEntry deleteJournalEntries(@PathVariable Long myId){
            return journalEntries.remove(myId);
        }

        @PutMapping("/update/id/{userID}")
        public boolean updateEntriesById(@PathVariable Long userID, @RequestBody JournalEntry entry){
            journalEntries.put(userID, entry);
            return true;
        }

    }


