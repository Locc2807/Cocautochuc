package com.bkap.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "BATCH", uniqueConstraints = {
    @UniqueConstraint(columnNames = "batch_code")
})
@JsonIdentityInfo(
	    generator = ObjectIdGenerators.PropertyGenerator.class, 
	    property = "batchId"
	)
public class Batch {
	
	@Id
    @GeneratedValue
    (strategy = GenerationType.IDENTITY)
    @Column(name = "batch_id")
    private Long batchId;

    @Column(name = "batch_code", nullable = false, length = 10)
    private String batchCode;

    @Column(name = "academic_year", nullable = false, length = 20)
    private String academicYear;

    @Column(name = "description", length = 200)
    private String description;

    @Column(name = "total_curriculums")
    private Integer totalCurriculums = 0;

    // Quan hệ 1-N với Curriculum
    @OneToMany(mappedBy = "batch", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Curriculum> curriculums;


    // Constructors
    public Batch() {}

	public Batch(Long batchId, String batchCode, String academicYear, String description, Integer totalCurriculums,
			List<Curriculum> curriculums) {
		super();
		this.batchId = batchId;
		this.batchCode = batchCode;
		this.academicYear = academicYear;
		this.description = description;
		this.totalCurriculums = totalCurriculums;
		this.curriculums = curriculums;
	}

	public Long getBatchId() {
		return batchId;
	}

	public void setBatchId(Long batchId) {
		this.batchId = batchId;
	}

	public String getBatchCode() {
		return batchCode;
	}

	public void setBatchCode(String batchCode) {
		this.batchCode = batchCode;
	}

	public String getAcademicYear() {
		return academicYear;
	}

	public void setAcademicYear(String academicYear) {
		this.academicYear = academicYear;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getTotalCurriculums() {
		return totalCurriculums;
	}

	public void setTotalCurriculums(Integer totalCurriculums) {
		this.totalCurriculums = totalCurriculums;
	}

	public List<Curriculum> getCurriculums() {
		return curriculums;
	}

	public void setCurriculums(List<Curriculum> curriculums) {
		this.curriculums = curriculums;
	}
    
    
}
