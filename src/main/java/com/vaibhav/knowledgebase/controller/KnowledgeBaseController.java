package com.vaibhav.knowledgebase.controller;

import com.vaibhav.knowledgebase.entity.KnowledgeBase;
import com.vaibhav.knowledgebase.services.KnowledgeBaseService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;


@RestController
@RequestMapping("/base")
public class KnowledgeBaseController {

    @Autowired
    private KnowledgeBaseService knowledgeBaseService;

    @GetMapping
    public List<KnowledgeBase> getAll(){
        return knowledgeBaseService.getAll();
    }

    @PostMapping
    public KnowledgeBase createEntry(@RequestBody KnowledgeBase myEntry){
        myEntry.setCreatedAt(LocalDateTime.now());
        knowledgeBaseService.saveEntry(myEntry);
        return myEntry;
    }

    @GetMapping("/id/{myId}")
    public KnowledgeBase getEntryById(@PathVariable ObjectId myId){
        return knowledgeBaseService.findById(myId).orElse(null);
    }

    @DeleteMapping("/id/{myId}")
    public KnowledgeBase deleteEntryById(@PathVariable ObjectId myId){
        knowledgeBaseService.deleteById(myId);
        return null;
    }

    @PutMapping("/id/{myId}")
    public KnowledgeBase updateEntryById(@PathVariable ObjectId myId, @RequestBody KnowledgeBase myEntry){
        KnowledgeBase old = knowledgeBaseService.findById(myId).orElse(null);
        if (old != null) {
            if (myEntry.getTopic() != null && !myEntry.getTopic().isEmpty()) {
                old.setTopic(myEntry.getTopic());
            }
            if (myEntry.getExplanation() != null && !myEntry.getExplanation().isEmpty()) {
                old.setExplanation(myEntry.getExplanation());
            }
            if (myEntry.getExample() != null && !myEntry.getExample().isEmpty()) {
                old.setExample(myEntry.getExample());
            }
            if (myEntry.getCategory() != null && !myEntry.getCategory().isEmpty()) {
                old.setCategory(myEntry.getCategory());
            }

            old.setUpdatedAt(LocalDateTime.now());
        }

        knowledgeBaseService.saveEntry(old);
        return old;
    }
}
