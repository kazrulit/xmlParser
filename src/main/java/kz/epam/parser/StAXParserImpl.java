package kz.epam.parser;

import kz.epam.entity.Category;
import kz.epam.entity.Good;
import kz.epam.entity.SubCategory;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import java.io.FileReader;
import java.util.List;

import static kz.epam.configs.Configs.*;

/**
 * Created by admin on 11/17/2015.
 */
public class StAXParserImpl {
    private List<Category> shopList;
    private Category category;
    private SubCategory subCategory;
    private Good good;

    public void parse() {
        try {
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLEventReader eventReader = factory.createXMLEventReader(new FileReader(FILE_PATH));
        } catch (Exception e) {
            
        }
    }
}
