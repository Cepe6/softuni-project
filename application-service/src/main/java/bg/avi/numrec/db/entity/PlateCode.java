package bg.avi.numrec.db.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "plate_codes")
@Getter @Setter @Builder
@NoArgsConstructor @AllArgsConstructor
public class PlateCode {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = _code)
	private String code;
	public static final String _code = "code";

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "country_id")
	private Country country;
	public static final String _country = "country";
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "code_type_id")
	private CodeType codeType;
	public static final String _codeType = "codeType";
}
