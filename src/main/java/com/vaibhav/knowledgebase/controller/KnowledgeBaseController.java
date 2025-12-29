package com.vaibhav.knowledgebase.controller;

import com.vaibhav.knowledgebase.entity.KnowledgeBase;
import com.vaibhav.knowledgebase.services.KnowledgeBaseService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import java.time.LocalDateTime;


@RestController
@RequestMapping("/base")
public class KnowledgeBaseController {

    private final KnowledgeBaseService knowledgeBaseService;

    public KnowledgeBaseController(KnowledgeBaseService knowledgeBaseService) {
        this.knowledgeBaseService = knowledgeBaseService;
    }

    @GetMapping
    public List<KnowledgeBase> getAll(){
        return knowledgeBaseService.getAll();
    }

    @PostMapping
    public KnowledgeBase createEntry(@Valid @RequestBody KnowledgeBase myEntry){
        myEntry.setCreatedAt(LocalDateTime.now());
        knowledgeBaseService.saveEntry(myEntry);
        return myEntry;
    }

    @GetMapping("/id/{myId}")
    public KnowledgeBase getEntryById(@PathVariable Long myId){
        return knowledgeBaseService.findById(myId).orElse(null);
    }

    @DeleteMapping("/id/{myId}")
    public KnowledgeBase deleteEntryById(@PathVariable Long myId){
        knowledgeBaseService.deleteById(myId);
        return null;
    }

    @PutMapping("/id/{myId}")
    public KnowledgeBase updateEntryById(@PathVariable Long myId, @RequestBody KnowledgeBase myEntry){
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
