package kz.epam.entity;

import java.util.ArrayList;
import java.util.List;


public class SubCategory {
    private List<Good> goodList = new ArrayList<>();

    public List<Good> getGoodList() {
        return goodList;
    }

    public void setGoodList(List<Good> goodList) {
        this.goodList = goodList;
    }
}
