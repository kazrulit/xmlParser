package kz.epam.action;

import kz.epam.configs.Configs;
import kz.epam.entity.Category;
import kz.epam.service.StAXFactoryService;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class StaxAction extends Action {
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        StAXFactoryService staxFactoryService = new StAXFactoryService();
        staxFactoryService.runParser();

        List<Category> shopList = staxFactoryService.getShopList();
        request.setAttribute(Configs.SHOP_LIST, shopList);
        return mapping.findForward(Configs.SUCCESS);
    }
}
