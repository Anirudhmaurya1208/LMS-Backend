package com.LMS.service;

import com.LMS.dto.ChapterDTO;
import com.LMS.dto.CourseDTO;
import com.LMS.dto.LectureDTO;
import com.LMS.model.*;
import com.LMS.repository.CourseRepository;
import com.LMS.repository.EducatorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;
    private final EducatorRepository educatorRepository;

    public Course saveCourse(CourseDTO courseDTO) {
        Educator educator = educatorRepository.findById(courseDTO.getEducatorId())
                .orElseThrow(() -> new RuntimeException("Educator not found"));

        Course course = Course.builder()
                .courseTitle(courseDTO.getCourseTitle())
                .coursePrice(courseDTO.getCoursePrice())
                .discount(courseDTO.getDiscount())
                .image(courseDTO.getImage())
                .educator(educator)
                .build();

        List<Chapter> chapters = buildChaptersFromDTO(courseDTO.getChapters(), course);
        course.setChapters(chapters);

        return courseRepository.save(course);
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Course getCourseById(Long id) {
        return courseRepository.findById(id).orElse(null);
    }

    public Course updateCourse(Long id, CourseDTO updatedCourseDTO) {
        Course existing = courseRepository.findById(id).orElse(null);
        if (existing == null) return null;

        if (updatedCourseDTO.getEducatorId() != null) {
            Educator educator = educatorRepository.findById(updatedCourseDTO.getEducatorId())
                    .orElseThrow(() -> new RuntimeException("Educator not found"));
            existing.setEducator(educator);
        }

        existing.setCourseTitle(updatedCourseDTO.getCourseTitle());
        existing.setCoursePrice(updatedCourseDTO.getCoursePrice());
        existing.setDiscount(updatedCourseDTO.getDiscount());
        existing.setImage(updatedCourseDTO.getImage());

        List<Chapter> updatedChapters = buildChaptersFromDTO(updatedCourseDTO.getChapters(), existing);

        existing.getChapters().clear();
        existing.getChapters().addAll(updatedChapters);

        return courseRepository.save(existing);
    }

    public boolean deleteCourse(Long id) {
        if (courseRepository.existsById(id)) {
            courseRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Course> getCoursesByEducator(String educatorId) {
        Educator educator = educatorRepository.findById(educatorId)
                .orElseThrow(() -> new RuntimeException("Educator not found"));
        return courseRepository.findByEducator(educator);
    }

    private List<Chapter> buildChaptersFromDTO(List<ChapterDTO> chapterDTOs, Course course) {
        List<Chapter> chapters = new ArrayList<>();
        for (ChapterDTO chDto : chapterDTOs) {
            Chapter chapter = Chapter.builder()
                    .chapterTitle(chDto.getChapterTitle())
                    .chapterOrder(chDto.getChapterOrder())
                    .course(course)
                    .build();

            List<Lecture> lectures = new ArrayList<>();
            for (LectureDTO lecDto : chDto.getChapterContent()) {
                lectures.add(Lecture.builder()
                        .lectureTitle(lecDto.getLectureTitle())
                        .lectureDuration(lecDto.getLectureDuration())
                        .lectureUrl(lecDto.getLectureUrl())
                        .isPreviewFree(lecDto.isPreviewFree())
                        .lectureOrder(lecDto.getLectureOrder())
                        .chapter(chapter)
                        .build());
            }

            chapter.setLectures(lectures);
            chapters.add(chapter);
        }
        return chapters;
    }
}
