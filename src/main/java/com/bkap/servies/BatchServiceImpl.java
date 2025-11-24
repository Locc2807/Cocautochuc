package com.bkap.servies;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.bkap.entity.Batch;
import com.bkap.repository.BatchRepository;

@Service
public class BatchServiceImpl implements BatchServices{
	@Autowired
    private BatchRepository batchRepository;

	@Override
    public List<Batch> getAllBatches() {
        return batchRepository.findAll();
    }

    @Override
    public Batch getBatchById(Long id) {
        return batchRepository.findById(id).orElse(null);
    }

    @Override
    public Batch createBatch(Batch batch) {
        return batchRepository.save(batch);
    }

    @Override
    public Batch updateBatch(Long id, Batch batch) {
        Optional<Batch> existing = batchRepository.findById(id);
        if (existing.isPresent()) {
            Batch b = existing.get();
            b.setBatchCode(batch.getBatchCode());
            b.setAcademicYear(batch.getAcademicYear());
            b.setTrainingRegulation(batch.getTrainingRegulation());
            b.setStudentCount(batch.getStudentCount());
            b.setTotalCurriculums(batch.getTotalCurriculums());
            return batchRepository.save(b);
        }
        return null;
    }

    @Override
    public void deleteBatch(Long id) {
        try {
            batchRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Cannot delete batch with id=" + id);
        }
    }

    @Override
    public Page<Batch> findAll(Pageable pageable) {
        return batchRepository.findAll(pageable);
    }

    @Override
    public Page<Batch> findByBatchCodePaged(String keyword, Pageable pageable) {
        return batchRepository.findByBatchCodeContainingIgnoreCase(keyword, pageable);
    }

    @Override
    public Page<Batch> findByBatchCodeAndAcademicYearPaged(String keyword, String academicYear, Pageable pageable) {
        return batchRepository.findByBatchCodeContainingIgnoreCaseAndAcademicYear(keyword, academicYear, pageable);
    }

    @Override
    public Page<Batch> findByAcademicYearPaged(String academicYear, Pageable pageable) {
        return batchRepository.findByAcademicYear(academicYear, pageable);
    }

    @Override
    public List<String> getDistinctAcademicYears() {
        return batchRepository.findDistinctAcademicYears();
    }

}
