package tasksApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tasksApp.dto.StateDTO;
import tasksApp.dto.TaskDTO;
import tasksApp.model.State;
import tasksApp.model.Task;
import tasksApp.service.StateService;
import tasksApp.service.TaskService;
import tasksApp.support.StateToStateDto;
import tasksApp.support.TaskToTaskDto;

@RestController
@RequestMapping(value = "/api/states", produces = MediaType.APPLICATION_JSON_VALUE)
public class StateController {
	
	@Autowired
	private StateService stateService;
	
	@Autowired
	private TaskService taskService;
	
	@Autowired
	private StateToStateDto toStateDto;
	
	@Autowired
	private TaskToTaskDto toTaskDto;
	
	@GetMapping
	public ResponseEntity<List<StateDTO>> getAll () {
		
		List<State> states = stateService.findAll();
		
		return new ResponseEntity<>(toStateDto.convert(states), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity <TaskDTO> changeState(@PathVariable Long id) {
		
		Task task = taskService.findOne(id);
		if (task.getState().getName().equals("NEW")) {
			task.setState(stateService.findOne(2L));
			taskService.update(task);
		} else if (task.getState().getName().equals("IN PROGRESS")) {
			task.setState(stateService.findOne(3L));
			taskService.update(task);
		}
		
		return new ResponseEntity<>(toTaskDto.convert(task), HttpStatus.OK);

	}
}
