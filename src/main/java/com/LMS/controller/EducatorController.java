package com.LMS.controller;

import com.LMS.model.Course;
import com.LMS.model.Educator;
import com.LMS.service.EducatorService;
import com.LMS.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/educators")
public class EducatorController {

    @Autowired
    private EducatorService educatorService;

    @Autowired
    private CourseService courseService;

    @GetMapping
    public List<Educator> getAllEducators() {
        return educatorService.getAllEducators();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Educator> getEducatorById(@PathVariable String id) {
        Optional<Educator> educator = educatorService.getEducatorById(id);
        return educator.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/courses")
    public ResponseEntity<List<Course>> getCoursesByEducator(@PathVariable String id) {
        List<Course> courses = courseService.getCoursesByEducator(id);
        return courses.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(courses);
    }

    @PostMapping
    public ResponseEntity<Educator> saveEducator(@RequestBody Educator educator) {
        Educator savedEducator = educatorService.saveEducator(educator);
        return ResponseEntity.status(201).body(savedEducator);  // Return 201 Created status
    }

    @PutMapping("/{id}")
    public ResponseEntity<Educator> updateEducator(@PathVariable String id, @RequestBody Educator educator) {
        Educator updatedEducator = educatorService.updateEducator(id, educator);
        return updatedEducator != null ? ResponseEntity.ok(updatedEducator) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteEducator(@PathVariable String id) {
        boolean deleted = educatorService.deleteEducator(id);
        if (deleted) {
            return ResponseEntity.ok(Map.of("message", "Educator deleted successfully"));
        } else {
            return ResponseEntity.status(404).body(Map.of("error", "Educator not found"));
        }
    }
}
