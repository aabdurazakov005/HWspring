package org.skypro.skyshop.model.product;

import org.skypro.skyshop.model.search.Searchable;
import java.util.Objects;
import java.util.UUID;

public abstract class Product implements Searchable {
    private final String name;
    private final UUID id = UUID.randomUUID();

    public Product(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException(
                    "Название продукта не может быть пустым.");
        }
        this.name = name;
    }
    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public String getSearchTerm() {
        return name;
    }

    @Override
    public String getContentType() {
        return "PRODUCT";
    }

    @Override
    public String getName() {
        return name;
    }

    public abstract int getPrice();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return name.equalsIgnoreCase(product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name.toLowerCase());
    }

    public abstract boolean isSpecial();
}