package kz.epam.action;

import kz.epam.configs.Configs;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.StringWriter;

/**
 * Created by admin on 11/18/2015.
 */
public class ShowXSLT extends Action {
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Source source = new StreamSource(new File(Configs.XSLT_PATH));

        Transformer transformer = transformerFactory.newTransformer(source);

        Source xmlSource = new StreamSource(new File(Configs.FILE_PATH));

        StreamResult streamResult = new StreamResult(new StringWriter());
        transformer.setParameter("type", "category");

        if(request.getParameter("cat") != null) {
            transformer.setParameter("type", "subcategory");
            transformer.setParameter("cat", request.getParameter("cat"));

            if(request.getParameter("sub") != null) {
                transformer.setParameter("type", "good");
                transformer.setParameter("sub", request.getParameter("sub"));
            }
        }

        transformer.transform(xmlSource, streamResult);
        response.getWriter().print(streamResult.getWriter());

        return super.execute(mapping, form, request, response);
    }
}
