package tasksApp.support;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import tasksApp.dto.UserDTO;
import tasksApp.model.User;

@Component
public class UserToUserDto implements Converter<User, UserDTO>{
	
	@Override
	public UserDTO convert(User source) {
		UserDTO target = new UserDTO();
		
		target.setId(source.getId());
		target.setUsername(source.getUsername());
		
		return target;
	}
}
