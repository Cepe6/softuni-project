package bg.avi.numrec.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bg.avi.numrec.data.dto.CodeTypeDTO;
import bg.avi.numrec.service.AuthorizationService;
import bg.avi.numrec.service.CodeTypeService;

@RestController
@RequestMapping("/codeTypes")
public class CodeTypeController {
	@Autowired CodeTypeService plateCodeService;
	@Autowired AuthorizationService authorizationService;
	
	@GetMapping("/findAll")
	public ResponseEntity<List<CodeTypeDTO>> findAll(@RequestHeader String authorization) {
		if (!authorizationService.isAuthorized(authorization)) {
			return new ResponseEntity<List<CodeTypeDTO>>(HttpStatus.UNAUTHORIZED);
		}
		
		return ResponseEntity.ok(plateCodeService.findAll());
	}
	
}
