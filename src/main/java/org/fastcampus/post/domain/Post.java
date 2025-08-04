package org.fastcampus.post.domain;

import org.fastcampus.post.domain.common.ContentAction;
import org.fastcampus.post.domain.content.PostContent;
import org.fastcampus.post.domain.content.PostVisibleState;
import org.fastcampus.user.domain.User;

public class Post extends ContentAction {

    private PostVisibleState visibleState;

    public Post(Long id, User author, PostContent content, PostVisibleState visibleState) {
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
