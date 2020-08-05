package bg.avi.numrec.db.entity.security;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Singular;

@Entity
@Table(name = "users", schema="security")
@Getter @Setter @Builder
@NoArgsConstructor @AllArgsConstructor
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = _username)
	private String username;
	public static final String _username = "username";
	
	@Column(name = _password)
	private String password;
	public static final String _password = "password";
	
	@Column(name = _email)
	private String email;
	public static final String _email = "email";
	
	@Column(name = "date_created")
	private Date dateCreated;
	public static final String _dateCreated = "dateCreated";
	
	@Column(name = "last_login")
	private Date lastLogin;
	public static final String _lastLogin = "lastLogin";
	
	@Singular
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
	  name = "n_user_authority", 
	  joinColumns = @JoinColumn(name = "user_id"), 
	  inverseJoinColumns = @JoinColumn(name = "authority_id"))
	private Set<Authority> authorities;
	public static final String _authorities = "authorities";
}
