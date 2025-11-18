package com.bkap.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bkap.entity.Faculty;
import com.bkap.entity.School;
import com.bkap.servies.FacultyServices;
import com.bkap.servies.SchoolServices;

@RestController
@RequestMapping("/api/faculties")
public class FacultyApiController {

    @Autowired
    private FacultyServices facultyServices;

    @Autowired
    private SchoolServices schoolServices;

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    // Thêm mới
    @PostMapping
    public ResponseEntity<?> addFaculty(@RequestBody Map<String, Object> payload) {
        try {
            String name = (String) payload.get("facultyName");
            Long schoolId = Long.valueOf(payload.get("schoolId").toString());
            String dateStr = (String) payload.get("establishedDate");
            String phone = (String) payload.get("phone");
            String email = (String) payload.get("email");
            String description = (String) payload.get("description");

            Faculty faculty = new Faculty();
            faculty.setFacultyName(name);
            if(dateStr != null && !dateStr.isEmpty()) {
                faculty.setEstablishedDate(java.sql.Date.valueOf(dateStr)); // parse từ "YYYY-MM-DD"
            }
            faculty.setPhone(phone);
            faculty.setEmail(email);
            faculty.setDescription(description);

            // Set School
            School school = schoolServices.findById(schoolId)
                    .orElseThrow(() -> new RuntimeException("School not found"));
            faculty.setSchool(school);

            Faculty saved = facultyServices.save(faculty);
            return ResponseEntity.ok(saved);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Lỗi lưu khoa: " + e.getMessage());
        }
    }

    // Lấy 1 khoa theo ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getFacultyById(@PathVariable Long id) {
        return facultyServices.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Xóa
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFaculty(@PathVariable Long id) {
        try {
            facultyServices.delete(id);
            return ResponseEntity.ok("Xóa thành công!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Không thể xóa!");
        }
    }

    // Cập nhật
    @PutMapping("/{id}")
    public ResponseEntity<?> updateFaculty(@PathVariable Long id, @RequestBody Map<String, Object> payload) {
        try {
            Faculty faculty = facultyServices.findById(id)
                    .orElseThrow(() -> new RuntimeException("Faculty not found"));

            faculty.setFacultyName((String) payload.get("facultyName"));

            String dateStr = (String) payload.get("establishedDate");
            if(dateStr != null && !dateStr.isEmpty()) {
                Date establishedDate = dateFormat.parse(dateStr);
                faculty.setEstablishedDate(establishedDate);
            }

            faculty.setPhone((String) payload.get("phone"));
            faculty.setEmail((String) payload.get("email"));
            faculty.setDescription((String) payload.get("description"));

            Long schoolId = Long.valueOf(payload.get("schoolId").toString());
            School school = schoolServices.findById(schoolId)
                    .orElseThrow(() -> new RuntimeException("School not found"));
            faculty.setSchool(school);

            Faculty saved = facultyServices.save(faculty);
            return ResponseEntity.ok(saved);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Cập nhật thất bại!");
        }
    }
}
