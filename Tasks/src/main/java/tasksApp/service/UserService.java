package tasksApp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import tasksApp.dto.UserPasswordChangeDto;
import tasksApp.model.User;

public interface UserService {

	Optional<User> findOne(Long id);

	List<User> findAll();

	Page<User> findAll(int brojStranice);

	User save(User user);

	void delete(Long id);

	Optional<User> findbyUsername(String username);

	boolean changePassword(Long id, UserPasswordChangeDto userPasswordChangeDto);

}
