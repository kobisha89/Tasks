package tasksApp.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import tasksApp.dto.TaskDTO;
import tasksApp.model.Task;
import tasksApp.service.SprintService;
import tasksApp.service.StateService;
import tasksApp.service.TaskService;

@Component
public class TaskDtoToTask implements Converter<TaskDTO, Task>{
	
	@Autowired
	private TaskService taskService;
	
	@Autowired
	private StateService stateService;
	
	@Autowired
	private SprintService sprintService;

	@Override
	public Task convert(TaskDTO taskDto) {
		Task task;
		
		if (taskDto.getId() == null) {
			task = new Task();
		} else {
			task = taskService.findOne(taskDto.getId());
		}
		
		if (task != null) {
			task.setPoints(taskDto.getPoints());
			task.setName(taskDto.getName());
			task.setEmployee(taskDto.getEmployee());
			
			if (taskDto.getState() != null) {
				task.setState(stateService.findOne(taskDto.getState().getId()));
			}
			if (taskDto.getSprint() != null) {
				task.setSprint(sprintService.findOne(taskDto.getSprint().getId()));
			}
		}
		return task;
	}

}
