package org.fastcampus.post.repository.convert;

import jakarta.persistence.AttributeConverter;
import org.fastcampus.post.domain.content.PostVisibleState;

public class PostVisibleStateConverter implements AttributeConverter<PostVisibleState, String> {

    @Override
    public String convertToDatabaseColumn(PostVisibleState visibleState) {
        if (visibleState == null) {
            return null;
        }
        return visibleState.name();
    }

    @Override
    public PostVisibleState convertToEntityAttribute(String s) {
        return PostVisibleState.valueOf(s);
    }
}
