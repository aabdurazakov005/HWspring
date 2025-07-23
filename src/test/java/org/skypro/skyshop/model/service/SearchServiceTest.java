package org.skypro.skyshop.model.service;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.search.SearchResult;
import org.skypro.skyshop.model.service.SearchService;
import org.skypro.skyshop.model.service.StorageService;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.article.Article;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SearchServiceTest {

    @Mock
    private StorageService storageService;

    @InjectMocks
    private SearchService searchService;

    // Тест 1: Поиск при пустом хранилище
    @Test
    void search_WhenStorageEmpty_ReturnsEmptyList() {
        when(storageService.getAllSearchables()).thenReturn(Collections.emptyList());

        Collection<SearchResult> results = searchService.search("query");

        assertTrue(results.isEmpty());
        verify(storageService).getAllSearchables();
    }

    // Тест 2: Поиск без совпадений
    @Test
    void search_WhenNoMatches_ReturnsEmptyList() {
        Product product = new SimpleProduct("Молоко", 80);
        Article article = new Article("Польза витаминов", "Текст...");
        when(storageService.getAllSearchables()).thenReturn(Arrays.asList(product, article));

        Collection<SearchResult> results = searchService.search("Хлеб");

        assertTrue(results.isEmpty());
        verify(storageService).getAllSearchables();
    }

    // Тест 3: Успешный поиск товара
    @Test
    void search_WhenProductMatches_ReturnsProduct() {
        Product product = new SimpleProduct("Молоко", 80);
        when(storageService.getAllSearchables()).thenReturn(Collections.singletonList(product));

        Collection<SearchResult> results = searchService.search("мол");

        assertEquals(1, results.size());
        assertEquals("Молоко", results.iterator().next().getName());
        verify(storageService).getAllSearchables();
    }

    // Тест 4: Успешный поиск статьи
    @Test
    void search_WhenArticleMatches_ReturnsArticle() {
        Article article = new Article("Здоровое питание", "Статья о молоке");
        when(storageService.getAllSearchables()).thenReturn(Collections.singletonList(article));

        Collection<SearchResult> results = searchService.search("молок");

        assertEquals(1, results.size());
        assertEquals("Здоровое питание", results.iterator().next().getName());
        verify(storageService).getAllSearchables();
    }

    // Тест 5: Поиск с пустым запросом
    @Test
    void search_WhenEmptyQuery_ReturnsEmptyList() {
        Collection<SearchResult> results = searchService.search("");

        assertTrue(results.isEmpty());
        verifyNoInteractions(storageService);
    }
}