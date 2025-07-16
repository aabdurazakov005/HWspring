package org.skypro.skyshop.model.service;

import org.skypro.skyshop.exeptions.NoSuchProductException;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.product.Product;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class BasketService {
    private final ProductBasket productBasket;
    private final StorageService storageService;

    public BasketService(ProductBasket productBasket, StorageService storageService) {
        this.productBasket = productBasket;
        this.storageService = storageService;
    }

    public void addProductToBasket(UUID productId) {
        try {
            Product product = storageService.getProductById(productId);
            productBasket.addProduct(product.getId());
        } catch (NoSuchProductException ex) {
            throw new NoSuchProductException("Не удалось добавить товар в корзину: " + ex.getMessage());
        }
    }
}