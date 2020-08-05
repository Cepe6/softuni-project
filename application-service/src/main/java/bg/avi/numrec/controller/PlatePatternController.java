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
import bg.avi.numrec.data.dto.PlatePatternDTO;
import bg.avi.numrec.service.AuthorizationService;
import bg.avi.numrec.service.PlatePatternService;

@RestController
@RequestMapping("/platePatterns")
public class PlatePatternController {
	@Autowired PlatePatternService platePatternService;
	@Autowired AuthorizationService authorizationService;
	
	@GetMapping("/findAll")
	public ResponseEntity<List<PlatePatternDTO>> findAll(@RequestHeader String authorization) {
		if (!authorizationService.isAuthorized(authorization)) {
			return new ResponseEntity<List<PlatePatternDTO>>(HttpStatus.UNAUTHORIZED);
		}
		
		return ResponseEntity.ok(platePatternService.findAll());
	}
	
	@GetMapping("/find")
	public ResponseEntity<List<PlatePatternDTO>> find(@RequestBody PlatePatternDTO example, @RequestHeader String authorization) {
		if (!authorizationService.isAuthorized(authorization)) {
			return new ResponseEntity<List<PlatePatternDTO>>(HttpStatus.UNAUTHORIZED);
		}
		
		return ResponseEntity.ok(platePatternService.find(example));
	}
	
	@PostMapping(value = "/update", consumes = "application/json", produces = "application/json")
	public MultipleResponse saveOrUpdate(@RequestBody PlatePatternDTO entity, @RequestHeader String authorization) {
		MultipleResponse response = new MultipleResponse();
		if (!authorizationService.isAuthorized(authorization)) {
			response.setMessage(ResponseMessage.UNAUTHORIZED);
			return response;
		}
		
		platePatternService.saveOrUpdate(entity);
		response.setMessage(ResponseMessage.SUCCESSFULL);
		return response;
	}
}
