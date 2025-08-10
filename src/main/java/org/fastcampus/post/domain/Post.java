package org.fastcampus.post.domain;

import org.fastcampus.post.domain.common.ContentAction;
import org.fastcampus.post.domain.content.Content;
import org.fastcampus.post.domain.content.PostContent;
import org.fastcampus.post.domain.content.PostVisibleState;
import org.fastcampus.user.domain.User;

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

    public void updatePost(User user, String content, PostVisibleState visibleState) {
        if (!getAuthor().equals(user)) {
            throw new IllegalArgumentException();
        }
        getContent().updateContent(content);
        this.visibleState = visibleState;
    }

    public PostVisibleState getVisibleState() {
        return visibleState;
    }

}
