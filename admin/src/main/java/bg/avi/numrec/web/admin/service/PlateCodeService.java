package bg.avi.numrec.web.admin.service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import bg.avi.numrec.web.admin.config.Config;
import bg.avi.numrec.web.admin.dto.PlateCodeDTO;
import bg.avi.numrec.web.admin.util.HTTPRequestUtil;

@Service
public class PlateCodeService {
	
	public List<PlateCodeDTO> getAllCodes() {
		List<PlateCodeDTO> list = Collections.emptyList();	
		try {
			String jsonResponse = HTTPRequestUtil.doGet( Config.APPLICATION_BASE_PATH + "/plateCodes/findAll", "da", "da");
			if (jsonResponse != null) {
				Type listType = new TypeToken<ArrayList<PlateCodeDTO>>() {}.getType();
				list = new Gson().fromJson(jsonResponse, listType);
			}
		} catch (Exception e) {
			// Tujno
		}
		
		return list;
	}

	public List<PlateCodeDTO> getCodesByExample(PlateCodeDTO searchObject) {
		List<PlateCodeDTO> list = Collections.emptyList();
		if (searchObject.isEmpty()) {
			list = getAllCodes();
		} else {
			searchObject.removeEmpty();
			try {
				String jsonResponse = HTTPRequestUtil
						.doGetWithBody(Config.APPLICATION_BASE_PATH + "/plateCodes/find", new Gson().toJson(searchObject), "da", "da");
				if (jsonResponse != null) {
					Type listType = new TypeToken<ArrayList<PlateCodeDTO>>() {}.getType();
					list = new Gson().fromJson(jsonResponse, listType);
				}
			} catch (Exception e) {
				// Tujno
			}
		}
		
		return list;
	}

	public void saveOrUpdate(PlateCodeDTO addEditObject) {
		try {
			HTTPRequestUtil.doPost(Config.APPLICATION_BASE_PATH + "/plateCodes/update", new Gson().toJson(addEditObject), "da", "da");
		} catch (Exception e) {
			// Tujno
		}
	}

}
