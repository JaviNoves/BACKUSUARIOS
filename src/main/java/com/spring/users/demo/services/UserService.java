package com.spring.users.demo.services;



import com.spring.users.demo.models.dto.UserDTO;
import com.spring.users.demo.models.entities.User;
import com.spring.users.demo.models.dto.UserRequest;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<UserDTO> findAll();
    Optional<UserDTO> findById(Long id);
    UserDTO save(User user);
    Optional<UserDTO>  update(UserRequest user, Long id);
    void remove(Long id);

}
