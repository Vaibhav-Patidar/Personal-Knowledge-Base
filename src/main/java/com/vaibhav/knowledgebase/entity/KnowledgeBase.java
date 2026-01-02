package com.vaibhav.knowledgebase.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDateTime;

@Entity
@Table(name = "knowledge_base")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class KnowledgeBase {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private User user;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotBlank(message = "Topic must not be empty")
    @Column(nullable = false)
    private String topic;

    @NotBlank(message = "Explanation must not be empty")
    @Column(nullable = false, columnDefinition = "TEXT")
    private String explanation;

    @Column(columnDefinition = "TEXT")
    private String example;

    @NotBlank(message = "Category must not be empty")
    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
