package bg.avi.numrec.service.mapping;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import bg.avi.numrec.data.dto.CountryDTO;
import bg.avi.numrec.db.entity.Country;

@Service
public class CountryMapping {
	
	public List<CountryDTO> entityToDTO(List<Country> entities) {
		if (entities == null || entities.isEmpty()) return Collections.emptyList();
		
		List<CountryDTO> mappedList = new ArrayList<>();
		for (Country curr : entities) {
			mappedList.add(new CountryDTO(curr.getCode(), curr.getName(), curr.getDescription()));
		}
		return mappedList;
	}
	
	public Country dtoToEntity(CountryDTO dto) {
		if (dto == null) return null;
		return new Country(dto.getCode(), dto.getName(), dto.getDescription(), null);
	}
}
