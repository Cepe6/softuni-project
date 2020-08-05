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
public class PlatePatternDTO {
	private Integer id;
	private String pattern;
	private CountryDTO country;
	
	public boolean isEmpty() {
		if (StringUtils.isEmpty(this.pattern) && this.country == null) return true;
		else return false;
	}
	
	public void removeEmpty() {
		if (this.pattern != null && this.pattern.equals("")) this.pattern = null;
	}
}
