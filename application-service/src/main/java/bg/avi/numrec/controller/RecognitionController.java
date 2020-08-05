package bg.avi.numrec.controller;

import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bg.avi.numrec.service.AuthorizationService;
import bg.avi.numrec.service.RecognitionService;
import bg.avi.numrec.utils.RecognitionPair;

@RestController
@RequestMapping("/recognition")
public class RecognitionController {
	@Autowired RecognitionService recognitionService;
	@Autowired AuthorizationService authorizationService;
	
	@GetMapping(value = "/recognize", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> recognize(@RequestHeader String authorization, @RequestBody(required = true) RecognitionPair data) throws IOException {
		if (!authorizationService.isAuthorized(authorization)) {
			return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
		}
		
		String result = recognitionService.recognize(data.getRegexes(), Base64.getDecoder().decode(data.getData()));
		return ResponseEntity.ok(result);
	}
}
