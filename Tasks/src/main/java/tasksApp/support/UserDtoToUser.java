package tasksApp.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import tasksApp.dto.UserDTO;
import tasksApp.model.User;
import tasksApp.service.UserService;

@Component
public class UserDtoToUser implements Converter<UserDTO, User>{
	
	@Autowired
	private UserService userService;

	@Override
	public User convert(UserDTO userDTO) {
		User user = null;
		if (userDTO.getId() != null) {
			user = userService.findOne(userDTO.getId()).get();
		}
		
		if (user == null) {
			user = new User();
		}
		
		user.setUsername(userDTO.getUsername());
		user.setLastname(userDTO.getLastname());
		user.setName(userDTO.getName());
		user.seteMail(userDTO.geteMail());
		
		
		return user;
	}

}
