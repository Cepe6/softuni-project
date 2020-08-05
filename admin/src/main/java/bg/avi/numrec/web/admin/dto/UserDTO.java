package bg.avi.numrec.web.admin.dto;

import java.util.Date;

import org.springframework.util.StringUtils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
	private String username;
	private String email;
	private Date dateCreated;
	private Date lastLogin;
	
	public boolean isEmpty() {
		if (StringUtils.isEmpty(this.username) && StringUtils.isEmpty(this.email)
				&& StringUtils.isEmpty(this.dateCreated) && StringUtils.isEmpty(this.lastLogin)) return true;
		else return false;
	}

	public void removeEmpty() {
		if (this.username != null && this.username.equals("")) this.username = null;
		if (this.email != null && this.email.equals("")) this.email = null;
	}
}
