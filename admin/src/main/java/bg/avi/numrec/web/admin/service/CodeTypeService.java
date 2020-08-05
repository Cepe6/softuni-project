package bg.avi.numrec.web.admin.service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import bg.avi.numrec.web.admin.config.Config;
import bg.avi.numrec.web.admin.dto.CodeTypeDTO;
import bg.avi.numrec.web.admin.util.HTTPRequestUtil;

@Service
public class CodeTypeService {

	public List<CodeTypeDTO> getAllCodeTypes() {
		List<CodeTypeDTO> list = Collections.emptyList();	
		try {
			String jsonResponse = HTTPRequestUtil.doGet( Config.APPLICATION_BASE_PATH + "/codeTypes/findAll", "da", "da");
			if (jsonResponse != null) {
				Type listType = new TypeToken<ArrayList<CodeTypeDTO>>() {}.getType();
				list = new Gson().fromJson(jsonResponse, listType);
			}
		} catch (Exception e) {
			// Tujno
		}
		
		return list;
	}
	
}
