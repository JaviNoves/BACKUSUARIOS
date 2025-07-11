package com.spring.users.demo.models.dto;

import com.spring.users.demo.models.entities.User;

public class DtoMapperUser {



    private User user;

    private DtoMapperUser(){

    }

    public static DtoMapperUser builder(){
        return new DtoMapperUser();
    }

    public DtoMapperUser setUser(User user) {
        this.user = user;
        return this;
    }

    public UserDTO build(){

        if (user == null){
            throw new RuntimeException("Debe pasar el entity user");
        }
        return new UserDTO(this.user.getId(), user.getUsername(),user.getEmail());
    }


}
