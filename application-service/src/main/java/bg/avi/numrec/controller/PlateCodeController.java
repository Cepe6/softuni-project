package bg.avi.numrec.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bg.avi.common.comm.main.ResponseMessage;
import bg.avi.common.comm.pub.MultipleResponse;
import bg.avi.numrec.data.dto.PlateCodeDTO;
import bg.avi.numrec.service.AuthorizationService;
import bg.avi.numrec.service.PlateCodeService;

@RestController
@RequestMapping("/plateCodes")
public class PlateCodeController {
	@Autowired PlateCodeService plateCodeService;
	@Autowired AuthorizationService authorizationService;
	
	@GetMapping("/findAll")
	public ResponseEntity<List<PlateCodeDTO>> findAll(@RequestHeader String authorization) {
		if (!authorizationService.isAuthorized(authorization)) {
			return new ResponseEntity<List<PlateCodeDTO>>(HttpStatus.UNAUTHORIZED);
		}
		
		return ResponseEntity.ok(plateCodeService.findAll());
	}
	
	@GetMapping("/find")
	public ResponseEntity<List<PlateCodeDTO>> find(@RequestBody PlateCodeDTO example, @RequestHeader String authorization) {
		if (!authorizationService.isAuthorized(authorization)) {
			return new ResponseEntity<List<PlateCodeDTO>>(HttpStatus.UNAUTHORIZED);
		}
		
		return ResponseEntity.ok(plateCodeService.find(example));
	}
	
	@PostMapping(value = "/update", consumes = "application/json", produces = "application/json")
	public MultipleResponse saveOrUpdate(@RequestBody PlateCodeDTO entity, @RequestHeader String authorization) {
		MultipleResponse response = new MultipleResponse();
		if (!authorizationService.isAuthorized(authorization)) {
			response.setMessage(ResponseMessage.UNAUTHORIZED);
			return response;
		}
		
		plateCodeService.saveOrUpdate(entity);
		response.setMessage(ResponseMessage.SUCCESSFULL);
		return response;
	}
}
