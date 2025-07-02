package org.skypro.skyshop.model.search;

import java.util.Objects;
import java.util.UUID;

public final class SearchResult {
    private final UUID id;
    private final String name;
    private final String contentType;

    public SearchResult(UUID id, String name, String contentType) {
        this.id = Objects.requireNonNull(id, "ID не может быть null");
        this.name = Objects.requireNonNull(name, "Название не может быть null");
        this.contentType = Objects.requireNonNull(contentType, "Тип контента не может быть null");
    }

    public static SearchResult fromSearchable(Searchable searchable) {
        Objects.requireNonNull(searchable, "Searchable не может быть null");
        return new SearchResult(
                searchable.getId(),
                searchable.getName(),
                searchable.getContentType()
        );
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getContentType() {
        return contentType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SearchResult that = (SearchResult) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "SearchResult{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", contentType='" + contentType + '\'' +
                '}';
    }
}