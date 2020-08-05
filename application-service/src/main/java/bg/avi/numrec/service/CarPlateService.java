package bg.avi.numrec.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import bg.avi.numrec.data.dto.CarPlateDTO;
import bg.avi.numrec.db.entity.CarPlate;
import bg.avi.numrec.db.repository.CarPlateRepository;
import bg.avi.numrec.service.mapping.CarPlateMapping;

@Service
public class CarPlateService {
	@Autowired private CarPlateRepository carPlateRepository;
	@Autowired private CarPlateMapping carPlateMapping;
	
	public List<CarPlateDTO> findAll() {
		List<CarPlate> results = carPlateRepository.findAll();
		return carPlateMapping.entityToDTO(results);
	}
	
	public List<CarPlateDTO> find(CarPlateDTO example) {
		Example<CarPlate> countryExample = Example.of(carPlateMapping.dtoToEntity(example));
		List<CarPlate> results = carPlateRepository.findAll(countryExample);
		return carPlateMapping.entityToDTO(results);
	}
	
}
