package org.fastcampus.post.domain.common;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class DateTimeInfoTest {

    @Test
    void givenCreated_whenUpdated_thenDateTimeInfoUpdated() {
        //given
        DateTimeInfo dateTimeInfo = new DateTimeInfo();
        //when
        dateTimeInfo.updateEdited();;
        //then
        assertTrue(dateTimeInfo.isEdited());
        assertNotNull(dateTimeInfo.getModDtm());
    }
}
