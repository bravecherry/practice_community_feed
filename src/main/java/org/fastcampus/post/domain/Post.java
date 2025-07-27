package org.fastcampus.post.domain;

import org.fastcampus.common.domain.PositiveIntegerCounter;
import org.fastcampus.post.domain.content.PostContent;
import org.fastcampus.post.domain.content.PostVisibleState;
import org.fastcampus.user.domain.User;

public class Post {

    //id 설정이 테스트 세팅할때는 편할 수 있다.
//    private final long authorId;
    private final User author;
    private final PostContent content;
    private final PositiveIntegerCounter likeCounter;
    private PostVisibleState visibleState;

    public Post(User author, PostContent content) {
        if (author == null) {
            throw new IllegalArgumentException("author cannot be null");
        }
        this.author = author;
        this.content = content;
        this.likeCounter = new PositiveIntegerCounter();
        this.visibleState = PostVisibleState.PUBLIC;
    }

    public void like(User user) {
        if (this.author.equals(user)) {
            throw new IllegalArgumentException();
        }
        likeCounter.increase();
    }

    public void dislike() {
        likeCounter.decrease();
    }

    public void updatePost(User user, String content, PostVisibleState visibleState) {
        if (!author.equals(user)) {
            throw new IllegalArgumentException();
        }
        this.content.updateContent(content);
        this.visibleState = visibleState;
    }
}
