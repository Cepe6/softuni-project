package bg.avi.numrec.contoller;

import java.io.InputStream;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import bg.avi.numrec.controller.RecognitionController;
import bg.avi.numrec.data.dto.CountryDTO;
import bg.avi.numrec.data.dto.PlatePatternDTO;
import bg.avi.numrec.service.PlatePatternService;
import bg.avi.numrec.utils.RecognitionPair;

@ExtendWith(SpringExtension.class)
@WebMvcTest(RecognitionController.class)
public class RecognitionContollerTest {
	private static final PlatePatternDTO BG_PLATE_PATTERN_EXAMPLE = new PlatePatternDTO(null, null,
			new CountryDTO("BG", null, null));
	private static final PlatePatternDTO IN_PLATE_PATTERN_EXAMPLE = new PlatePatternDTO(null, null,
			new CountryDTO("IN", null, null));
	private static final PlatePatternDTO GB_PLATE_PATTERN_EXAMPLE = new PlatePatternDTO(null, null,
			new CountryDTO("GB", null, null));

	@Autowired
	private MockMvc mvc;

	@Autowired
	private PlatePatternService platePatternService;

	@Test
	void testRecognizePlate1() throws JsonProcessingException, Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		InputStream in = getClass().getResourceAsStream("/test/test-subjects/plate1.jpg");
		
		List<String> patterns = platePatternService.find(BG_PLATE_PATTERN_EXAMPLE).stream()
				.map(PlatePatternDTO::getPattern).collect(Collectors.toList());
		RecognitionPair testPair = new RecognitionPair(patterns, Base64.getEncoder().encodeToString(IOUtils.toByteArray(in)));
		mvc.perform(MockMvcRequestBuilders.get("/recognize").header("authorization", "da", "da")
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(objectMapper.writeValueAsString(testPair)))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.value").value("СА9198ВТ"));
	}
}
