package com.spring.users.demo.services;

import com.spring.users.demo.models.dto.DtoMapperUser;
import com.spring.users.demo.models.dto.UserDTO;
import com.spring.users.demo.models.entities.Role;
import com.spring.users.demo.models.entities.User;
import com.spring.users.demo.models.dto.UserRequest;
import com.spring.users.demo.repositories.RoleRepository;
import com.spring.users.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    @Transactional(readOnly = true)
    public List<UserDTO> findAll() {
        List<User> users = (List<User>) repository.findAll();
        return users.stream().map(u-> DtoMapperUser.builder().setUser(u).build()).collect(Collectors.toList());

    }

    @Override
    @Transactional(readOnly = false)
    public Optional<UserDTO> update(UserRequest userRequest, Long id) {
        return repository.findById(id)
                .map(existingUser -> {
                    existingUser.setUsername(userRequest.getUsername());
                    existingUser.setEmail(userRequest.getEmail());

                    User saved = repository.save(existingUser);
                    return DtoMapperUser.builder()
                            .setUser(saved)
                            .build();
                });
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<UserDTO> findById(Long id) {
        return repository.findById(id).map(u-> DtoMapperUser.builder().setUser(u).build());
    }

    @Transactional(readOnly = false)
    @Override
    public UserDTO save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Optional<Role> o = roleRepository.findByName("ROLE_USER");
        List<Role> roles = new ArrayList<>();
        if (o.isPresent()){
            roles.add(o.orElseThrow());
        }
        user.setRoles(roles);
        return DtoMapperUser.builder().setUser(repository.save(user)).build();
    }

    @Transactional(readOnly = false)
    @Override
    public void remove(Long id) {
        repository.deleteById(id);
    }
}
