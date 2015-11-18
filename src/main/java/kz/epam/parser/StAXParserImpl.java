package kz.epam.parser;

import kz.epam.configs.Configs;
import kz.epam.entity.Category;
import kz.epam.entity.Good;
import kz.epam.entity.SubCategory;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import static kz.epam.configs.Configs.*;

public class StAXParserImpl {
    private List<Category> shopList = new ArrayList<>();
    private Category category;
    private SubCategory subCategory;
    private Good good;
    private String content;

    public void parse() {
        try {
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLEventReader eventReader = factory.createXMLEventReader(new FileReader(FILE_PATH));

            while (eventReader.hasNext()) {
                XMLEvent xmlEvent = eventReader.nextEvent();
                switch (xmlEvent.getEventType()) {
                    case XMLStreamConstants.START_ELEMENT:
                        startElement(xmlEvent);
                        break;
                    case XMLStreamConstants.CHARACTERS:
                        character(xmlEvent);
                        break;
                    case XMLStreamConstants.END_ELEMENT:
                        endElement(xmlEvent);
                        break;
                }
            }

            eventReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void startElement(XMLEvent event) {
        StartElement startElement = event.asStartElement();
        String qName = startElement.getName().getLocalPart();
        switch (qName) {
            case CATEGORY:
                category = new Category();
                category.setName(startElement.getAttributeByName(new QName(Configs.NAME)).getValue());
                break;
            case SUB_CATEGORY:
                subCategory = new SubCategory();
                subCategory.setName(startElement.getAttributeByName(new QName(Configs.NAME)).getValue());
                break;
            case GOOD:
                good = new Good();
        }
    }

    private void character(XMLEvent event) {
        Characters characters = event.asCharacters();
        content = characters.getData().trim();
    }

    private void endElement(XMLEvent event) {
        EndElement endElement = event.asEndElement();
        String qName = endElement.getName().getLocalPart();
        switch (qName) {
            case CATEGORY:
                shopList.add(category);
                break;
            case SUB_CATEGORY:
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
