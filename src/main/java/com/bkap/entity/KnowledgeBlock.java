package com.bkap.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "KNOWLEDGE_BLOCK")
public class KnowledgeBlock {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "block_id")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "curriculum_id", nullable = false)
    private Curriculum curriculum; // liên kết với chương trình đào tạo

    @Column(name = "name", nullable = false, length = 100)
    private String name; // tên khối: Cơ sở, Cơ sở ngành, Chuyên ngành

    @Column(name = "description", length = 200)
    private String description;

    @OneToMany(mappedBy = "block", cascade = CascadeType.ALL)
    private List<BlockCourse> courses; // danh sách học phần thuộc khối

    public KnowledgeBlock() {}

	public KnowledgeBlock(Long id, Curriculum curriculum, String name, String description, List<BlockCourse> courses) {
		super();
		this.id = id;
		this.curriculum = curriculum;
		this.name = name;
		this.description = description;
		this.courses = courses;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Curriculum getCurriculum() {
		return curriculum;
	}

	public void setCurriculum(Curriculum curriculum) {
		this.curriculum = curriculum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<BlockCourse> getCourses() {
		return courses;
	}

	public void setCourses(List<BlockCourse> courses) {
		this.courses = courses;
	}
    
    
}
