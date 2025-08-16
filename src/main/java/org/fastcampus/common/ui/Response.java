package org.fastcampus.common.ui;

import org.fastcampus.common.domain.exception.ErrorCode;

public record Response<T>(Integer code, String message, T data) {

    public static <T> Response<T> ok(T data) {
        return new Response<>(0, "ok", data);
    }

    public static <T> Response<T> error(ErrorCode code) {
        return new Response<>(code.getCode(), code.getMessage(), null);
    }

}
