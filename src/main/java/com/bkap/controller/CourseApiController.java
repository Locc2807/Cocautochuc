package com.bkap.controller;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bkap.entity.Course;
import com.bkap.entity.Faculty;
import com.bkap.entity.Major;
import com.bkap.repository.FacultyRepository;
import com.bkap.repository.MajorRepository;
import com.bkap.servies.CourseServices;

@RestController
@RequestMapping("/api/courses")
@CrossOrigin(origins = "*")
public class CourseApiController {

    @Autowired
    private CourseServices courseService;
    
    @Autowired
    private FacultyRepository facultyRepository;
    
    @Autowired
    private MajorRepository majorRepository;

    // Lấy tất cả course
    @GetMapping
    public ResponseEntity<?> getAllCourses() {
        return ResponseEntity.ok(courseService.getAll());
    }

    // Lấy course theo id
    @GetMapping("/{id}")
    public ResponseEntity<?> getCourseById(@PathVariable Long id) {
        Optional<Course> courseOpt = courseService.findById(id);
        if (courseOpt.isPresent()) {
            return ResponseEntity.ok(courseOpt.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Thêm mới course
    @PostMapping
    public ResponseEntity<?> createCourse(@RequestBody Map<String, Object> payload) {
        try {
            Course course = new Course();
            course.setCourseCode((String) payload.get("courseCode"));
            course.setCourseName((String) payload.get("courseName"));
            course.setCredits(Integer.parseInt(payload.get("credits").toString()));

         // Chuyển facultyId sang Faculty entity
            Long facultyId = Long.parseLong(payload.get("facultyId").toString());
            Faculty faculty = facultyRepository.findById(facultyId)
                    .orElseThrow(() -> new RuntimeException("Faculty not found"));
            course.setFaculty(faculty);

            // Chuyển majorId sang Major entity (nếu có)
            if (payload.get("majorId") != null) {
                Long majorId = Long.parseLong(payload.get("majorId").toString());
                Major major = majorRepository.findById(majorId)
                        .orElseThrow(() -> new RuntimeException("Major not found"));
                course.setMajor(major);
            } else {
                course.setMajor(null);
            }

            Course saved = courseService.save(course);
            return ResponseEntity.ok(saved);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Lỗi lưu học phần: " + e.getMessage());
        }
    }


    // Cập nhật course
    @PutMapping("/{id}")
    public ResponseEntity<?> updateCourse(@PathVariable Long id, @RequestBody Map<String, Object> payload) {
        try {
            Course course = courseService.findById(id)
                    .orElseThrow(() -> new RuntimeException("Course not found"));

            course.setCourseCode((String) payload.get("courseCode"));
            course.setCourseName((String) payload.get("courseName"));
            course.setCredits(Integer.parseInt(payload.get("credits").toString()));

            // Faculty
            Long facultyId = Long.parseLong(payload.get("facultyId").toString());
            Faculty faculty = facultyRepository.findById(facultyId)
                    .orElseThrow(() -> new RuntimeException("Faculty not found"));
            course.setFaculty(faculty);

            // Major (nếu có)
            if (payload.get("majorId") != null) {
                Long majorId = Long.parseLong(payload.get("majorId").toString());
                Major major = majorRepository.findById(majorId)
                        .orElseThrow(() -> new RuntimeException("Major not found"));
                course.setMajor(major);
            } else {
                course.setMajor(null);
            }

            Course saved = courseService.save(course);
            return ResponseEntity.ok(saved);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Cập nhật thất bại: " + e.getMessage());
        }
    }

    // Xóa course
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCourse(@PathVariable Long id) {
        try {
            courseService.delete(id);
            return ResponseEntity.ok("Xóa thành công!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Không thể xóa học phần này!");
        }
    }
}
