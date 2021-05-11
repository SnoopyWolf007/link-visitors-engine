package org.link.visitors.core.dto;

/**
 * @author ginger
 */
public class IResponse {
    private Integer code;
    private String message;
    private Object data;

    public static IResponse getInstance() {
        return new IResponse();
    }

    public IResponse success() {
        this.code = 0;
        this.message = "success";
        return this;
    }

    public IResponse success(Object data) {
        this.code = 0;
        this.message = "success";
        this.data = data;
        return this;
    }

    public IResponse fail() {
        this.code = -1;
        this.message = "fail";
        return this;
    }

    public IResponse data(Object data) {
        this.data = data;
        return this;
    }

    public IResponse code(int code) {
        this.code = code;
        return this;
    }

    public IResponse message(String message) {
        this.message = message;
        return this;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
