package org.skypro.skyshop.model.error;

import java.util.Objects;

public final class ShopError {
    private final String code;
    private final String message;

    public ShopError(String code, String message) {
        this.code = Objects.requireNonNull(code);
        this.message = Objects.requireNonNull(message);
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}