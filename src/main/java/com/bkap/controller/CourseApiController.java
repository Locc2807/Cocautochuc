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

    // ================================
    // Lấy tất cả Course
    // ================================
    @GetMapping
    public ResponseEntity<?> getAllCourses() {
        try {
            return ResponseEntity.ok(courseService.getAll());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Lỗi lấy danh sách Course: " + e.getMessage());
        }
    }

    // ================================
    // Lấy Course theo ID
    // ================================
    @GetMapping("/{id}")
    public ResponseEntity<?> getCourseById(@PathVariable Long id) {
        Optional<Course> courseOpt = courseService.findById(id);
        return courseOpt.isPresent() ? ResponseEntity.ok(courseOpt.get()) : ResponseEntity.notFound().build();
    }

    // ================================
    // Thêm mới Course
    // ================================
    @PostMapping
    public ResponseEntity<?> createCourse(@RequestBody Map<String, Object> payload) {
        try {
            Course course = new Course();
            course.setCourseCode((String) payload.get("courseCode"));
            course.setCourseName((String) payload.get("courseName"));
            course.setCredits(payload.get("credits") != null ?
                              Integer.parseInt(payload.get("credits").toString()) : 0);

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
            return ResponseEntity.badRequest().body("Lỗi lưu Course: " + e.getMessage());
        }
    }

    // ================================
    // Cập nhật Course
    // ================================
    @PutMapping("/{id}")
    public ResponseEntity<?> updateCourse(@PathVariable Long id, @RequestBody Map<String, Object> payload) {
        try {
            Course course = courseService.findById(id)
                    .orElseThrow(() -> new RuntimeException("Course not found"));

            course.setCourseCode((String) payload.get("courseCode"));
            course.setCourseName((String) payload.get("courseName"));
            course.setCredits(payload.get("credits") != null ?
                              Integer.parseInt(payload.get("credits").toString()) : 0);

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
            return ResponseEntity.badRequest().body("Cập nhật Course thất bại: " + e.getMessage());
        }
    }

    // ================================
    // Xóa Course
    // ================================
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCourse(@PathVariable Long id) {
        try {
            courseService.delete(id);
            return ResponseEntity.ok("Xóa thành công!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Không thể xóa Course: " + e.getMessage());
        }
    }
}
