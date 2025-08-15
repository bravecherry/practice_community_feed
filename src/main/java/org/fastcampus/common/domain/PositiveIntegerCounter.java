package org.fastcampus.common.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class PositiveIntegerCounter {
    private int count;

    public PositiveIntegerCounter(Integer followingCount) {
        this.count = 0;
    }

    public void increase() {
        this.count++;
    }

    public void decrease() {
        if (this.count == 0) {
            return;
        }
        this.count--;
    }

}
