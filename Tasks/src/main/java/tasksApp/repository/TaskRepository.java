package tasksApp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tasksApp.model.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long>{
	
	Task findOneById(Long id);

	List<Task> findByIdIn(List<Long> ids);

}
