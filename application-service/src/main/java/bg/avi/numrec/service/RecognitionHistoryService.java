package bg.avi.numrec.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import bg.avi.numrec.data.dto.RecognitionHistoryDTO;
import bg.avi.numrec.db.entity.RecognitionHistory;
import bg.avi.numrec.db.repository.RecognitionHistoryRepository;
import bg.avi.numrec.service.mapping.RecognitionHistoryMapping;

@Service
public class RecognitionHistoryService {
	@Autowired private RecognitionHistoryRepository recognitionHistoryRepository;
	@Autowired private RecognitionHistoryMapping recognitionHistoryMapping;
	
	public List<RecognitionHistoryDTO> findAll() {
		List<RecognitionHistory> results = recognitionHistoryRepository.findAll();
		return recognitionHistoryMapping.entityToDTO(results);
	}
	
	public List<RecognitionHistoryDTO> find(RecognitionHistoryDTO example) {
		Example<RecognitionHistory> countryExample = Example.of(recognitionHistoryMapping.dtoToEntity(example));
		List<RecognitionHistory> results = recognitionHistoryRepository.findAll(countryExample);
		return recognitionHistoryMapping.entityToDTO(results);
	}
	
	public void saveOrUpdate(RecognitionHistoryDTO entity) {
		recognitionHistoryRepository.save(recognitionHistoryMapping.dtoToEntity(entity));
	}
}