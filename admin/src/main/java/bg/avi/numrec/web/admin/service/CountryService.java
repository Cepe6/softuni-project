package bg.avi.numrec.web.admin.service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import bg.avi.numrec.web.admin.config.Config;
import bg.avi.numrec.web.admin.dto.CountryDTO;
import bg.avi.numrec.web.admin.util.HTTPRequestUtil;

@Service
public class CountryService {
	
	public List<CountryDTO> getAllCountries() {
		List<CountryDTO> list = Collections.emptyList();	
		try {
			String jsonResponse = HTTPRequestUtil.doGet( Config.APPLICATION_BASE_PATH + "/countries/findAll", "bg", "da");
			if (jsonResponse != null) {
				Type listType = new TypeToken<ArrayList<CountryDTO>>() {}.getType();
				list = new Gson().fromJson(jsonResponse, listType);
			}
		} catch (Exception e) {
			// Tujno
		}
		
		return list;
	}

	public List<CountryDTO> getCountriesByExample(CountryDTO searchObject) {
		List<CountryDTO> list = Collections.emptyList();
		if (searchObject.isEmpty()) {
			list = getAllCountries();
		} else {
			searchObject.removeEmpty();
			try {
				String jsonResponse = HTTPRequestUtil
						.doGetWithBody(Config.APPLICATION_BASE_PATH + "/countries/find", new Gson().toJson(searchObject), "da", "da");
				if (jsonResponse != null) {
					Type listType = new TypeToken<ArrayList<CountryDTO>>() {}.getType();
					list = new Gson().fromJson(jsonResponse, listType);
				}
			} catch (Exception e) {
				// Tujno
			}
		}
		
		return list;
	}

	public void saveOrUpdate(CountryDTO addEditObject) {
		try {
			HTTPRequestUtil.doPost(Config.APPLICATION_BASE_PATH + "/countries/update", new Gson().toJson(addEditObject), "da", "da");
		} catch (Exception e) {
			// Tujno
		}
	}

}
