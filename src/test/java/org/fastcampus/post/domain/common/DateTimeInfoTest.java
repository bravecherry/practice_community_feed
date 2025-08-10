package org.fastcampus.post.domain.common;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * 강사 시나리오
 * 1.
 * - DateTimeInfo 인스턴스 생성
 * - 초기화된 datetime 필드를 변수에 정의 후 업데이트
 * - 업데이트된 시간과 초기 시간이 같지 않다는 것을 확인
 */
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
