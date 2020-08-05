package bg.avi.numrec.web.admin.service;

import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import bg.avi.numrec.web.admin.config.Config;
import bg.avi.numrec.web.admin.dto.PlatePatternDTO;
import bg.avi.numrec.web.admin.util.HTTPRequestUtil;
import bg.avi.numrec.web.admin.utils.RecognitionPair;

@Service
public class RecognitionService {
	public String recognize(List<PlatePatternDTO> regexes, byte[] data) {
		RecognitionPair pair = new RecognitionPair(regexes.stream().map(pattern -> pattern.getPattern()).collect(Collectors.toList()), Base64.getEncoder().encodeToString(data));
		
		String jsonResponse = null;
		try {
			jsonResponse = HTTPRequestUtil.doGetWithBody( Config.APPLICATION_BASE_PATH + "/recognition/", new Gson().toJson(pair), "da", "da");
		} catch (Exception e) {
			// Tujno
		}
			
		return jsonResponse;
	}
}
