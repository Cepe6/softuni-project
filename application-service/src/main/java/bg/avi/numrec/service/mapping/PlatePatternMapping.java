package bg.avi.numrec.service.mapping;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bg.avi.numrec.data.dto.CountryDTO;
import bg.avi.numrec.data.dto.PlatePatternDTO;
import bg.avi.numrec.db.entity.PlatePattern;

@Service
public class PlatePatternMapping {
	@Autowired CountryMapping countryMapping;
	
	public List<PlatePatternDTO> entityToDTO(List<PlatePattern> entities) {
		if (entities == null || entities.isEmpty()) return Collections.emptyList();
		
		List<PlatePatternDTO> mappedList = new ArrayList<>();
		for (PlatePattern curr : entities) {
			mappedList.add(new PlatePatternDTO(curr.getId(), curr.getPattern(), 
					new CountryDTO(curr.getCountry().getCode(), curr.getCountry().getName(), curr.getCountry().getDescription())));
		}
		return mappedList;
	}
	
	public PlatePattern dtoToEntity(PlatePatternDTO dto) {
		if (dto == null) return null;
		return new PlatePattern(dto.getId(), dto.getPattern(), countryMapping.dtoToEntity(dto.getCountry()));
	}
}
