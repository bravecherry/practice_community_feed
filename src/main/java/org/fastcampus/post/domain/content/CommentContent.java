package org.fastcampus.post.domain.content;

public class CommentContent extends Content{

    private static final int MAX_LENGTH = 100;

    public CommentContent(String content) {
        super(content);
    }

    @Override
    protected void checkLength(String content) {
        if (content == null || content.isEmpty()) {
            throw new IllegalArgumentException();
        }
        if (content.length() > MAX_LENGTH) {
            throw new IllegalArgumentException();
        }
    }
}
