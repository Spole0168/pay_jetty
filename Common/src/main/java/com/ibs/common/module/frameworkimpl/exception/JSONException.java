package com.ibs.common.module.frameworkimpl.exception;

public class JSONException extends Exception {
    private static final long serialVersionUID = 1L;

    public JSONException() {
        super();
    }

    public JSONException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public JSONException(String message, Throwable cause) {
        super(message, cause);
    }

    public JSONException(String message) {
        super(message);
    }

    public JSONException(Throwable cause) {
        super(cause);
    }

}
