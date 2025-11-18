package com.bkap.controller;

import java.sql.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bkap.entity.Major;
import com.bkap.entity.Faculty;
import com.bkap.servies.MajorServices;
import com.bkap.servies.FacultyServices;

@RestController
@RequestMapping("/api/majors")
public class MajorApiController {

    @Autowired
    private MajorServices majorServices;

    @Autowired
    private FacultyServices facultyServices;

    // ✅ Thêm mới Major
    @PostMapping
    public ResponseEntity<?> addMajor(@RequestBody Map<String, Object> payload) {
        try {
            String name = (String) payload.get("majorName");
            String code = (String) payload.get("majorCode");
            Long facultyId = Long.valueOf(payload.get("facultyId").toString());
            String dateStr = (String) payload.get("establishedDate");
            String description = (String) payload.get("description");

            Major major = new Major();
            major.setMajorName(name);
            major.setMajorCode(code);

            if (dateStr != null && !dateStr.isEmpty()) {
                major.setEstablishedDate(Date.valueOf(dateStr));
            }

            major.setDescription(description);

            // set Faculty
            Faculty faculty = facultyServices.findById(facultyId)
                    .orElseThrow(() -> new RuntimeException("Faculty not found"));
            major.setFaculty(faculty);

            Major saved = majorServices.save(major);
            return ResponseEntity.ok(saved);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Lỗi lưu Major: " + e.getMessage());
        }
    }

    // ✅ Lấy Major theo ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getMajorById(@PathVariable Long id) {
        return majorServices.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ✅ Xóa Major
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMajor(@PathVariable Long id) {
        try {
            majorServices.delete(id);
            return ResponseEntity.ok("Xóa thành công!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Không thể xóa Major!");
        }
    }

    // ✅ Cập nhật Major
    @PutMapping("/{id}")
    public ResponseEntity<?> updateMajor(@PathVariable Long id, @RequestBody Map<String, Object> payload) {
        try {
            Major major = majorServices.findById(id)
                    .orElseThrow(() -> new RuntimeException("Major not found"));

            major.setMajorName((String) payload.get("majorName"));
            major.setMajorCode((String) payload.get("majorCode"));

            String dateStr = (String) payload.get("establishedDate");
            if (dateStr != null && !dateStr.isEmpty()) {
                major.setEstablishedDate(Date.valueOf(dateStr));
            }

            major.setDescription((String) payload.get("description"));

            Long facultyId = Long.valueOf(payload.get("facultyId").toString());
            Faculty faculty = facultyServices.findById(facultyId)
                    .orElseThrow(() -> new RuntimeException("Faculty not found"));
            major.setFaculty(faculty);

            Major saved = majorServices.save(major);
            return ResponseEntity.ok(saved);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Cập nhật Major thất bại!");
        }
    }
}
