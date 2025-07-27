package org.fastcampus.post.domain.content;

public abstract class Content {

    private final String content;

    public Content(String content) {
        checkLength(content);
        this.content = content;
    }

    protected abstract void checkLength(String content);

    public String getContent() {
        return content;
    }
}
