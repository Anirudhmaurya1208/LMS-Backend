//package com.LMS.dto;
//
//import jakarta.validation.constraints.NotNull;
//import jakarta.validation.constraints.Size;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import java.util.List;
//
//import org.springframework.web.multipart.MultipartFile;
//
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
//public class CourseDTO {
//
//    @NotNull(message = "Course title cannot be null")
//    @Size(min = 1, max = 100, message = "Course title must be between 1 and 100 characters")
//    private String courseTitle;
//
//    @NotNull(message = "Course price cannot be null")
//    private Double coursePrice;
//
//    private Integer discount;
//
//    @NotNull(message = "Course image cannot be null")
//    private MultipartFile image; // 🆕 File upload
//
//    @NotNull(message = "Chapters cannot be null")
//    private String chapters; // Directly map JSON to a list of ChapterDTOs
// // 🆕 Will be a JSON string (sent from frontend)
//
//    private String educatorId;
//    
// // Parsed List (not part of request)
//    private List<ChapterDTO> parsedChapters;
//
//    // Getter & Setter
//    public List<ChapterDTO> getParsedChapters() {
//        return parsedChapters;
//    }
//    public void setParsedChapters(List<ChapterDTO> parsedChapters) {
//        this.parsedChapters = parsedChapters;
//    }
//
//}
package com.LMS.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonIgnore;

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
    private MultipartFile image;

    /**
     * JSON string from frontend containing list of chapters and lectures.
     * This field will be parsed manually in the controller.
     */
    @NotNull(message = "Chapters cannot be null")
    private String chapters;

    @NotNull(message = "Educator ID cannot be null")
    private String educatorId;

    /**
     * Parsed object from the JSON string `chapters`.
     * Not to be included in JSON response/request.
     */
    @JsonIgnore
    private List<ChapterDTO> parsedChapters;
}


