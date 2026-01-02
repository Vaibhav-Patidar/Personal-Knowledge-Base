package com.vaibhav.knowledgebase.controller;

import com.vaibhav.knowledgebase.entity.KnowledgeBase;
import com.vaibhav.knowledgebase.services.KnowledgeBaseService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import java.time.LocalDateTime;


@RestController
@RequestMapping("/user")
public class KnowledgeBaseController {

    private final KnowledgeBaseService knowledgeBaseService;

    public KnowledgeBaseController(KnowledgeBaseService knowledgeBaseService) {
        this.knowledgeBaseService = knowledgeBaseService;
    }

    @PostMapping("/{userId}/knowledge-base")
    public KnowledgeBase createEntry(@Valid @RequestBody KnowledgeBase myEntry, @PathVariable long userId) {
        myEntry.setCreatedAt(LocalDateTime.now());
        knowledgeBaseService.saveEntry(myEntry, userId);
        return myEntry;
    }

    @GetMapping("/{userId}/knowledge-base")
    public List<KnowledgeBase> getAll(
            @PathVariable long userId) {
        return knowledgeBaseService.getByUser(userId);
    }

    @DeleteMapping("/{userId}/knowledge-base/{id}")
    public void delete(@PathVariable long userId, @PathVariable Long id) {
        knowledgeBaseService.deleteById(id, userId);
    }

    @PutMapping("/{userId}/knowledge-base/{id}")
    public KnowledgeBase updateEntryById(@PathVariable long userId, @PathVariable Long id, @RequestBody KnowledgeBase myEntry) {
        return knowledgeBaseService.updateEntry(id, userId, myEntry);
    }
}
