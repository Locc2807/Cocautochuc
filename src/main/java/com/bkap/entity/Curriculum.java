package com.bkap.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "CURRICULUM")
@JsonIdentityInfo(
	    generator = ObjectIdGenerators.PropertyGenerator.class, 
	    property = "id"
	)
public class Curriculum {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "curriculum_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "batch_id", nullable = false)
    private Batch batch; // Liên kết với BATCH

    @ManyToOne
    @JoinColumn(name = "major_id", nullable = false)
    private Major major; // Liên kết với ngành

    @Column(name = "curriculum_code", nullable = false, length = 20, unique = true)
    private String curriculumCode; // Mã CTĐT

    @Column(name = "duration_years")
    private Integer durationYears; // Thời gian đào tạo (năm)

    @Column(name = "total_subjects")
    private Integer totalSubjects; // Tổng số môn học

    @Column(name = "total_credits")
    private Integer totalCredits; // Tổng số tín chỉ

    @Column(name = "description", length = 200)
    private String description; // Ghi chú

    @OneToMany(mappedBy = "curriculum", cascade = CascadeType.ALL)
    private List<KnowledgeBlock> blocks; // Danh sách khối kiến thức

    public Curriculum() {}

	public Curriculum(Long id, Batch batch, Major major, String curriculumCode, Integer durationYears,
			Integer totalSubjects, Integer totalCredits, String description, List<KnowledgeBlock> blocks) {
		super();
		this.id = id;
		this.batch = batch;
		this.major = major;
		this.curriculumCode = curriculumCode;
		this.durationYears = durationYears;
		this.totalSubjects = totalSubjects;
		this.totalCredits = totalCredits;
		this.description = description;
		this.blocks = blocks;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Batch getBatch() {
		return batch;
	}

	public void setBatch(Batch batch) {
		this.batch = batch;
	}

	public Major getMajor() {
		return major;
	}

	public void setMajor(Major major) {
		this.major = major;
	}

	public String getCurriculumCode() {
		return curriculumCode;
	}

	public void setCurriculumCode(String curriculumCode) {
		this.curriculumCode = curriculumCode;
	}

	public Integer getDurationYears() {
		return durationYears;
	}

	public void setDurationYears(Integer durationYears) {
		this.durationYears = durationYears;
	}

	public Integer getTotalSubjects() {
		return totalSubjects;
	}

	public void setTotalSubjects(Integer totalSubjects) {
		this.totalSubjects = totalSubjects;
	}

	public Integer getTotalCredits() {
		return totalCredits;
	}

	public void setTotalCredits(Integer totalCredits) {
		this.totalCredits = totalCredits;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<KnowledgeBlock> getBlocks() {
		return blocks;
	}

	public void setBlocks(List<KnowledgeBlock> blocks) {
		this.blocks = blocks;
	}
    
}
