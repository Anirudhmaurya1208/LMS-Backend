package com.LMS.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;


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

//    private Multipartfile image;  // URL or file path for course image
    @Lob
    @Column(name = "image", columnDefinition = "LONGBLOB")
    private byte[] image;

//    @OneToMany(mappedBy = "course", cascade = CascadeType.PERSIST, orphanRemoval = true)
//    @JsonManagedReference
//    private List<Chapter> chapters;
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Chapter> chapters = new ArrayList<>();


    @ManyToOne
    @JoinColumn(name = "educator_id")
    private Educator educator;  // The educator who teaches the course
}
