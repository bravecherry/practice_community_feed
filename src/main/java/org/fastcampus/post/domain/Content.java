package org.fastcampus.post.domain;

public abstract class Content {

    private final String content;

    public Content(String content) {
        checkLength();
        this.content = content;
    }

    protected abstract void checkLength();

    public String getContent() {
        return content;
    }
}
