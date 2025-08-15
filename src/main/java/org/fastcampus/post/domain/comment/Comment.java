package org.fastcampus.post.domain.comment;

import lombok.Getter;
import lombok.experimental.SuperBuilder;
import org.fastcampus.post.domain.common.ContentAction;
import org.fastcampus.post.domain.Post;
import org.fastcampus.post.domain.content.CommentContent;
import org.fastcampus.post.domain.content.Content;
import org.fastcampus.user.domain.User;

@Getter
@SuperBuilder
public class Comment extends ContentAction {

    private final Post post;

    public static Comment createComment(Post post, User author, String content) {
        return new Comment(null, post, author, new CommentContent(content));
    }

    public Comment(Long id, Post post, User author, Content content) {
        super(id, author, content);

        if (author == null) {
            throw new IllegalArgumentException("author cannot be null");
        }
        if (post == null) {
            throw new IllegalArgumentException("post cannot be null");
        }
        if (content == null) {
            throw new IllegalArgumentException("content cannot be null");
        }
        this.post = post;
    }

    public void updateComment(User user, String comment) {
        if (!getAuthor().equals(user)) {
            throw new IllegalArgumentException();
        }
        getContent().updateContent(comment);
    }

    public Post getPost() {
        return this.post;
    }
}
