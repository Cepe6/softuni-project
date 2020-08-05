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

import bg.avi.numrec.data.dto.CarPlateDTO;
import bg.avi.numrec.service.AuthorizationService;
import bg.avi.numrec.service.CarPlateService;

@RestController
@RequestMapping("/plates")
public class CarPlateController {
	@Autowired CarPlateService carPlateService;
	@Autowired AuthorizationService authorizationService;
	
	@GetMapping("/findAll")
	public ResponseEntity<List<CarPlateDTO>> findAll(@RequestHeader String authorization) {
		if (!authorizationService.isAuthorized(authorization)) {
			return new ResponseEntity<List<CarPlateDTO>>(HttpStatus.UNAUTHORIZED);
		}
		
		return ResponseEntity.ok(carPlateService.findAll());
	}
	
	@GetMapping("/find")
	public ResponseEntity<List<CarPlateDTO>> find(@RequestBody CarPlateDTO example, @RequestHeader String authorization) {
		if (!authorizationService.isAuthorized(authorization)) {
			return new ResponseEntity<List<CarPlateDTO>>(HttpStatus.UNAUTHORIZED);
		}
		
		return ResponseEntity.ok(carPlateService.find(example));
	}
	
}
