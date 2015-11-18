package kz.epam.parser;

import kz.epam.configs.Configs;
import kz.epam.entity.Category;
import kz.epam.entity.Good;
import kz.epam.entity.SubCategory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DOMParserImpl {
    private List<Category> shopList = new ArrayList<>();
    private Category category;
    private SubCategory subCategory;
    private Good good;

    public void parse() {
        try {
            File file = new File(Configs.FILE_PATH);
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(file);

            document.getDocumentElement().normalize();

            NodeList nodeCategoryList = document.getElementsByTagName(Configs.CATEGORY);

            for (int i = 0; i < nodeCategoryList.getLength(); i++) {
                Node categoryNode = nodeCategoryList.item(i);
                if (categoryNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element)categoryNode;
                    category = new Category();
                    category.setName(element.getAttribute(Configs.NAME));

                    setSubCategoryNodeList(categoryNode);

                    shopList.add(category);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setSubCategoryNodeList(Node node) {
        NodeList subCategories = node.getChildNodes();
        for (int j = 0; j < subCategories.getLength(); j++) {
            Node subCategoryNode = subCategories.item(j);
            if (subCategoryNode.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) subCategoryNode;
                subCategory = new SubCategory();
                subCategory.setName(element.getAttribute(Configs.NAME));
                goodNodeList(subCategoryNode);
                category.getSubCategories().add(subCategory);
            }
        }

    }

    private void goodNodeList(Node node) {
        NodeList goodList = node.getChildNodes();
        for (int k = 0; k < goodList.getLength(); k++) {
            Node goodNode = (Node) goodList.item(k);
            if (goodNode.getNodeType() == Node.ELEMENT_NODE) {
                Element goodElement = (Element) goodNode;
                good = new Good();
                good.setProductName(goodElement.getElementsByTagName(Configs.PRODUCT_NAME).item(0).getTextContent());
                good.setProvider(goodElement.getElementsByTagName(Configs.PROVIDER).item(0).getTextContent());
                good.setModel(goodElement.getElementsByTagName(Configs.MODEL).item(0).getTextContent());
                good.setDate(goodElement.getElementsByTagName(Configs.DATE).item(0).getTextContent());
                good.setColor(goodElement.getElementsByTagName(Configs.COLOR).item(0).getTextContent());
                good.setPrice(Integer.parseInt(goodElement.getElementsByTagName(Configs.PRICE).item(0).getTextContent()));
                subCategory.getGoodList().add(good);
            }
        }
    }

    public List<Category> getShopList() {
        return shopList;
    }


}
