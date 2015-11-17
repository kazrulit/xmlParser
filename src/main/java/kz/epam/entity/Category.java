package kz.epam.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 11/17/2015.
 */
public class Category {
    private List<SubCategory> subCategories = new ArrayList<SubCategory>();

    public List<SubCategory> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(List<SubCategory> subCategories) {
        this.subCategories = subCategories;
    }
}
