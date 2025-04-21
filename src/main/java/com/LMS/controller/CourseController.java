package com.LMS.controller;

import com.LMS.dto.ChapterDTO;
import com.LMS.dto.CourseDTO;
import com.LMS.model.Course;
import com.LMS.service.CourseService;

import com.fasterxml.jackson.core.type.TypeReference;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.awt.PageAttributes.MediaType;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173") // Allow frontend to call
public class CourseController {

    private final CourseService courseService;
    private final ObjectMapper objectMapper; //

//    @PostMapping
//    public ResponseEntity<?> addCourse(@Valid @RequestBody CourseDTO courseDTO, BindingResult result) {
//        if (result.hasErrors()) {
//            // Collect and return the error messages
//            String errorMessages = result.getAllErrors().stream()
//                .map(error -> ((FieldError) error).getField() + ": " + error.getDefaultMessage())
//                .collect(Collectors.joining(", "));
//            return ResponseEntity.badRequest().body(errorMessages);  // Return 400 with error messages
//        }
//        
//        Course savedCourse = courseService.saveCourse(courseDTO);
//        return ResponseEntity.status(201).body(savedCourse);  // 201 Created status for resource creation
//    }
//    @PostMapping(consumes = "multipart/form-data")
//    @PostMapping(consumes = { MediaType.MULTIPART_FORM_DATA_VALUE, "multipart/form-data;charset=UTF-8" })
//
//    public ResponseEntity<?> createCourse(@ModelAttribute @Valid CourseDTO courseDTO) {
//        try {
//            List<ChapterDTO> parsedChapters = objectMapper.readValue(
//                courseDTO.getChapters(),
//                new TypeReference<List<ChapterDTO>>() {}
//            );
//
//            MultipartFile courseImage = courseDTO.getImage();
//            String title = courseDTO.getCourseTitle();
//
//            // save logic here...
//
//            return ResponseEntity.ok("Course created successfully!");
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                .body("Failed to create course: " + e.getMessage());
//        }
//    }
    @PostMapping
    public ResponseEntity<?> createCourse(@ModelAttribute @Valid CourseDTO courseDTO) {
        try {
            List<ChapterDTO> parsedChapters = objectMapper.readValue(
                courseDTO.getChapters(),
                new TypeReference<List<ChapterDTO>>() {}
            );

            MultipartFile courseImage = courseDTO.getImage();
            String title = courseDTO.getCourseTitle();

            // Save logic...

            return ResponseEntity.ok("Course created successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Failed to create course: " + e.getMessage());
        }
    }


    @GetMapping
    public ResponseEntity<List<Course>> getAllCourses() {
        return ResponseEntity.ok(courseService.getAllCourses());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable Long id) {
        Course course = courseService.getCourseById(id);
        if (course != null) {
            return ResponseEntity.ok(course);
        } else {
            return ResponseEntity.notFound().build();  // 404 if course not found
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCourse(@PathVariable Long id, @Valid @RequestBody CourseDTO updatedCourseDTO, BindingResult result) {
        if (result.hasErrors()) {
            // Collect and return the error messages
            String errorMessages = result.getAllErrors().stream()
                .map(error -> ((FieldError) error).getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining(", "));
            return ResponseEntity.badRequest().body(errorMessages);  // Return 400 with error messages
        }

        Course updatedCourse = courseService.updateCourse(id, updatedCourseDTO);
        if (updatedCourse != null) {
            return ResponseEntity.ok(updatedCourse);
        } else {
            return ResponseEntity.notFound().build();  // 404 if course not found
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        boolean deleted = courseService.deleteCourse(id);
        if (deleted) {
            return ResponseEntity.noContent().build();  // 204 No Content for successful deletion
        } else {
            return ResponseEntity.notFound().build();  // 404 if course not found
        }
    }

    @GetMapping("/educator/{educatorId}")
    public ResponseEntity<List<Course>> getCoursesByEducator(@PathVariable String educatorId) {
        return ResponseEntity.ok(courseService.getCoursesByEducator(educatorId));
    }
}
