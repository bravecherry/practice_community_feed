package org.fastcampus.post.application.dto;

import java.time.LocalDateTime;
import org.fastcampus.post.application.dto.db.PostWithCommentCount;
import org.fastcampus.post.domain.Post;
import org.fastcampus.user.domain.User;

public class ReadFeedResDto {
    private final ContentInfo contentInfo;
    private final UserInfo userInfo;

    public static class ContentInfo {
        private final Long id;
        private final String content;
        private final Integer likedCount;
        private final Integer commentCount;
        private final Boolean liked;
        private final Boolean isEdited;
        private final LocalDateTime regDtm;
        private final LocalDateTime modDtm;

        public ContentInfo(PostWithCommentCount postWithCommentCount) {
            Post post = postWithCommentCount.post();
            this.id = post.getId();
            this.content = post.getContent();
            this.likedCount = post.getLikeCount();
            this.commentCount = postWithCommentCount.commentCount();
            this.liked = postWithCommentCount.liked();
            this.isEdited = post.isEdited();
            this.regDtm = post.getRegDtm();
            this.modDtm = post.getUpdateDtm();
        }
    }

    public static class UserInfo {
        private final Long id;
        private final String name;
        private final String profileImageUrl;

        public UserInfo(User user) {
            this.id = user.getId();
            this.name = user.getUserInfo().getName();
            this.profileImageUrl = user.getUserInfo().getProfileImageUrl();
        }
    }

    public ReadFeedResDto(PostWithCommentCount post, User user) {
        this.contentInfo = new ContentInfo(post);
        this.userInfo = new UserInfo(user);
    }

}
