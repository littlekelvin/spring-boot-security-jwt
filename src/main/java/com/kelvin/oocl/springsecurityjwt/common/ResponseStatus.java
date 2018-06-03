package com.kelvin.oocl.springsecurityjwt.common;

public enum ResponseStatus {
    SUCCESS("success"),
    Fail("failed");

    private String value;

    ResponseStatus(String value) {
        this.value = value;
    }

    public String value(){
        return value;
    }
}
