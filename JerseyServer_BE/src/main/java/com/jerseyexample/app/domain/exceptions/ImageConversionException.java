package com.jerseyexample.app.domain.exceptions;

public class ImageConversionException extends RuntimeException {

    public ImageConversionException(String msg) {
        super(msg);
    }

    public ImageConversionException(String msg, Throwable t) {
        super(msg, t);
    }
}
