package com.LMS.dto;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LectureDTO {

    @NotNull(message = "Lecture title cannot be null")
    @Size(min = 1, max = 100, message = "Lecture title must be between 1 and 100 characters")
    private String lectureTitle;

    @NotNull(message = "Lecture duration cannot be null")
    private String lectureDuration;  // Duration format can be in String (e.g., "30m")

    @NotNull(message = "Lecture URL cannot be null")
    private String lectureUrl;

    private boolean isPreviewFree;  // Indicates if the lecture preview is free

    @NotNull(message = "Lecture order cannot be null")
    private int lectureOrder;  // Order in the chapter for display
}
