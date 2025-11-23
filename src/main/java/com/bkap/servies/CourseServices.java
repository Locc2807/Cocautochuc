package com.bkap.servies;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.bkap.entity.Course;

public interface CourseServices {
//	List<Course> findAll();
//    Course findById(Long id);
//    Course save(Course course);
//    Course update(Long id, Course course);
//    void deleteById(Long id);
	
	List<Course> getAll();
	Page<Course> getAll(Pageable pageable);
	
	Page<Course> findByName(String keyword, Pageable pageable);
	Page<Course> findAll(Pageable pageable);
	
	Page<Course> findByIdPaged(Long id, Pageable pageable);
    List<Course> findAllNoPaging();
    
    Course save(Course course);
    void delete(Long id);
    Optional<Course> findById(Long id);
    
 // Lấy theo facultyId với phân trang
    Page<Course> findByFacultyPaged(Long facultyId, Pageable pageable);

    // Lấy theo majorId với phân trang
    Page<Course> findByMajorPaged(Long majorId, Pageable pageable);

    // Lấy theo cả facultyId và majorId với phân trang
    Page<Course> findByFacultyAndMajorPaged(Long facultyId, Long majorId, Pageable pageable);

}
