package com.jaisgroup.dnd.service.user;

import com.jaisgroup.dnd.dto.UserCreationDto;
import com.jaisgroup.dnd.dto.UserRoleDto;
import com.jaisgroup.dnd.enums.UserRoleName;
import com.jaisgroup.dnd.model.User;
import com.jaisgroup.dnd.repository.user.UserRepository;
import com.jaisgroup.dnd.repository.userRole.UserRoleRepository;
import com.jaisgroup.dnd.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    UserRepository userRepository;

    UserRoleRepository userRoleRepository;

    PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserRoleRepository userRoleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<User> getById(UUID id) {
        return userRepository.findById(id);
    }

    @Override
    public User persist(User user) {
        return userRepository.save(user);
    }

    @Override
    public void delete(UUID id) {
        userRepository.deleteById(id);
    }

    @Override
    public User signUp(UserCreationDto userCreationDto) {
        if (userCreationDto.getId() != null || userRepository.getUserByMail(userCreationDto.getMail()).isPresent()) {
            return null;
            // TODO create exceptions
        }

        User user = new User();
        user.setMail(userCreationDto.getMail());
        user.setFirstName(userCreationDto.getFirstName());
        user.setLastName(userCreationDto.getLastName());
        user.setCallingCode(userCreationDto.getCallingCode());
        user.setPhone(userCreationDto.getPhone());
        user.setDescription(userCreationDto.getDescription());
        user.setActive(false);
        user.setUserRole(
                userRoleRepository.getUserRoleByName(
                        Optional.ofNullable(userCreationDto.getUserRole())
                                .map(UserRoleDto::getName)
                                .map(UserRoleName::valueOf)
                                .orElse(null)
                ).orElse(null)
        );
        user.setPassword(
                passwordEncoder.encode(
                        StringUtil.isNullOrEmpty(userCreationDto.getPassword())
                                ? StringUtil.generateAlphanumericString(true)
                                : userCreationDto.getPassword()
                )
        );

        user = userRepository.saveAndFlush(user);

        // TODO mail!

        return user;
    }

    @Override
    public User changePassword(User user, String newPassword) {
        return null;
    }

    @Override
    public Optional<User> getByMail(String mail) {
        return Optional.empty();
    }

    @Override
    public User authenticate(String mail, String password) {
        Optional<User> user = userRepository.getUserByMail(mail);
        return user.filter(value -> passwordEncoder.matches(password, value.getPassword())).orElse(null);
        // TODO create exceptions
    }
}
