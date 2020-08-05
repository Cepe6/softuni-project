package bg.avi.numrec.web.admin.dto;

import org.springframework.util.StringUtils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlateCodeDTO {
	private Integer id;
	private String code;
	private CountryDTO country;
	private CodeTypeDTO type;
	
	public boolean isEmpty() {
		if (StringUtils.isEmpty(this.code) && this.country == null && this.type == null) return true;
		else return false;
	}
	
	public void removeEmpty() {
		if (this.code != null && this.code.equals("")) this.code = null;
	}
}
