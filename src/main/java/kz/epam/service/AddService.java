package kz.epam.service;

import kz.epam.entity.Good;

/**
 * Created by admin on 11/19/2015.
 */
public class AddService {
    public String getProductName(Good good) {
        return good.getProductName();
    }

    public String getProvider(Good good) {
        return good.getProvider();
    }

    public String getModel(Good good) {
        return good.getModel();
    }

    public String getDate(Good good) {
        return good.getDate();
    }

    public String getColor(Good good) {
        return good.getColor();
    }

    public String getPrice(Good good) {
        return String.valueOf(good.getPrice());
    }
}
