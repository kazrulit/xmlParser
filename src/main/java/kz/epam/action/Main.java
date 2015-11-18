package kz.epam.action;

import kz.epam.configs.Configs;
import kz.epam.entity.Category;
import kz.epam.service.SAXFactoryService;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class Main extends Action {
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        SAXFactoryService saxFactoryService = new SAXFactoryService();
        saxFactoryService.runParser();

        List<Category> shopList = saxFactoryService.getShopList();
        request.setAttribute(Configs.SHOP_LIST, shopList);
        return mapping.findForward(Configs.SUCCESS);
    }
}
