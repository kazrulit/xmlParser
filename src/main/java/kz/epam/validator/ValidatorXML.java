package kz.epam.validator;

import kz.epam.entity.Good;

/**
 * Created by admin on 11/19/2015.
 */
public class ValidatorXML {
    public boolean validate(Good good) {
        boolean check = true;
        if (good.getProductName() == null || good.getProductName().isEmpty())
            check = false;
        if (good.getProvider() == null ||  good.getProvider().isEmpty())
            check = false;
        if (good.getModel() == null ||  good.getModel().isEmpty())
            check = false;
        if (good.getDate() == null ||  good.getDate().isEmpty())
            check = false;
        if (good.getColor() == null ||  good.getColor().isEmpty())
            check = false;
        if (good.getPrice() == null)
            check = false;
        return check;
    }
}
