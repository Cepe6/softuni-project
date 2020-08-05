package bg.avi.numrec.web.admin.dto;

import java.util.Objects;

import org.springframework.util.StringUtils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CountryDTO {
	private String code;
	private String name;
	private String description;
	
	public boolean isEmpty() {
		if (StringUtils.isEmpty(this.code) && StringUtils.isEmpty(this.name) && StringUtils.isEmpty(this.description)) return true;
		else return false;
	}
	
	public void removeEmpty() {
		if (this.code != null && this.code.equals("")) this.code = null;
		if (this.name != null && this.name.equals("")) this.name = null;
	}

	@Override
	public String toString() {
		return code + ", " + name;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(code);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CountryDTO other = (CountryDTO) obj;
		return Objects.equals(code, other.code);
	}
}
