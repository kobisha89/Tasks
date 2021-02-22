package tasksApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tasksApp.dto.StateDTO;
import tasksApp.model.State;
import tasksApp.service.StateService;
import tasksApp.support.StateToStateDto;

@RestController
@RequestMapping(value = "/api/states", produces = MediaType.APPLICATION_JSON_VALUE)
public class StateController {
	
	@Autowired
	private StateService stateService;
	
	@Autowired
	private StateToStateDto toStateDto;
	
	@GetMapping
	public ResponseEntity<List<StateDTO>> getAll () {
		
		List<State> states = stateService.findAll();
		
		return new ResponseEntity<>(toStateDto.convert(states), HttpStatus.OK);
	}

}
