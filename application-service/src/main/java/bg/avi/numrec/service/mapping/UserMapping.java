package bg.avi.numrec.service.mapping;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import bg.avi.numrec.data.dto.UserDTO;
import bg.avi.numrec.db.entity.security.User;

@Service
public class UserMapping {
	
	public List<UserDTO> entityToDTO(List<User> entities) {
		if (entities == null || entities.isEmpty()) return Collections.emptyList();
		
		List<UserDTO> mappedList = new ArrayList<>();
		for (User curr : entities) {
			mappedList.add(new UserDTO(curr.getUsername(), curr.getEmail(), curr.getDateCreated(), curr.getLastLogin()));
		}
		return mappedList;
	}
	
	public User dtoToEntity(UserDTO dto) {
		if (dto == null) return null;
		return new User(null, dto.getUsername(), null, dto.getEmail(), dto.getDateCreated(), dto.getLastLogin(), null);
	}
}
