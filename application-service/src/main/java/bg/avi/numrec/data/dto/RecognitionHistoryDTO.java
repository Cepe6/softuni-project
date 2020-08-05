package bg.avi.numrec.data.dto;

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
public class RecognitionHistoryDTO {
	private Integer id;
	private CountryDTO country;
	private String plate;
	private Date date;
	
	public boolean isEmpty() {
		if (StringUtils.isEmpty(this.id) && this.country == null && this.plate == null && this.date == null) return true;
		else return false;
	}
	
	public void removeEmpty() {
		if (this.id != null && this.plate.equals("")) this.id = null;
	}
}
