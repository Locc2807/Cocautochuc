package com.bkap.entity;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "SCHOOL")
public class School {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "school_id")
	private Long id;

	@Column(name = "school_name", nullable = false, length = 255)
	private String name;

	@ManyToOne
	@JoinColumn(name = "university_id", nullable = false, foreignKey = @ForeignKey(name = "fk_school_university"))
	private University university;

	@Column(name = "established_date")
	@Temporal(TemporalType.DATE)
	private Date establishedDate;

	@Column(name = "address", length = 200)
	private String address;

	@Column(name = "phone", length = 20)
	private String phone;

	@Column(name = "email", length = 100)
	private String email;

	@Column(name = "website", length = 100)
	private String website;

	@Column(name = "description", length = 300)
	private String description;
	
	@OneToMany(mappedBy = "school")
	@com.fasterxml.jackson.annotation.JsonIgnore
	private List<Faculty> faculties;

	public School() {

	}

	public School(Long id, String name, University university, Date establishedDate, String address, String phone,
			String email, String website, String description, List<Faculty> faculties) {
		super();
		this.id = id;
		this.name = name;
		this.university = university;
		this.establishedDate = establishedDate;
		this.address = address;
		this.phone = phone;
		this.email = email;
		this.website = website;
		this.description = description;
		this.faculties = faculties;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public University getUniversity() {
		return university;
	}

	public void setUniversity(University university) {
		this.university = university;
	}

	public Date getEstablishedDate() {
		return establishedDate;
	}

	public void setEstablishedDate(Date establishedDate) {
		this.establishedDate = establishedDate;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Faculty> getFaculties() {
		return faculties;
	}

	public void setFaculties(List<Faculty> faculties) {
		this.faculties = faculties;
	}


}
