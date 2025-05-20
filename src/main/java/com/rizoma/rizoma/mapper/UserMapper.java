package com.rizoma.rizoma.mapper;

import com.rizoma.rizoma.dto.UserDTO;
import com.rizoma.rizoma.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public static UserDTO toUserDTO(User user) {
        if (user == null) return null;
        return new UserDTO(
            user.getUserId(),
            user.getUsername(),
            user.getEmail(),
            user.getUserImageUrl()
        );
    }

    public static User toEntity(UserDTO userDTO) {
        if (userDTO == null) return null;
        User user = new User();
        user.setUserId(userDTO.getIdUser());
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setUserImageUrl(userDTO.getUserImageUrl());
        return user;
    }
}
