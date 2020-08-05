package bg.avi.numrec.service.mapping;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import bg.avi.numrec.data.dto.CodeTypeDTO;
import bg.avi.numrec.db.entity.CodeType;

@Service
public class CodeTypeMapping {
	
	public List<CodeTypeDTO> entityToDTO(List<CodeType> entities) {
		if (entities == null || entities.isEmpty()) return Collections.emptyList();
		
		List<CodeTypeDTO> mappedList = new ArrayList<>();
		for (CodeType curr : entities) {
			mappedList.add(new CodeTypeDTO(curr.getCode(), curr.getName(), curr.getDescription()));
		}
		return mappedList;
	}

	public CodeType dtoToEntity(CodeTypeDTO dto) {
		if (dto == null) return null;
		return new CodeType(dto.getCode(), dto.getName(), dto.getDescription());
	}
}
