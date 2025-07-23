package org.skypro.skyshop.controller;
import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.search.SearchResult;
import org.skypro.skyshop.model.service.BasketService;
import org.skypro.skyshop.model.service.SearchService;
import org.skypro.skyshop.model.service.StorageService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class ShopController {
    private final StorageService storageService;
    private final SearchService searchService;

    public ShopController(StorageService storageService, SearchService searchService) {
        this.storageService = storageService;
        this.searchService = searchService;
    }

    @GetMapping("/products")
    public Collection<Product> getAllProducts() {
        return storageService.getAllProducts();
    }

    @GetMapping("/articles")
    public Collection<Article> getAllArticles() {
        return storageService.getAllArticles();
    }

    @GetMapping("/search")
    public Collection<SearchResult> search(@RequestParam String pattern) {
        return searchService.search(pattern);
    }
    @RestController
    @RequestMapping("/api/basket")
    public class BasketController {
        private final BasketService basketService;

        public BasketController(BasketService basketService) {
            this.basketService = basketService;
        }

        @PostMapping("/{id}")
        public String addProduct(@PathVariable UUID id) {
            basketService.addProductToBasket(id);
            return "Продукт успешно добавлен";
        }

        @GetMapping
        public Map<UUID, Integer> getUserBasket() {
            return basketService.getUserBasket();
        }
    }
}