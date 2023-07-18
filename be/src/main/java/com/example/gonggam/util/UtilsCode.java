package com.example.gonggam.util;

import com.example.gonggam.util.exception.UtilsException;

public class UtilsCode {

    public static final class Space {

        public static final int MIN_SPACE_CAPACITY = 1;

        private Space() {
            throw new UtilsException(ErrorMessage.Global.UTIL_CLASS);
        }
    }

    private UtilsCode() {
        throw new UtilsException(ErrorMessage.Global.UTIL_CLASS);
    }
}
