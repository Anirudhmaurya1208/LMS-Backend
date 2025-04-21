package com.LMS.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseDTO {

    @NotNull(message = "Course title cannot be null")
    @Size(min = 1, max = 100, message = "Course title must be between 1 and 100 characters")
    private String courseTitle;

    @NotNull(message = "Course price cannot be null")
    private Double coursePrice;

    private Integer discount;

    @NotNull(message = "Course image cannot be null")
    private MultipartFile image; // 🆕 File upload

    @NotNull(message = "Chapters cannot be null")
    private List<ChapterDTO> chapters; // Directly map JSON to a list of ChapterDTOs
 // 🆕 Will be a JSON string (sent from frontend)

    private String educatorId;
}
