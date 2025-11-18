package com.bkap.controller;

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

import com.bkap.entity.School;
import com.bkap.entity.University;
import com.bkap.servies.SchoolServices;
import com.bkap.servies.UniversityServices;

@RestController
@RequestMapping("/api/schools")
public class SchoolApiController {
	@Autowired
	private SchoolServices schoolServices;
	
	@Autowired
	private UniversityServices universityServices;
	
	@PostMapping
	public ResponseEntity<?> addSchool(@RequestBody Map<String, Object> payload) {
	    try {
	        String name = (String) payload.get("name");
	        Long universityId = Long.valueOf(payload.get("universityId").toString());
	        String address = (String) payload.get("address");
	        String phone = (String) payload.get("phone");
	        String email = (String) payload.get("email");

	        School school = new School();
	        school.setName(name);
	        school.setAddress(address);
	        school.setPhone(phone);
	        school.setEmail(email);

	        // Set University
	        University university = universityServices.findById(universityId)
	                .orElseThrow(() -> new RuntimeException("University not found"));
	        school.setUniversity(university);

	        School saved = schoolServices.save(school);
	        return ResponseEntity.ok(saved);
	    } catch (Exception e) {
	        return ResponseEntity.badRequest().body("Lỗi lưu trường!");
	    }
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getSchoolById(@PathVariable Long id) {
	    return schoolServices.findById(id)
	        .map(ResponseEntity::ok)
	        .orElse(ResponseEntity.notFound().build());
	}
	
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSchool(@PathVariable Long id) {
        try {
            schoolServices.delete(id);
            return ResponseEntity.ok("Xóa thành công!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Không thể xóa!");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateSchool(@PathVariable Long id, @RequestBody School updated) {
        try {
            updated.setId(id);
            return ResponseEntity.ok(schoolServices.save(updated));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Cập nhật thất bại!");
        }
    }

}
