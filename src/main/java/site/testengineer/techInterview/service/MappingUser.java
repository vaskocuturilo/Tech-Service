package site.testengineer.techInterview.service;

import org.springframework.stereotype.Service;
import site.testengineer.techInterview.model.User;
import site.testengineer.techInterview.model.UserDto;

@Service
public class MappingUser {

    public UserDto mapToUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setExercises(user.getExercises());

        return userDto;
    }

    public User mapToUser(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setExercises(userDto.getExercises());

        return user;
    }
}
