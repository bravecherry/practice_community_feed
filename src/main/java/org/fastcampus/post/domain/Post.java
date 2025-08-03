package org.fastcampus.post.domain;

import java.time.LocalDateTime;
import org.fastcampus.common.domain.PositiveIntegerCounter;
import org.fastcampus.post.domain.content.PostContent;
import org.fastcampus.post.domain.content.PostVisibleState;
import org.fastcampus.user.domain.User;

public class Post {

    private final Long id;
    //id 설정이 테스트 세팅할때는 편할 수 있다.
//    private final long authorId;
    private final User author;
    private final PostContent content;
    private final PositiveIntegerCounter likeCounter;
    private PostVisibleState visibleState;

    public Post(Long id, User author, PostContent content, PostVisibleState visibleState) {
        if (author == null) {
            throw new IllegalArgumentException("author cannot be null");
        }
        this.id = id;
        this.author = author;
        this.content = content;
        this.visibleState = visibleState;
        this.likeCounter = new PositiveIntegerCounter();
    }

    public void getLike(User user) {
        if (this.author.equals(user)) {
            throw new IllegalArgumentException();
        }
        likeCounter.increase();
    }

    public void loseLike() {
        likeCounter.decrease();
    }

    public void updatePost(User user, String content, PostVisibleState visibleState) {
        if (!author.equals(user)) {
            throw new IllegalArgumentException();
        }
        this.content.updateContent(content);
        this.visibleState = visibleState;
    }

    public boolean isAuthor(User user) {
        return this.author.equals(user);
    }

    public Long getId() {
        return id;
    }

    public String getContentMessage() {
        return content.getContent();
    }

    public User getAuthor() {
        return author;
    }

    public Integer getLikeCount() {
        return likeCounter.getCount();
    }

    public PostVisibleState getVisibleState() {
        return visibleState;
    }

    public Boolean isEdited() {
        return content.isEdited();
    }

    public LocalDateTime getRegDtm() {
        return content.getRegDtm();
    }

    public LocalDateTime getModDtm() {
        return content.getModDtm();
    }

}
