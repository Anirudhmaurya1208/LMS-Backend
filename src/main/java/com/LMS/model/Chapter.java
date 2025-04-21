package com.LMS.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.ArrayList;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Chapter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String chapterTitle;

    @NotNull
    private Integer chapterOrder;

    @ManyToOne
    @JoinColumn(name = "course_id")
    @JsonBackReference  // Prevents infinite recursion when serializing
    private Course course;

    @OneToMany(mappedBy = "chapter", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "lectureList")  // Manages the relationship between chapters and lectures
    private List<Lecture> lectures = new ArrayList<>();  // Initialize the list to avoid null pointer exceptions
}
