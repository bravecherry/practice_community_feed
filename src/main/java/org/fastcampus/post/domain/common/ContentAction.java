package org.fastcampus.post.domain.common;

import java.time.LocalDateTime;
import org.fastcampus.common.domain.PositiveIntegerCounter;
import org.fastcampus.post.domain.content.Content;
import org.fastcampus.user.domain.User;

public abstract class ContentAction {

    private Long id;
    private final User author;
    private final Content content;
    private final PositiveIntegerCounter likeCounter;

    public ContentAction(Long id, User author, Content content) {
        this.id = id;
        this.author = author;
        this.content = content;
        this.likeCounter = new PositiveIntegerCounter();
    }

    public Long getId() {
        return id;
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

    public Integer getLikeCount() {
        return likeCounter.getCount();
    }

    public User getAuthor() {
        return author;
    }

    public boolean isAuthor(User user) {
        return this.author.equals(user);
    }

    public Content getContent() {
        return content;
    }

    public String getContentMessage() {
        return content.getContent();
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
