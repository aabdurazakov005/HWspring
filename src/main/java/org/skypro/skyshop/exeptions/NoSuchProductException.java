package org.skypro.skyshop.exeptions;

public class NoSuchProductException extends RuntimeException {
    public NoSuchProductException(String message) {
        super(message);
    }
}