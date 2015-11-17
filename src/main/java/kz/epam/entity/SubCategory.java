package kz.epam.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 11/17/2015.
 */
public class SubCategory {
    private List<Good> goodList = new ArrayList<Good>();

    public List<Good> getGoodList() {
        return goodList;
    }

    public void setGoodList(List<Good> goodList) {
        this.goodList = goodList;
    }
}
