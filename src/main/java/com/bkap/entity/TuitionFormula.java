package com.bkap.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "TUITION_FORMULA",
uniqueConstraints = @UniqueConstraint(columnNames = {"SEMESTER", "IS_ACTIVE"}))
public class TuitionFormula {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "semester", nullable = false, length = 20)
    private String semester;

	@Column(name = "price_per_credit", precision = 10, scale = 2, nullable = false)
	private BigDecimal pricePerCredit;

    @Column(name = "training_system", nullable = false, length = 50)
    private String trainingSystem;

    @Column(name = "training_coeff", precision = 4, scale = 2, nullable = false)
    private BigDecimal trainingCoeff = BigDecimal.valueOf(1.0);

    @Column(name = "subject_coeff", precision = 4, scale = 2, nullable = false)
    private BigDecimal subjectCoeff = BigDecimal.valueOf(1.0);

    @Column(name = "extra_fee", precision = 12, scale = 2)
    private BigDecimal extraFee = BigDecimal.valueOf(0.0);

    @Column(name = "is_active", length = 1, nullable = false)
    private String isActive = "1"; // '1' = đang dùng, '0' = không dùng

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "created_by", nullable = false, length = 50)
    private String createdBy = "system";

    @Lob
    @Column(name = "note")
    private String note;

    public TuitionFormula() {

    }

	public TuitionFormula(Long id, String semester, BigDecimal pricePerCredit, String trainingSystem,
			BigDecimal trainingCoeff, BigDecimal subjectCoeff, BigDecimal extraFee, String isActive,
			LocalDateTime createdAt, String createdBy, String note) {
		super();
		this.id = id;
		this.semester = semester;
		this.pricePerCredit = pricePerCredit;
		this.trainingSystem = trainingSystem;
		this.trainingCoeff = trainingCoeff;
		this.subjectCoeff = subjectCoeff;
		this.extraFee = extraFee;
		this.isActive = isActive;
		this.createdAt = createdAt;
		this.createdBy = createdBy;
		this.note = note;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSemester() {
		return semester;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}

	public BigDecimal getPricePerCredit() {
		return pricePerCredit;
	}

	public void setPricePerCredit(BigDecimal pricePerCredit) {
		this.pricePerCredit = pricePerCredit;
	}

	public String getTrainingSystem() {
		return trainingSystem;
	}

	public void setTrainingSystem(String trainingSystem) {
		this.trainingSystem = trainingSystem;
	}

	public BigDecimal getTrainingCoeff() {
		return trainingCoeff;
	}

	public void setTrainingCoeff(BigDecimal trainingCoeff) {
		this.trainingCoeff = trainingCoeff;
	}

	public BigDecimal getSubjectCoeff() {
		return subjectCoeff;
	}

	public void setSubjectCoeff(BigDecimal subjectCoeff) {
		this.subjectCoeff = subjectCoeff;
	}

	public BigDecimal getExtraFee() {
		return extraFee;
	}

	public void setExtraFee(BigDecimal extraFee) {
		this.extraFee = extraFee;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

}
