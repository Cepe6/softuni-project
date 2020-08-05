package bg.avi.numrec.web.admin.service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import bg.avi.numrec.web.admin.config.Config;
import bg.avi.numrec.web.admin.dto.CarPlateDTO;
import bg.avi.numrec.web.admin.util.HTTPRequestUtil;

@Service
public class CarPlateService {
	
	public List<CarPlateDTO> getAllPlates() {
		List<CarPlateDTO> list = Collections.emptyList();	
		try {
			String jsonResponse = HTTPRequestUtil.doGet( Config.APPLICATION_BASE_PATH + "/plates/findAll", "da", "da");
			if (jsonResponse != null) {
				Type listType = new TypeToken<ArrayList<CarPlateDTO>>() {}.getType();
				list = new Gson().fromJson(jsonResponse, listType);
			}
		} catch (Exception e) {
			// Tujno
		}
		
		return list;
	}

	public List<CarPlateDTO> getPlatesByExample(CarPlateDTO searchObject) {
		List<CarPlateDTO> list = Collections.emptyList();
		if (searchObject.isEmpty()) {
			list = getAllPlates();
		} else {
			searchObject.removeEmpty();
			try {
				String jsonResponse = HTTPRequestUtil
						.doGetWithBody(Config.APPLICATION_BASE_PATH + "/plates/find", new Gson().toJson(searchObject), "da", "da");
				if (jsonResponse != null) {
					Type listType = new TypeToken<ArrayList<CarPlateDTO>>() {}.getType();
					list = new Gson().fromJson(jsonResponse, listType);
				}
			} catch (Exception e) {
				// Tujno
			}
		}
		
		return list;
	}
	
}
