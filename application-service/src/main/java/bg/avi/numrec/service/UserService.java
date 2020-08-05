package bg.avi.numrec.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import bg.avi.numrec.data.dto.UserDTO;
import bg.avi.numrec.db.entity.security.User;
import bg.avi.numrec.db.repository.UserRepository;
import bg.avi.numrec.service.mapping.UserMapping;

@Service
public class UserService {
	@Autowired private UserRepository userRepository;
	@Autowired private UserMapping userMapping;
	
	public List<UserDTO> findAll() {
		List<User> results = userRepository.findAll();
		return userMapping.entityToDTO(results);
	}
	
	public List<UserDTO> find(UserDTO example) {
		Example<User> countryExample = Example.of(userMapping.dtoToEntity(example));
		List<User> results = userRepository.findAll(countryExample);
		return userMapping.entityToDTO(results);
	}
	
	public boolean checkExistingUser(String username, String password) {
		return userRepository.checkIfUserExists(username, password);
	}
	
}
