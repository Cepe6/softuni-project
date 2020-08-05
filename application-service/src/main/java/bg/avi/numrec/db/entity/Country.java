package bg.avi.numrec.db.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "countries")
@Getter @Setter @Builder
@NoArgsConstructor @AllArgsConstructor
public class Country {
	@Id
	private String code;
	public static final String _code = "code";

	@Column(name = _name)
	private String name;
	public static final String _name = "name";
	
	@Column(name = _description)
	private String description;
	public static final String _description = "description";
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "country_type_id")
	private CountryType countryType;
	public static final String _countryType = "countryType";	
}
