package com.bkap.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.ForeignKey;


@Entity
@Table(name = "tuition_bill")
public class TuitionBill {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "semester", nullable = false, length = 10)
    private String semester;

    @Column(name = "price_per_credit", nullable = false, precision = 10, scale = 2)
    private BigDecimal pricePerCredit;

    @Column(name = "total_credits", nullable = false)
    private Integer totalCredits;

    @Column(name = "personal_extra_fee", precision = 10, scale = 2)
    private BigDecimal personalExtraFee = BigDecimal.ZERO;

    @Column(name = "total_amount", nullable = false, precision = 12, scale = 2)
    private BigDecimal totalAmount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "formula_id", referencedColumnName = "id",
                foreignKey = @ForeignKey(name = "fk_bill_formula"))
    private TuitionFormula formula;

    @Column(name = "created_by", length = 50)
    private String createdBy;

    @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

    // Constructors
    public TuitionBill() {
    	
    }

	public TuitionBill(Long id, String semester, BigDecimal pricePerCredit, Integer totalCredits, BigDecimal personalExtraFee,
			BigDecimal totalAmount, TuitionFormula formula, String createdBy, LocalDateTime createdAt) {
		super();
		this.id = id;
		this.semester = semester;
		this.pricePerCredit = pricePerCredit;
		this.totalCredits = totalCredits;
		this.personalExtraFee = personalExtraFee;
		this.totalAmount = totalAmount;
		this.formula = formula;
		this.createdBy = createdBy;
		this.createdAt = createdAt;
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

	public Integer getTotalCredits() {
		return totalCredits;
	}

	public void setTotalCredits(Integer totalCredits) {
		this.totalCredits = totalCredits;
	}

	public BigDecimal getPersonalExtraFee() {
		return personalExtraFee;
	}

	public void setPersonalExtraFee(BigDecimal personalExtraFee) {
		this.personalExtraFee = personalExtraFee;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public TuitionFormula getFormula() {
		return formula;
	}

	public void setFormula(TuitionFormula formula) {
		this.formula = formula;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
    
    
}
