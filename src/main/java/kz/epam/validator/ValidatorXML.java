package kz.epam.validator;

import kz.epam.entity.Good;

/**
 * Created by admin on 11/19/2015.
 */
public class ValidatorXML {
    public boolean validate(Good good) {
        boolean check = true;
        if(good != null) {
            if (good.getProductName() != null && good.getProductName().isEmpty())
                check = false;
            if (good.getProductName() != null && good.getProvider().isEmpty())
                check = false;
            if (good.getProductName() != null && good.getModel().isEmpty())
                check = false;
            if (good.getProductName() != null && good.getColor().isEmpty())
                check = false;
            if (good.getProductName() != null && good.getProductName().isEmpty())
                check = false;
            if (good.getProductName() != null && good.getProductName().isEmpty())
                check = false;
        } else {
            check = false;
        }
        return check;
    }
}
