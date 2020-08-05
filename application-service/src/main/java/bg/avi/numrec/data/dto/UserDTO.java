package bg.avi.numrec.data.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserDTO {
	private String username;
	private String email;
	private Date dateCreated;
	private Date lastLogin;
}
