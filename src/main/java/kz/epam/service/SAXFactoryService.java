package kz.epam.service;

import kz.epam.configs.Configs;
import kz.epam.entity.Category;
import kz.epam.parser.SAXParserImpl;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.util.List;

/**
 * Created by admin on 11/17/2015.
 */
public class SAXFactoryService implements FactoryService {
    private List<Category> shopList;

    @Override
    public synchronized void runParser() {
        try {
            File inputFile = new File(Configs.FILE_PATH);
            SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
            SAXParser saxParser = saxParserFactory.newSAXParser();
            SAXParserImpl impl = new SAXParserImpl();
            saxParser.parse(inputFile, impl);
            shopList = impl.getShopList();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Category> getShopList() {
        return shopList;
    }

}
