package com.LMS.service;

import com.LMS.model.Educator;
import com.LMS.repository.EducatorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EducatorService {

    private final EducatorRepository educatorRepository;

    public Educator saveEducator(Educator educator) {
        return educatorRepository.save(educator);
    }

    public List<Educator> getAllEducators() {
        return educatorRepository.findAll();
    }

    public Optional<Educator> getEducatorById(String id) {
        return educatorRepository.findById(id);
    }

    public boolean deleteEducator(String id) {
        if (educatorRepository.existsById(id)) {
            educatorRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Educator updateEducator(String id, Educator updatedData) {
        return educatorRepository.findById(id).map(existing -> {
            existing.setName(updatedData.getName());
            existing.setEmail(updatedData.getEmail());
            existing.setImageUrl(updatedData.getImageUrl());
            return educatorRepository.save(existing);
        }).orElse(null);
    }
}
