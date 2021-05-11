package org.link.visitors.core.exception;

/**
 * @author ginger
 */
public class BizException extends RuntimeException {
    private int code;
    private String message;

    public BizException(int code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
