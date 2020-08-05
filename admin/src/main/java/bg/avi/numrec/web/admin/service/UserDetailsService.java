package bg.avi.numrec.web.admin.service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import bg.avi.numrec.web.admin.config.Config;
import bg.avi.numrec.web.admin.dto.UserDTO;
import bg.avi.numrec.web.admin.util.HTTPRequestUtil;

@Service
public class UserDetailsService {

	public List<UserDTO> getAllUsers() {
		List<UserDTO> list = Collections.emptyList();
		try {
			String jsonResponse = HTTPRequestUtil.doGet( Config.APPLICATION_BASE_PATH + "/users/findAll", "da", "da");
			if (jsonResponse != null) {
				Type listType = new TypeToken<ArrayList<UserDTO>>() {}.getType();
				list = new Gson().fromJson(jsonResponse, listType);
			}
		} catch (Exception e) {
			// Tujno
		}
		
		return list;
	}

	public List<UserDTO> getUsersByExample(UserDTO searchObject) {
		List<UserDTO> list = Collections.emptyList();
		if (searchObject.isEmpty()) {
			list = getAllUsers();
		} else {
			searchObject.removeEmpty();
			try {
				String jsonResponse = HTTPRequestUtil
						.doGetWithBody(Config.APPLICATION_BASE_PATH + "/users/find", new Gson().toJson(searchObject), "da", "da");
				if (jsonResponse != null) {
					Type listType = new TypeToken<ArrayList<UserDTO>>() {}.getType();
					list = new Gson().fromJson(jsonResponse, listType);
				}
			} catch (Exception e) {
				// Tujno
			}
		}
		
		return list;
	}

}
