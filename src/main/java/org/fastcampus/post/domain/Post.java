package org.fastcampus.post.domain;

import org.fastcampus.user.domain.User;

public class Post {

    //id 설정이 테스트 세팅할때는 편할 수 있다.
//    private final long authorId;
    private final User author;
    private final PostContent content;

    public Post(User author, PostContent content) {
        if (author == null) {
            throw new IllegalArgumentException("author cannot be null");
        }
        this.author = author;
        this.content = content;
    }
}
