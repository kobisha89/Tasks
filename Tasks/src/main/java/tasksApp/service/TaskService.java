package tasksApp.service;

import java.util.List;

import org.springframework.data.domain.Page;

import tasksApp.model.Task;

public interface TaskService {
	
	List<Task> findAll();
	
	Task findOne(Long id);
	
	List<Task> find(List<Long> ids);
	
	Page<Task> findAll(int pageNumber);
	
	Task save (Task task);
	
	Task update (Task task);
	
	Task delete (Long id);

}
