package com.LMS.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.List;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String courseTitle;

    @NotNull
    private Double coursePrice;

    private Integer discount;

    private String image;  // URL or file path for course image

    @OneToMany(mappedBy = "course", cascade = CascadeType.PERSIST, orphanRemoval = true)
    @JsonManagedReference
    private List<Chapter> chapters;

    @ManyToOne
    @JoinColumn(name = "educator_id")
    private Educator educator;  // The educator who teaches the course
}
