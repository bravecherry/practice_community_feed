package org.fastcampus.post.application.dto;

import java.util.List;
import org.fastcampus.post.application.dto.db.PostWithCommentCount;
import org.fastcampus.user.domain.User;

public class ReadFeedListResDto {
    List<ReadFeedResDto> list;

    public ReadFeedListResDto(List<PostWithCommentCount> list, User user) {
        this.list = list.stream().map(it -> new ReadFeedResDto(it, user)).toList();
    }
}
