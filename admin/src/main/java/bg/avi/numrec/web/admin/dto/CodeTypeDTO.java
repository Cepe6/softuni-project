package bg.avi.numrec.web.admin.dto;

import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CodeTypeDTO {
	private String code;
	private String name;
	private String description;
	
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
		CodeTypeDTO other = (CodeTypeDTO) obj;
		return Objects.equals(code, other.code);
	}
}
