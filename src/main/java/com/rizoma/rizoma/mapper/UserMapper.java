package com.rizoma.rizoma.mapper;

import com.rizoma.rizoma.dto.UserSummaryDTO;
import com.rizoma.rizoma.model.User;

public class UserMapper {
    public static UserSummaryDTO toDTO(User user) {
        if (user == null) return null;

        return new UserSummaryDTO(
            user.getId_user(),
            user.getUsername(),
            user.getEmail(),
            user.getUserImageUrl()
        );
    }
}