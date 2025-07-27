package org.fastcampus.post.domain;

public class CommentContent extends Content{

    private static final int MAX_LENGTH = 100;

    public CommentContent(String content) {
        super(content);
    }

    @Override
    protected void checkLength() {
        if (getContent() == null || getContent().isEmpty()) {
            throw new IllegalArgumentException();
        }
        if (getContent().length() > MAX_LENGTH) {
            throw new IllegalArgumentException();
        }
    }
}
