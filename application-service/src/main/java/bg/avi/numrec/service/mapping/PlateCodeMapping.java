package bg.avi.numrec.service.mapping;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bg.avi.numrec.data.dto.CodeTypeDTO;
import bg.avi.numrec.data.dto.CountryDTO;
import bg.avi.numrec.data.dto.PlateCodeDTO;
import bg.avi.numrec.db.entity.PlateCode;

@Service
public class PlateCodeMapping {
	@Autowired CountryMapping countryMapping;
	@Autowired CodeTypeMapping codeTypeMapping;
	
	public List<PlateCodeDTO> entityToDTO(List<PlateCode> entities) {
		if (entities == null || entities.isEmpty()) return Collections.emptyList();
		
		List<PlateCodeDTO> mappedList = new ArrayList<>();
		for (PlateCode curr : entities) {
			mappedList.add(new PlateCodeDTO(curr.getId(), curr.getCode(), 
					new CountryDTO(curr.getCountry().getCode(), curr.getCountry().getName(), curr.getCountry().getDescription()),
					new CodeTypeDTO(curr.getCodeType().getCode(), curr.getCodeType().getName(), curr.getCodeType().getDescription())));
		}
		return mappedList;
	}
	
	public PlateCode dtoToEntity(PlateCodeDTO dto) {
		if (dto == null) return null;
		return new PlateCode(dto.getId(), dto.getCode(), 
				countryMapping.dtoToEntity(dto.getCountry()), codeTypeMapping.dtoToEntity(dto.getType()));
	}
}
