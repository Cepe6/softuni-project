package bg.avi.numrec.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import bg.avi.numrec.data.dto.PlateCodeDTO;
import bg.avi.numrec.db.entity.PlateCode;
import bg.avi.numrec.db.repository.PlateCodeRepository;
import bg.avi.numrec.service.mapping.PlateCodeMapping;

@Service
public class PlateCodeService {
	@Autowired private PlateCodeRepository plateCodeRepository;
	@Autowired private PlateCodeMapping plateCodeMapping;
	
	public List<PlateCodeDTO> findAll() {
		List<PlateCode> results = plateCodeRepository.findAll();
		return plateCodeMapping.entityToDTO(results);
	}
	
	public List<PlateCodeDTO> find(PlateCodeDTO example) {
		Example<PlateCode> countryExample = Example.of(plateCodeMapping.dtoToEntity(example));
		List<PlateCode> results = plateCodeRepository.findAll(countryExample);
		return plateCodeMapping.entityToDTO(results);
	}
	
	public void saveOrUpdate(PlateCodeDTO entity) {
		plateCodeRepository.save(plateCodeMapping.dtoToEntity(entity));
	}
}
