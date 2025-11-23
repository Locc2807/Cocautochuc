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
		// TODO Auto-generated method stub
		return batchRepository.findAll();
	}

	@Override
	public Batch getBatchById(Long id) {
		// TODO Auto-generated method stub
		return batchRepository.findById(id).orElse(null);
	}

	@Override
	public Batch createBatch(Batch batch) {
		// TODO Auto-generated method stub
		return batchRepository.save(batch);
	}

	@Override
	public Batch updateBatch(Long id, Batch batch) {
		Optional<Batch> existing = batchRepository.findById(id);
        if (existing.isPresent()) {
            Batch b = existing.get();
            b.setBatchCode(batch.getBatchCode());
            b.setAcademicYear(batch.getAcademicYear());
            b.setDescription(batch.getDescription());
            b.setTotalCurriculums(batch.getTotalCurriculums());
            return batchRepository.save(b);
        }
        return null;
	}

	@Override
	public void deleteBatch(Long id) {
		// TODO Auto-generated method stub
		 try {
		        batchRepository.deleteById(id);
		    } catch (Exception e) {
		        throw new RuntimeException("Cannot delete batch with id=" + id);
		    }
	}

	@Override
	public Page<Batch> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return batchRepository.findAll(pageable);
	}

	@Override
	public Page<Batch> findByBatchCodePaged(String keyword, Pageable pageable) {
		// TODO Auto-generated method stub
		return batchRepository.findByBatchCodeContainingIgnoreCase(keyword, pageable);
	}

	@Override
	public Page<Batch> findByBatchCodeAndAcademicYearPaged(String keyword, String academicYear, Pageable pageable) {
		// TODO Auto-generated method stub
		return batchRepository.findByBatchCodeContainingIgnoreCaseAndAcademicYear(keyword, academicYear, pageable);
	}

	@Override
	public Page<Batch> findByAcademicYearPaged(String academicYear, Pageable pageable) {
		// TODO Auto-generated method stub
		return batchRepository.findByAcademicYear(academicYear, pageable);
	}

	@Override
	public List<String> getDistinctAcademicYears() {
		// TODO Auto-generated method stub
		return batchRepository.findDistinctAcademicYears();
	}

}
