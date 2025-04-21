package com.LMS.repository;

import com.LMS.model.Course;
import com.LMS.model.Educator;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findByEducator(Educator educator);
}
