package com.bkap.entity;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "KNOWLEDGE_BLOCK")
public class KnowledgeBlock {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "BLOCK_CODE", length = 36)
    private String blockCode;  // Mã khối kiến thức

    @Column(name = "NAME", nullable = false, length = 100)
    private String name;       // Tên khối kiến thức

    @Column(name = "MIN_REQUIRED_CREDITS", nullable = false)
    private Integer minRequiredCredits;  // Tổng số tín chỉ bắt buộc

    @Column(name = "ELECTIVE_CREDITS")
    private Integer electiveCredits;     // Số tín chỉ tự chọn

    @Column(name = "SUBJECT_COUNT", nullable = false)
    private Integer subjectCount;        // Số môn học trong khối

    // Constructors
    public KnowledgeBlock() {}

    public KnowledgeBlock(String blockCode, String name, Integer minRequiredCredits,
                          Integer electiveCredits, Integer subjectCount) {
        this.blockCode = blockCode;
        this.name = name;
        this.minRequiredCredits = minRequiredCredits;
        this.electiveCredits = electiveCredits;
        this.subjectCount = subjectCount;
    }

    // Getters và Setters
    public String getBlockCode() {
        return blockCode;
    }

    public void setBlockCode(String blockCode) {
        this.blockCode = blockCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMinRequiredCredits() {
        return minRequiredCredits;
    }

    public void setMinRequiredCredits(Integer minRequiredCredits) {
        this.minRequiredCredits = minRequiredCredits;
    }

    public Integer getElectiveCredits() {
        return electiveCredits;
    }

    public void setElectiveCredits(Integer electiveCredits) {
        this.electiveCredits = electiveCredits;
    }

    public Integer getSubjectCount() {
        return subjectCount;
    }

    public void setSubjectCount(Integer subjectCount) {
        this.subjectCount = subjectCount;
    }
}
