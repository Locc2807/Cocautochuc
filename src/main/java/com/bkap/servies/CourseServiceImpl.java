package com.bkap.servies;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.bkap.entity.Course;
import com.bkap.entity.Faculty;
import com.bkap.repository.CourseRepository;

@Service
public class CourseServiceImpl implements CourseServices{
	
	@Autowired
	private CourseRepository courseRepository;

	@Override
	public List<Course> getAll() {
		// TODO Auto-generated method stub
		return this.courseRepository.findAll();
	}

	@Override
	public Page<Course> getAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return this.courseRepository.findAll(pageable);
	}

	@Override
	public Page<Course> findByName(String keyword, Pageable pageable) {
		// TODO Auto-generated method stub
		return courseRepository.findByCourseNameContainingIgnoreCase(keyword, pageable);
	}

	@Override
	public Page<Course> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return courseRepository.findAll(pageable);
	}

	@Override
	public Page<Course> findByIdPaged(Long id, Pageable pageable) {
		Optional<Course> facultyOpt = courseRepository.findById(id);
        if (facultyOpt.isPresent()) {
            return new PageImpl<>(Collections.singletonList(facultyOpt.get()), pageable, 1);
        }
        return Page.empty(pageable);
	}

	@Override
	public List<Course> findAllNoPaging() {
		// TODO Auto-generated method stub
		return courseRepository.findAll();
	}

	@Override
	public Course save(Course course) {
		// TODO Auto-generated method stub
		return courseRepository.save(course);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		courseRepository.deleteById(id);
	}

	@Override
	public Optional<Course> findById(Long id) {
		// TODO Auto-generated method stub
		return courseRepository.findById(id);
	}

	@Override
	public Page<Course> findByFacultyPaged(Long facultyId, Pageable pageable) {
		// TODO Auto-generated method stub
		return courseRepository.findByFaculty_FacultyId(facultyId, pageable);
	}

	@Override
	public Page<Course> findByMajorPaged(Long majorId, Pageable pageable) {
		// TODO Auto-generated method stub
		return courseRepository.findByMajor_MajorId(majorId, pageable);
	}

	@Override
	public Page<Course> findByFacultyAndMajorPaged(Long facultyId, Long majorId, Pageable pageable) {
		// TODO Auto-generated method stub
		return courseRepository.findByFaculty_FacultyIdAndMajor_MajorId(facultyId, majorId, pageable);
	}
	
}
