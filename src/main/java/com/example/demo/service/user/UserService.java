package com.example.demo.service.user;

import com.example.demo.dto.UserCreationDto;
import com.example.demo.model.User;
import com.example.demo.service.AbstractService;

import java.util.Optional;
import java.util.UUID;

public interface UserService extends AbstractService<User, UUID> {

    User signUp(UserCreationDto userDto);

    User changePassword(User user, String newPassword);

    Optional<User> getByMail(String mail);

    User authenticate(String mail, String password);

}
