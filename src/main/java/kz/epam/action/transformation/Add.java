package kz.epam.action.transformation;

import kz.epam.configs.Configs;
import kz.epam.entity.Good;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.xalan.transformer.TransformerImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.StringWriter;

public class Add extends Action {
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Source xslSource = new StreamSource(new File(Configs.XSL_ADD));

        Transformer transformer = transformerFactory.newTransformer(xslSource);

        Source xmlSource = new StreamSource(new File(Configs.FILE_PATH));
        StreamResult streamResult = new StreamResult(new File("D:\\KhamidJava\\xmlParser\\src\\main\\resources\\configs\\output.xml"));

        TransformerImpl transformerImpl = (TransformerImpl)transformer;
        if(transformerImpl != null) {
            transformerImpl.setParameter("cat", "clothes");
            transformerImpl.setParameter("sub", "china");

            Good good = new Good();

            if(request.getParameter(Configs.NAME) != null)
                good.setProductName(request.getParameter(Configs.NAME));

            if(request.getParameter(Configs.PROVIDER) != null)
                good.setProvider(request.getParameter(Configs.PROVIDER));

            if(request.getParameter(Configs.MODEL) != null)
                good.setModel(request.getParameter(Configs.MODEL));

            if(request.getParameter(Configs.DATE) != null)
                good.setDate(request.getParameter(Configs.DATE));

            if(request.getParameter(Configs.COLOR) != null)
                good.setColor(request.getParameter(Configs.COLOR));

            if(request.getParameter(Configs.PRICE) != null)
                good.setPrice(Integer.valueOf(request.getParameter(Configs.PRICE)));

            /*good.setProvider("UZBEKISTAN");
            good.setModel("123sd");
            good.setDate("12/12/2015");
            good.setColor("red");
            good.setPrice(123);*/

            transformerImpl.setParameter("good", good);
            transformerImpl.transform(xmlSource, streamResult);
        }


        response.getWriter().print(streamResult.getWriter());
        //transformer.setParameter(Configs.TYPE, Configs.CATEGORY);


        return super.execute(mapping, form, request, response);
    }
}
