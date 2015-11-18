package kz.epam.service;

import kz.epam.entity.Category;
import kz.epam.parser.StAXParserImpl;

import java.util.List;

public class StAXFactoryService implements FactoryService {
    private List<Category> shopList;

    @Override
    public synchronized void runParser() {
        StAXParserImpl stAXParser = new StAXParserImpl();
        stAXParser.parse();
        shopList = stAXParser.getShopList();
    }

    public List<Category> getShopList() {
        return shopList;
    }
}
