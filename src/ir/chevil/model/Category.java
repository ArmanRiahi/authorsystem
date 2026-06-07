package ir.chevil.model;

import java.util.Objects;

public class Category {
    private int id;
    private String title;

    public Category(int id, String title) {
        this.setId(id);
        this.setTitle(title);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != getClass()) return false;
        Category category = (Category) obj;
        return Objects.equals(id, category.id);
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ",title=" + title +
                "}";
    }
}
