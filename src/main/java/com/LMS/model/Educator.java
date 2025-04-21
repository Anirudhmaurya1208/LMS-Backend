package com.LMS.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Educator {

    @Id
    private String id;

    @NotNull
    private String name;

    @NotNull
    private String email;

    private String imageUrl;

    @OneToMany(mappedBy = "educator", cascade = CascadeType.ALL)
    private List<Course> courses;
}
