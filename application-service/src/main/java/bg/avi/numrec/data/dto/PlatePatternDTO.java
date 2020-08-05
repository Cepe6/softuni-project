package bg.avi.numrec.data.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PlatePatternDTO {
	private Integer id;
	private String pattern;
	private CountryDTO country;
}
