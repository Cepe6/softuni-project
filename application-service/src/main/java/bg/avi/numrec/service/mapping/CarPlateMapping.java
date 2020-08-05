package bg.avi.numrec.service.mapping;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bg.avi.numrec.data.dto.CarPlateDTO;
import bg.avi.numrec.data.dto.CountryDTO;
import bg.avi.numrec.db.entity.CarPlate;

@Service
public class CarPlateMapping {
	@Autowired CountryMapping countryMapping;
	
	public List<CarPlateDTO> entityToDTO(List<CarPlate> entities) {
		if (entities == null || entities.isEmpty()) return Collections.emptyList();
		
		List<CarPlateDTO> mappedList = new ArrayList<>();
		for (CarPlate curr : entities) {
			mappedList.add(new CarPlateDTO(curr.getNumber(), 
					new CountryDTO(curr.getCountry().getCode(), curr.getCountry().getName(), curr.getCountry().getDescription())));
		}
		return mappedList;
	}
	
	public CarPlate dtoToEntity(CarPlateDTO dto) {
		if (dto == null) return null;
		return new CarPlate(null, dto.getNumber(), countryMapping.dtoToEntity(dto.getCountry()));
	}
}
