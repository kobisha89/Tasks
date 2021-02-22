package tasksApp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tasksApp.model.Sprint;

@Repository
public interface SprintRepository extends JpaRepository<Sprint, Long>{
	
	Sprint findOneById(Long id);

	List<Sprint> findByIdIn(List<Long> ids);

}
