package kz.epam.service;

import kz.epam.entity.Category;
import kz.epam.parser.DOMParserImpl;

import java.util.List;

/**
 * Created by admin on 11/17/2015.
 */
public class DOMFactoryService implements FactoryService {
    private List<Category> shopList;

    @Override
    public synchronized void runParser() {
        DOMParserImpl domParser = new DOMParserImpl();
        domParser.parse();
        shopList = domParser.getShopList();
    }

    public List<Category> getShopList() {
        return shopList;
    }
}
