package org.fastcampus.post.domain.common;

import java.time.LocalDateTime;

public class DateTimeInfo {

    private boolean isEdited;
    private LocalDateTime dateTime;

    public DateTimeInfo() {}

    public void updateEdited() {
        this.isEdited = true;
        this.dateTime = LocalDateTime.now();
    }

    public boolean isEdited() {
        return isEdited;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

}
