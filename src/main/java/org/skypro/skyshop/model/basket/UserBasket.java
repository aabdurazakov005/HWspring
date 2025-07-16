package org.skypro.skyshop.model.basket;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public final class UserBasket {
    private final List<BasketItem> items;
    private final int total;


    public UserBasket(List<BasketItem> items) {
        Objects.requireNonNull(items, "Список товаров не может быть null");
        this.items = Collections.unmodifiableList(items);
        this.total = calculateTotal();
    }


    private int calculateTotal() {
        return items.stream()
                .mapToInt(BasketItem::getTotalPrice)
                .sum();
    }

    public List<BasketItem> getItems() {
        return items;
    }


    public int getTotal() {
        return total;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserBasket that = (UserBasket) o;
        return total == that.total &&
                items.equals(that.items);
    }

    @Override
    public int hashCode() {
        return Objects.hash(items, total);
    }

    @Override
    public String toString() {
        return "UserBasket{" +
                "items=" + items +
                ", total=" + total +
                '}';
    }
}