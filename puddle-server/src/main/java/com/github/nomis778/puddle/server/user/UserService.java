package com.github.nomis778.puddle.server.user;

import com.github.nomis778.puddle.server.user.model.User;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserById(long id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty())
            throw new EntityNotFoundException("Error: User does not exist!");
        return user.get();
    }
}
