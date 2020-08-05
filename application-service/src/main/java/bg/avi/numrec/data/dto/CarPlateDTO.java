package bg.avi.numrec.data.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CarPlateDTO {
	private String number;
	private CountryDTO country;
}
