package com.engsoft.marmita.service;

import com.engsoft.marmita.exceptions.NegocioException;
import com.engsoft.marmita.model.User;
import com.engsoft.marmita.model.UserDTO;
import com.engsoft.marmita.repository.UserRepository;
import com.engsoft.marmita.utils.Utils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User save(UserDTO userDTO) {
        Optional<User> user = userRepository.findByUsername(userDTO.getUsername());
        if(user.isPresent()){
            throw new NegocioException("Nome de usuario já existente");
        }else {
            User newUser = new User();
            newUser.setName(userDTO.getName());
            newUser.setUsername(userDTO.getUsername());
            newUser.setPassword(Utils.hashPassword(userDTO.getPassword()));
            newUser.addRole(userDTO.getRole());
            newUser.setActive(true);
            return userRepository.save(newUser);
        }
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(null);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public void toggleActive(User user) {
        user.setActive(!user.isActive());
        userRepository.save(user);
    }
}
