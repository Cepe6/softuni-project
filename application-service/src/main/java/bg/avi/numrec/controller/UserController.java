package bg.avi.numrec.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bg.avi.numrec.data.dto.UserDTO;
import bg.avi.numrec.service.AuthorizationService;
import bg.avi.numrec.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired UserService userService;
	@Autowired AuthorizationService authorizationService;
	
	@GetMapping("/findAll")
	public ResponseEntity<List<UserDTO>> findAll(@RequestHeader String authorization) {
		if (!authorizationService.isAuthorized(authorization)) {
			return new ResponseEntity<List<UserDTO>>(HttpStatus.UNAUTHORIZED);
		}
		
		return ResponseEntity.ok(userService.findAll());
	}
	
	@GetMapping("/find")
	public ResponseEntity<List<UserDTO>> find(@RequestBody UserDTO example, @RequestHeader String authorization) {
		if (!authorizationService.isAuthorized(authorization)) {
			return new ResponseEntity<List<UserDTO>>(HttpStatus.UNAUTHORIZED);
		}
		
		return ResponseEntity.ok(userService.find(example));
	}
	
}
