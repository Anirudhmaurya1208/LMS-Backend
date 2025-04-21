package com.LMS.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChapterDTO {

    @NotNull(message = "Chapter title cannot be null")
    @Size(min = 1, max = 100, message = "Chapter title must be between 1 and 100 characters")
    private String chapterTitle;

    @NotNull(message = "Chapter order cannot be null")
    private Integer chapterOrder;

    @NotNull(message = "Chapter content cannot be null")
    private List<LectureDTO> chapterContent = new ArrayList<>();  // List of lectures in the chapter, initialized to avoid null pointer exception
}
