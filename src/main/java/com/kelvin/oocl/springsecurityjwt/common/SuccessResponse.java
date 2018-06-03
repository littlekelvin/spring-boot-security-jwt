package com.kelvin.oocl.springsecurityjwt.common;

public class SuccessResponse<T>{
    private String status;
    private T data;

    public SuccessResponse() {
    }

    public SuccessResponse(String status, T data) {
        this.status = status;
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
