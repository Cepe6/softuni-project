package bg.avi.numrec.db.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "country_types")
@Getter @Setter @Builder
@NoArgsConstructor @AllArgsConstructor
public class CountryType {
	@Id
	private String code;
	public static final String _code = "code";

	@Column(name = _name)
	private String name;
	public static final String _name = "name";
	
	@Column(name = _description)
	private String description;
	public static final String _description = "description";
}
