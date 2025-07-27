package org.fastcampus.post.domain.content;

public class PostContent extends Content{

    private static final int MIN_LENGTH = 5;
    private static final int MAX_LENGTH = 500;

    public PostContent(String content) {
        super(content);
    }

    @Override
    protected void checkLength(String content) {
        if (content == null || content.isEmpty()) {
            throw new IllegalArgumentException();
        }
        if (content.length() < MIN_LENGTH) {
            throw new IllegalArgumentException();
        }
        if (content.length() > MAX_LENGTH) {
            throw new IllegalArgumentException();
        }
    }
}
