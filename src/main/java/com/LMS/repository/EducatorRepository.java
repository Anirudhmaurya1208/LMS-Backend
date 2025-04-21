package com.LMS.repository;

import com.LMS.model.Educator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EducatorRepository extends JpaRepository<Educator, String> {
    // Custom query methods can be added here
}
