package kz.epam.entity;

import java.util.ArrayList;
import java.util.List;

public class Category {
    private List<SubCategory> subCategories = new ArrayList<>();
    private String name;

    public List<SubCategory> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(List<SubCategory> subCategories) {
        this.subCategories = subCategories;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
