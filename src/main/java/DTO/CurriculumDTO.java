package DTO;

public class CurriculumDTO {
	public String curriculumCode;
    public Long batchId;
    public Long majorId;
    public Integer durationYears;
    public Integer totalSubjects;
    public Integer totalCredits;
    public String description;
    
    public CurriculumDTO() {}

	public CurriculumDTO(String curriculumCode, Long batchId, Long majorId, Integer durationYears,
			Integer totalSubjects, Integer totalCredits, String description) {
		super();
		this.curriculumCode = curriculumCode;
		this.batchId = batchId;
		this.majorId = majorId;
		this.durationYears = durationYears;
		this.totalSubjects = totalSubjects;
		this.totalCredits = totalCredits;
		this.description = description;
	}

	public String getCurriculumCode() {
		return curriculumCode;
	}

	public void setCurriculumCode(String curriculumCode) {
		this.curriculumCode = curriculumCode;
	}

	public Long getBatchId() {
		return batchId;
	}

	public void setBatchId(Long batchId) {
		this.batchId = batchId;
	}

	public Long getMajorId() {
		return majorId;
	}

	public void setMajorId(Long majorId) {
		this.majorId = majorId;
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
    
    
}
