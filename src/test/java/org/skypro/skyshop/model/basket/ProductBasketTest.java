package org.skypro.skyshop.model.basket;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.exeptions.NoSuchProductException;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.service.BasketService;
import org.skypro.skyshop.model.service.StorageService;

import java.util.Collections;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BasketServiceTest {

    @Mock
    private ProductBasket productBasket;

    @Mock
    private StorageService storageService;

    @InjectMocks
    private BasketService basketService;

    // Тест 1: Добавление несуществующего товара
    @Test
    void addProduct_WhenProductNotExists_ThrowsException() {
        UUID productId = UUID.randomUUID();
        when(storageService.getProductById(productId))
                .thenThrow(new NoSuchProductException("Товар не найден"));

        assertThrows(NoSuchProductException.class, () ->
                basketService.addProductToBasket(productId));

        verify(storageService).getProductById(productId);
        verifyNoInteractions(productBasket);
    }

    // Тест 2: Успешное добавление товара
    @Test
    void addProduct_WhenProductExists_AddsToBasket() {
        UUID productId = UUID.randomUUID();
        Product product = new SimpleProduct("Молоко", 80);
        when(storageService.getProductById(productId)).thenReturn(product);

        basketService.addProductToBasket(productId);

        verify(storageService).getProductById(productId);
        verify(productBasket).addProduct(productId);
    }

    // Тест 3: Получение пустой корзины
    @Test
    void getUserBasket_WhenEmpty_ReturnsEmptyMap() {
        when(productBasket.getBasketItems()).thenReturn(Collections.emptyMap());

        Map<UUID, Integer> basket = basketService.getUserBasket();

        assertTrue(basket.isEmpty());
        verify(productBasket).getBasketItems();
    }

    // Тест 4: Получение непустой корзины
    @Test
    void getUserBasket_WhenNotEmpty_ReturnsBasketContents() {
        UUID productId = UUID.randomUUID();
        when(productBasket.getBasketItems())
                .thenReturn(Map.of(productId, 2));

        Map<UUID, Integer> basket = basketService.getUserBasket();

        assertEquals(1, basket.size());
        assertEquals(2, basket.get(productId));
        verify(productBasket).getBasketItems();
    }
}