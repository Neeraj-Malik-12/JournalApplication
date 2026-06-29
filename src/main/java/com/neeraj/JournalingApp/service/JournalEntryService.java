package com.neeraj.JournalingApp.service;

import com.neeraj.JournalingApp.entity.JournalEntry;
import com.neeraj.JournalingApp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    public void saveEntries(JournalEntry journalEntry){
        journalEntryRepository.save(journalEntry);
    }

    public List<JournalEntry> getEntries(){
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> getEntryById( ObjectId userId){
        return journalEntryRepository.findById(userId);
    }

    public void deleteById( ObjectId userId){
        journalEntryRepository.deleteById(userId);
    }

    public void updateEntry( ObjectId userId,  JournalEntry newEntry){
        JournalEntry oldEntry =  journalEntryRepository.findById(userId).orElse(null);

        if(oldEntry != null){
            oldEntry.setTitle((newEntry.getTitle() != null && !newEntry.getTitle().isBlank()) ? newEntry.getTitle() : oldEntry.getTitle());
            oldEntry.setContent((newEntry.getContent() != null && !newEntry.getContent().isBlank()) ? newEntry.getContent() : oldEntry.getContent());
        }
    }

}
