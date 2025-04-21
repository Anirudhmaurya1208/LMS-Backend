package com.LMS.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Lecture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String lectureTitle;

    private String lectureDuration;  // Duration in a String format (e.g., "30m")

    @NotNull
    private String lectureUrl;

    private Boolean isPreviewFree;  // Flag indicating if the lecture preview is free

    private Integer lectureOrder;  // The order in the chapter

    @ManyToOne
    @JoinColumn(name = "chapter_id")
    @JsonBackReference(value = "lectureList")  // Prevents infinite recursion during serialization
    private Chapter chapter;  // The chapter to which the lecture belongs
}
