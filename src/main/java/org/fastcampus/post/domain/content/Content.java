package org.fastcampus.post.domain.content;

import java.time.LocalDateTime;
import java.util.Objects;
import org.fastcampus.post.domain.common.DateTimeInfo;

public abstract class Content {

    private String content;
    private DateTimeInfo updateDtm;

    public Content(String content) {
        checkLength(content);
        this.content = content;
        this.updateDtm = new DateTimeInfo();
    }

    protected abstract void checkLength(String content);

    public void updateContent(String content) {
        checkLength(content);
        if (!this.content.equals(content)) {
            this.content = content;
            this.updateDtm.updateEdited();
        }
    }

    public String getContent() {
        return content;
    }

    public Boolean isEdited() {
        return updateDtm.isEdited();
    }

    public LocalDateTime getUpdateDtm() {
        return updateDtm.getDateTime();
    }
}
