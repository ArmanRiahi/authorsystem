package ir.chevil.model;

import ir.chevil.model.base.BaseModel;

import java.util.Objects;
import java.util.UUID;

public class Category extends BaseModel<UUID> {
    private String title;

    public Category(int id, String title) {
        this.setId(UUID.randomUUID());
        this.setTitle(title);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.getId());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != getClass()) return false;
        Category category = (Category) obj;
        return Objects.equals(this.getId(), category.getId());
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + this.getId() +
                ",title=" + title +
                "}";
    }
}
