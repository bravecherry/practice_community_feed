package org.fastcampus.post.domain;

import lombok.Getter;
import lombok.experimental.SuperBuilder;
import org.fastcampus.post.domain.common.ContentAction;
import org.fastcampus.post.domain.content.Content;
import org.fastcampus.post.domain.content.PostContent;
import org.fastcampus.post.domain.content.PostVisibleState;
import org.fastcampus.user.domain.User;

@Getter
@SuperBuilder
public class Post extends ContentAction {

    private PostVisibleState visibleState;

    public static Post createPost(User author, String content, PostVisibleState visibleState) {
        return new Post(null, author, new PostContent(content), visibleState);
    }

    public Post(Long id, User author, Content content, PostVisibleState visibleState) {
        super(id, author, content);

        if (author == null) {
            throw new IllegalArgumentException("author cannot be null");
        }

        this.visibleState = visibleState;
    }

    public void updatePost(String content, PostVisibleState visibleState) {
        getContent().updateContent(content);
        this.visibleState = visibleState;
    }

}
