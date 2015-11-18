package kz.epam.entity;

import java.util.ArrayList;
import java.util.List;

public class Shop {
    private List<Category> categories = new ArrayList<>();

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}
