package com.bkap.controller;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bkap.entity.Faculty;
import com.bkap.entity.Lecturer;
import com.bkap.entity.School;
import com.bkap.servies.FacultyServices;
import com.bkap.servies.LecturerServices;
import com.bkap.servies.SchoolServices;

@RestController
@RequestMapping("/api/lecturers")
public class LecturerApiController {

	@Autowired
    private LecturerServices lecturerServices;

    @Autowired
    private SchoolServices schoolServices;

    @Autowired
    private FacultyServices facultyServices;

    // ✅ 1. Thêm mới Lecturer
    @PostMapping
    public ResponseEntity<?> addLecturer(@RequestBody Map<String, Object> payload) {
        try {
            Lecturer lecturer = new Lecturer();

            lecturer.setLecturerName((String) payload.get("lecturerName"));
            lecturer.setLecturerCode((String) payload.get("lecturerCode")); 
            lecturer.setEmail((String) payload.get("email"));
            lecturer.setPhone((String) payload.get("phone"));
            lecturer.setPosition((String) payload.get("position"));
            lecturer.setContractType((String) payload.get("contractType"));
            
            if (payload.get("startDate") != null) {
                String startDateStr = (String) payload.get("startDate"); // yyyy-MM-dd
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date startDate = sdf.parse(startDateStr);
                lecturer.setStartDate(startDate);
            }

            // School
            if (payload.get("schoolId") != null) {
                Long schoolId = Long.valueOf(payload.get("schoolId").toString());
                School school = schoolServices.findById(schoolId)
                        .orElseThrow(() -> new RuntimeException("School not found"));
                lecturer.setSchool(school);
            }

            // Faculty
            if (payload.get("facultyId") != null) {
                Long facultyId = Long.valueOf(payload.get("facultyId").toString());
                Faculty faculty = facultyServices.findById(facultyId)
                        .orElseThrow(() -> new RuntimeException("Faculty not found"));
                lecturer.setFaculty(faculty);
            }

            Lecturer saved = lecturerServices.save(lecturer);
            return ResponseEntity.ok(saved);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Lỗi lưu Lecturer: " + e.getMessage());
        }
    }

    // ✅ 2. Lấy Lecturer theo ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getLecturerById(@PathVariable Long id) {
        return lecturerServices.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ✅ 3. Xóa Lecturer
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteLecturer(@PathVariable Long id) {
        try {
            lecturerServices.delete(id);
            return ResponseEntity.ok("Xóa thành công!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Không thể xóa Lecturer!");
        }
    }

    // ✅ 4. Cập nhật Lecturer
    @PutMapping("/{id}")
    public ResponseEntity<?> updateLecturer(@PathVariable Long id, @RequestBody Map<String, Object> payload) {
        try {
            Lecturer lecturer = lecturerServices.findById(id)
                    .orElseThrow(() -> new RuntimeException("Lecturer not found"));

            lecturer.setLecturerName((String) payload.get("lecturerName"));
            lecturer.setLecturerCode((String) payload.get("lecturerCode")); 
            lecturer.setEmail((String) payload.get("email"));
            lecturer.setPhone((String) payload.get("phone"));
            lecturer.setPosition((String) payload.get("position"));
            lecturer.setContractType((String) payload.get("contractType"));
            
            if (payload.get("startDate") != null) {
                String startDateStr = (String) payload.get("startDate"); // yyyy-MM-dd
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date startDate = sdf.parse(startDateStr);
                lecturer.setStartDate(startDate);
            }

            // School
            if (payload.get("schoolId") != null) {
                Long schoolId = Long.valueOf(payload.get("schoolId").toString());
                School school = schoolServices.findById(schoolId)
                        .orElseThrow(() -> new RuntimeException("School not found"));
                lecturer.setSchool(school);
            }

            // Faculty
            if (payload.get("facultyId") != null) {
                Long facultyId = Long.valueOf(payload.get("facultyId").toString());
                Faculty faculty = facultyServices.findById(facultyId)
                        .orElseThrow(() -> new RuntimeException("Faculty not found"));
                lecturer.setFaculty(faculty);
            }

            Lecturer saved = lecturerServices.save(lecturer);
            return ResponseEntity.ok(saved);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Cập nhật Lecturer thất bại!");
        }
    }

}
