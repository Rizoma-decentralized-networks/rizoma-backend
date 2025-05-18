package com.rizoma.rizoma.mapper;

import com.rizoma.rizoma.dto.*;
import com.rizoma.rizoma.model.*;

public class MarkMapper {

    public static MarkDTO toDTO(Mark mark) {
        return new MarkDTO(
            mark.getId_mark(),
            mark.getTitle(),
            mark.getDescription(),
            mark.getLocation(),
            mark.getImageUrl(),
            new UserSummaryDTO(
                mark.getUser().getId_user(),
                mark.getUser().getUsername(),
                mark.getUser().getEmail(),
                mark.getUser().getUserImageUrl()
            ),
            new CategoryDTO(
                mark.getCategory().getId_category(),
                mark.getCategory().getCategory()
            ),
            new TagDTO(
                mark.getTag().getId_tag(),
                mark.getTag().getTag()
            )
        );
    }
}
