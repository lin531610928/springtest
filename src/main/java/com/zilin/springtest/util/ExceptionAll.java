package com.zilin.springtest.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ExceptionAll {
    EMPTY_BLANK(0001,"Data Error"),
    LONGER(0002,"nopu");

    private final int code;
    private final String result;
}
