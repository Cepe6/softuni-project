package bg.avi.numrec.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bg.avi.numrec.data.dto.CodeTypeDTO;
import bg.avi.numrec.db.entity.CodeType;
import bg.avi.numrec.db.repository.CodeTypeRepository;
import bg.avi.numrec.service.mapping.CodeTypeMapping;

@Service
public class CodeTypeService {
	@Autowired private CodeTypeRepository codeTypeRepository;
	@Autowired private CodeTypeMapping codeTypeMapping;
	
	public List<CodeTypeDTO> findAll() {
		List<CodeType> results = codeTypeRepository.findAll();
		return codeTypeMapping.entityToDTO(results);
	}
}
