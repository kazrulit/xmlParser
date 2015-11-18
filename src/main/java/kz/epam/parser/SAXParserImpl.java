package kz.epam.parser;

import kz.epam.configs.Configs;
import kz.epam.entity.Category;
import kz.epam.entity.Good;
import kz.epam.entity.SubCategory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

import static kz.epam.configs.Configs.*;

public class SAXParserImpl extends DefaultHandler {
    private List<Category> shopList = new ArrayList<>();
    private Category category;
    private SubCategory subCategory;
    private Good good;
    private String content;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        switch (qName) {
            case CATEGORY:
                category = new Category();
                category.setName(attributes.getValue(Configs.NAME));
                break;
            case SUBCATEGORY:
                subCategory = new SubCategory();
                subCategory.setName(attributes.getValue(Configs.NAME));
                break;
            case GOOD:
                good = new Good();
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        content = String.copyValueOf(ch, start, length).trim();
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        switch (qName) {
            case CATEGORY:
                shopList.add(category);
                break;
            case SUBCATEGORY:
                category.getSubCategories().add(subCategory);
                break;
            case GOOD:
                subCategory.getGoodList().add(good);
                break;
            case PRODUCT_NAME:
                good.setProductName(content);
                break;
            case PROVIDER:
                good.setProvider(content);
                break;
            case MODEL:
                good.setModel(content);
                break;
            case DATE:
                good.setDate(content);
                break;
            case COLOR:
                good.setColor(content);
                break;
            case PRICE:
                good.setPrice(Integer.parseInt(content));
                break;
        }

    }

    public List<Category> getShopList() {
        return shopList;
    }
}
