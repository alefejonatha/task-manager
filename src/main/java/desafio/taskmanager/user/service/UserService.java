package desafio.taskmanager.user.service;


import desafio.taskmanager.security.PasswordEncodingConfig;
import desafio.taskmanager.user.dto.UserDTO;
import desafio.taskmanager.user.dto.UserPutDTO;
import desafio.taskmanager.user.entity.User;
import desafio.taskmanager.user.exception.UserAlreadyExistsException;
import desafio.taskmanager.user.exception.UserNotFoundException;
import desafio.taskmanager.user.mapper.UserMapper;
import desafio.taskmanager.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncodingConfig passwordEncodingConfig;

    public void save(UserDTO userDTO) {
        verifyIfExists(userDTO.getUsername());
        User userToCreate = UserMapper.INSTANCE.toUser(userDTO);
        userToCreate.setPassword(passwordEncodingConfig.passwordEncoder().encode(userToCreate.getPassword()));
        userRepository.save(userToCreate);
    }

    public void update(UserPutDTO userPutDTO) {
        verifyAndGetById(userPutDTO.getId());
        User userToUpdate = UserMapper.INSTANCE.toUser(userPutDTO);
        userToUpdate.setPassword(passwordEncodingConfig.passwordEncoder().encode(userToUpdate.getPassword()));

        userRepository.save(userToUpdate);
    }

    public void delete(Long id) {
        userRepository.delete(verifyAndGetById(id));
    }

    private User verifyAndGetById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    private void verifyIfExists(String username) {
        Optional<User> foundUser = userRepository.findByUsername(username);
        if (foundUser.isPresent()) {
            throw new UserAlreadyExistsException(username);
        }
    }
}
