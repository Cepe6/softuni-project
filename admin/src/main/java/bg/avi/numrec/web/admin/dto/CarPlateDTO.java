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
public class CarPlateDTO {
	private String number;
	private CountryDTO country;
	
	public boolean isEmpty() {
		if (StringUtils.isEmpty(this.number) && this.country == null) return true;
		else return false;
	}
	
	public void removeEmpty() {
		if (this.number != null && this.number.equals("")) this.number = null;
	}
}
