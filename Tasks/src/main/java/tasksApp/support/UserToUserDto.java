package tasksApp.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import tasksApp.dto.UserDTO;
import tasksApp.model.User;

@Component
public class UserToUserDto implements Converter<User, UserDTO>{
	
	@Override
	public UserDTO convert(User user) {
		UserDTO userDTO = new UserDTO();
		
		userDTO.setId(user.getId());
		userDTO.seteMail(user.geteMail());
		userDTO.setLastname(user.getLastname());
		userDTO.setName(user.getName());
		userDTO.setUsername(user.getUsername());
		
		return userDTO;
	}
	
	public List<UserDTO> convert(List<User> users){
        List<UserDTO> usersDTO = new ArrayList<>();

        for(User user : users) {
        	UserDTO dto = convert(user);
        	usersDTO.add(dto);
        }

        return usersDTO;
    }

}
