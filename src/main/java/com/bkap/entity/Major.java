package com.bkap.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.ForeignKey;

@Entity
@Table(name = "MAJOR")
public class Major {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "major_id")
    private Long majorId;

    @Column(name = "major_name", nullable = false, length = 255)
    private String majorName;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "faculty_id", nullable = false, foreignKey = @ForeignKey(name = "fk_major_faculty"))
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Faculty faculty;

    @Column(name = "established_date")
    @Temporal(TemporalType.DATE)
    private Date establishedDate;

    @Column(name = "description", length = 300)
    private String description;

    @Column(name = "major_code", nullable = false, unique = true, length = 50)
    private String majorCode;
    
    @Column(name = "min_credits", nullable = false)
    private Integer minCredits;

    public Major() {
    }

	public Major(Long majorId, String majorName, Faculty faculty, Date establishedDate, String description,
			String majorCode, Integer minCredits) {
		super();
		this.majorId = majorId;
		this.majorName = majorName;
		this.faculty = faculty;
		this.establishedDate = establishedDate;
		this.description = description;
		this.majorCode = majorCode;
		this.minCredits = minCredits;
	}

	public Long getMajorId() {
		return majorId;
	}

	public void setMajorId(Long majorId) {
		this.majorId = majorId;
	}

	public String getMajorName() {
		return majorName;
	}

	public void setMajorName(String majorName) {
		this.majorName = majorName;
	}

	public Faculty getFaculty() {
		return faculty;
	}

	public void setFaculty(Faculty faculty) {
		this.faculty = faculty;
	}

	public Date getEstablishedDate() {
		return establishedDate;
	}

	public void setEstablishedDate(Date establishedDate) {
		this.establishedDate = establishedDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMajorCode() {
		return majorCode;
	}

	public void setMajorCode(String majorCode) {
		this.majorCode = majorCode;
	}

	public Integer getMinCredits() {
		return minCredits;
	}

	public void setMinCredits(Integer minCredits) {
		this.minCredits = minCredits;
	}

}