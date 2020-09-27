package com.mamalimomen.base.controller.utilities;

public final class InValidDataException extends Exception {

    public InValidDataException(String message) {
        super(message);
    }

    public InValidDataException(String message, Exception e) {
        super(message, e);
    }
}