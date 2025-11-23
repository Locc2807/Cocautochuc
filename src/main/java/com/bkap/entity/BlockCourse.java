package com.bkap.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "BLOCK_COURSE")
public class BlockCourse {
	
	 	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "id")
	    private Long id;  // id tự sinh thay vì composite key

	    @ManyToOne
	    @JoinColumn(name = "block_id", nullable = false)
	    private KnowledgeBlock block;

	    @ManyToOne
	    @JoinColumn(name = "course_id", nullable = false)
	    private Course course;

	    @Column(name = "semester", length = 10)
	    private String semester; // HK1, HK2, HK Hè

	    @Column(name = "is_compulsory")
	    private Boolean isCompulsory; // true = bắt buộc, false = tự chọn

	    public BlockCourse() {}

		public BlockCourse(Long id, KnowledgeBlock block, Course course, String semester, Boolean isCompulsory) {
			super();
			this.id = id;
			this.block = block;
			this.course = course;
			this.semester = semester;
			this.isCompulsory = isCompulsory;
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public KnowledgeBlock getBlock() {
			return block;
		}

		public void setBlock(KnowledgeBlock block) {
			this.block = block;
		}

		public Course getCourse() {
			return course;
		}

		public void setCourse(Course course) {
			this.course = course;
		}

		public String getSemester() {
			return semester;
		}

		public void setSemester(String semester) {
			this.semester = semester;
		}

		public Boolean getIsCompulsory() {
			return isCompulsory;
		}

		public void setIsCompulsory(Boolean isCompulsory) {
			this.isCompulsory = isCompulsory;
		}
	    
	    
}
