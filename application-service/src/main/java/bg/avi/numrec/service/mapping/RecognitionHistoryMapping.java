package bg.avi.numrec.service.mapping;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bg.avi.numrec.data.dto.CountryDTO;
import bg.avi.numrec.data.dto.RecognitionHistoryDTO;
import bg.avi.numrec.db.entity.RecognitionHistory;

@Service
public class RecognitionHistoryMapping {

	@Autowired CountryMapping countryMapping;
	
	public List<RecognitionHistoryDTO> entityToDTO(List<RecognitionHistory> entities) {
		if (entities == null || entities.isEmpty()) return Collections.emptyList();
		
		List<RecognitionHistoryDTO> mappedList = new ArrayList<>();
		for (RecognitionHistory curr : entities) {
			mappedList.add(new RecognitionHistoryDTO(curr.getId(), 
					new CountryDTO(curr.getCountry().getCode(), curr.getCountry().getName(), curr.getCountry().getDescription()), curr.getPlate(), curr.getDate()));
		}
		return mappedList;
	}
	
	public RecognitionHistory dtoToEntity(RecognitionHistoryDTO dto) {
		if (dto == null) return null;
		return new RecognitionHistory(dto.getId(), countryMapping.dtoToEntity(dto.getCountry()), dto.getPlate(), dto.getDate());
	}
}
