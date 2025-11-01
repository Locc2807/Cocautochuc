package com.bkap.servies;

import java.util.List;
import java.util.Optional;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.bkap.entity.School;
import com.bkap.repository.SchoolRepository;

@Service
public class SchoolServiceImpl implements SchoolServices {

    @Autowired
    private SchoolRepository schoolRepository;

    @Override
    public List<School> getAll() {
        return schoolRepository.findAll();
    }

    @Override
    public Page<School> getAll(Pageable pageable) {
        return schoolRepository.findAll(pageable);
    }

    @Override
    public Page<School> findAll(Pageable pageable) {
        return schoolRepository.findAll(pageable);
    }

    @Override
    public Page<School> findByName(String keyword, Pageable pageable) {
        return schoolRepository.findByNameContainingIgnoreCase(keyword, pageable);
    }

    @Override
    public Page<School> findByIdPaged(Long id, Pageable pageable) {
        Optional<School> schoolOpt = schoolRepository.findById(id);
        if (schoolOpt.isPresent()) {
            return new PageImpl<>(Collections.singletonList(schoolOpt.get()), pageable, 1);
        }
        return Page.empty(pageable);
    }

    @Override
    public List<School> findAllNoPaging() {
        return schoolRepository.findAll();
    }
}
