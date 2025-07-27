package org.fastcampus.post.domain;

public class PostContent extends Content{

    private static final int MIN_LENGTH = 5;
    private static final int MAX_LENGTH = 500;

    public PostContent(String content) {
        super(content);
    }

    @Override
    protected void checkLength() {
        if (getContent() == null || getContent().isEmpty()) {
            throw new IllegalArgumentException();
        }
        if (getContent().length() < MIN_LENGTH) {
            throw new IllegalArgumentException();
        }
        if (getContent().length() > MAX_LENGTH) {
            throw new IllegalArgumentException();
        }
    }
}
