package tasksApp.service.impl;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import tasksApp.dto.UserPasswordChangeDto;
import tasksApp.enumeration.UserRole;
import tasksApp.model.User;
import tasksApp.repository.UserRepository;
import tasksApp.service.UserService;

@Service
public class JpaUserService implements UserService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
    private PasswordEncoder passwordEncoder;

	@Override
	public Optional<User> findOne(Long id) {
		return userRepository.findById(id);
	}

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public Page<User> findAll(int pageNumber) {
		return userRepository.findAll(PageRequest.of(pageNumber,10));
	}

	@Override
	public User save(User user) {
		user.setRole(UserRole.USER);
        return userRepository.save(user);
	}

	@Override
	public void delete(Long id) {
		userRepository.deleteById(id);
		
	}

	@Override
	public Optional<User> findbyUsername(String username) {
		return userRepository.findFirstByUsername(username);
	}

	@Override
	public boolean changePassword(Long id, UserPasswordChangeDto userPasswordChangeDto) {
		  Optional<User> rezultat = userRepository.findById(id);

	        if(!rezultat.isPresent()) {
	            throw new EntityNotFoundException();
	        }

	        User user = rezultat.get();

	        if(!user.getUsername().equals(userPasswordChangeDto.getUserName())
	                || !user.getPassword().equals(userPasswordChangeDto.getPassword())){
	            return false;
	        }

	        String password = userPasswordChangeDto.getPassword();
	        if (!userPasswordChangeDto.getPassword().equals("")) {
	            password = passwordEncoder.encode(userPasswordChangeDto.getPassword());
	        }

	        user.setPassword(password);;

	        userRepository.save(user);

	        return true;
	    }


}
