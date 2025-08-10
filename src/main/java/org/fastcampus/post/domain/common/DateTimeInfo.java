package org.fastcampus.post.domain.common;

import java.time.LocalDateTime;

public class DateTimeInfo {

    private boolean isEdited;
    private LocalDateTime regDtm;
    private LocalDateTime modDtm;

    public DateTimeInfo() {
        this.isEdited = false;
        this.regDtm = LocalDateTime.now();
    }

    public void updateEdited() {
        this.isEdited = true;
        this.modDtm = LocalDateTime.now();
    }

    public boolean isEdited() {
        return isEdited;
    }

    public LocalDateTime getRegDtm() {
        return regDtm;
    }

    public LocalDateTime getModDtm() {
        return modDtm;
    }

}
