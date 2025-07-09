package org.skypro.skyshop.model.service;

import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.DiscountedProduct;
import org.skypro.skyshop.model.product.FixPriceProduct;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.search.Searchable;
import org.springframework.stereotype.Service;

import java.util.*;

import static java.util.Collections.addAll;
import static java.util.UUID.randomUUID;

@Service
public class StorageService {
    private static Map<UUID, Product> products;
    private static Map<UUID, Article> articles;

    public StorageService(){
        this.products = new HashMap<>();
        this.articles = new HashMap<>();
        createTestData();
    }

    private void createTestData() {
        Product product = new SimpleProduct("Молоко", 80);
        products.put(product.getId(), product);
        FixPriceProduct fixPriceProduct = new FixPriceProduct("Хлеб");
        products.put(fixPriceProduct.getId(), fixPriceProduct);
        DiscountedProduct discountedProduct = new DiscountedProduct("Яйца", 150, 115);
        products.put(discountedProduct.getId(), discountedProduct);
        Article article = new Article("Молоко и здоровье", "Текст статьи...");
        articles.put(article.getId(), article);
    }

    public Collection<Product> getAllProducts() {
        return products.values();
    }

    public Collection<Article> getAllArticles() {
        return articles.values();
    }

    public static Collection<Product> getProducts() {
        return Collections.unmodifiableCollection(products.values());
    }
    public Collection<Article> getArticles() {
        return Collections.unmodifiableCollection(articles.values());
    }

    public Collection<Searchable> getAllSearchables() {
        List<Searchable> result = new ArrayList<>();
        result.addAll(products.values());
        result.addAll(articles.values());
        return result;
    }
}