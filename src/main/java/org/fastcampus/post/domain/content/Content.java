package org.fastcampus.post.domain.content;

import java.time.LocalDateTime;
import lombok.Getter;
import org.fastcampus.post.domain.common.DateTimeInfo;

public abstract class Content {

    @Getter
    private String content;
    private DateTimeInfo dateTimeInfo;

    public Content(String content) {
        checkLength(content);
        this.content = content;
        this.dateTimeInfo = new DateTimeInfo();
    }

    protected abstract void checkLength(String content);

    public void updateContent(String content) {
        checkLength(content);
        if (!this.content.equals(content)) {
            this.content = content;
            this.dateTimeInfo.updateEdited();
        }
    }

    public Boolean isEdited() {
        return dateTimeInfo.isEdited();
    }

    public LocalDateTime getRegDtm() {
        return dateTimeInfo.getRegDtm();
    }

    public LocalDateTime getModDtm() {
        return dateTimeInfo.getModDtm();
    }

}
