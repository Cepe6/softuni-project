package bg.avi.numrec.data.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PlateCodeDTO {
	private Integer id;
	private String code;
	private CountryDTO country;
	private CodeTypeDTO type;
}
