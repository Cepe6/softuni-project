package bg.avi.numrec.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import bg.avi.numrec.data.dto.PlatePatternDTO;
import bg.avi.numrec.db.entity.PlatePattern;
import bg.avi.numrec.db.repository.PlatePatternRepository;
import bg.avi.numrec.service.mapping.PlatePatternMapping;

@Service
public class PlatePatternService {
	@Autowired private PlatePatternRepository platePatternRepository;
	@Autowired private PlatePatternMapping platePatternMapping;
	
	public List<PlatePatternDTO> findAll() {
		List<PlatePattern> results = platePatternRepository.findAll();
		return platePatternMapping.entityToDTO(results);
	}
	
	public List<PlatePatternDTO> find(PlatePatternDTO example) {
		Example<PlatePattern> countryExample = Example.of(platePatternMapping.dtoToEntity(example));
		List<PlatePattern> results = platePatternRepository.findAll(countryExample);
		return platePatternMapping.entityToDTO(results);
	}
	
	public void saveOrUpdate(PlatePatternDTO entity) {
		platePatternRepository.save(platePatternMapping.dtoToEntity(entity));
	}
}
