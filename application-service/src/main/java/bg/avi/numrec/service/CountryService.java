package bg.avi.numrec.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import bg.avi.numrec.data.dto.CountryDTO;
import bg.avi.numrec.db.entity.Country;
import bg.avi.numrec.db.repository.CountryRepository;
import bg.avi.numrec.service.mapping.CountryMapping;

@Service
public class CountryService {
	@Autowired private CountryRepository countryRepository;
	@Autowired private CountryMapping countryMapping;
	
	public List<CountryDTO> findAll() {
		List<Country> results = countryRepository.findAll();
		return countryMapping.entityToDTO(results);
	}
	
	public List<CountryDTO> find(CountryDTO example) {
		Example<Country> countryExample = Example.of(countryMapping.dtoToEntity(example));
		List<Country> results = countryRepository.findAll(countryExample);
		return countryMapping.entityToDTO(results);
	}
	
	public void saveOrUpdate(CountryDTO entity) {
		countryRepository.save(countryMapping.dtoToEntity(entity));
	}
}
