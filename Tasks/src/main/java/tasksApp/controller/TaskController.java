package tasksApp.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tasksApp.dto.TaskDTO;
import tasksApp.model.Task;
import tasksApp.service.TaskService;
import tasksApp.support.TaskDtoToTask;
import tasksApp.support.TaskToTaskDto;

@RestController
@RequestMapping(value = "/api/tasks", produces = MediaType.APPLICATION_JSON_VALUE)
public class TaskController {
	
	@Autowired
	private TaskService taskService;
	
	@Autowired
	private TaskToTaskDto toTaskDto;
	
	@Autowired
	private TaskDtoToTask toTask;
	
	//@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
	@GetMapping
	public ResponseEntity<List<TaskDTO>> getAll(@RequestParam(required=false) String name,
			@RequestParam(required=false) Long sprintId,
			@RequestParam(defaultValue="0") int pageNo) {
		
		 	Page<Task> taskPage = taskService.search(name, sprintId, pageNo);
	        
	        HttpHeaders responseHeaders = new HttpHeaders();
	        responseHeaders.set("Total-Pages", taskPage.getTotalPages() + "");
	        
	        return new ResponseEntity<>(toTaskDto.convert(taskPage.getContent()), responseHeaders, HttpStatus.OK);
	
	}
	
	//@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
	@GetMapping("/{id}")
	public ResponseEntity <TaskDTO> get(@PathVariable Long id) {
		Task task = taskService.findOne(id);
		
		if(task != null) {
            return new ResponseEntity<>(toTaskDto.convert(task), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
	}
	
	//@PreAuthorize("hasRole('ADMIN')")
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity <TaskDTO> create (@Valid @RequestBody TaskDTO taskDTO) {
		Task task = toTask.convert(taskDTO);
		Task savedTask = taskService.save(task);
		
		return new ResponseEntity<>(toTaskDto.convert(savedTask), HttpStatus.CREATED);
	}
	
	//@PreAuthorize("hasRole('ADMIN')")
	@PutMapping(value = "/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TaskDTO> update(@PathVariable Long id, @Valid @RequestBody TaskDTO taskDTO){

        if(!id.equals(taskDTO.getId())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

		Task task = toTask.convert(taskDTO);
		Task savedTask = taskService.save(task);

        return new ResponseEntity<>(toTaskDto.convert(savedTask),HttpStatus.OK);
    }
	
	//@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
		Task deletedTask = taskService.delete(id);

        if(deletedTask != null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
