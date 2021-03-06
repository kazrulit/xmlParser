package kz.epam.action.transformation;

import kz.epam.configs.Configs;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Source;
import javax.xml.transform.Templates;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.StringWriter;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by admin on 11/18/2015.
 */
public class ShowXSLT extends Action {
    private static ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Source xslSource = new StreamSource(new File(Configs.XSLT_PATH));

        Transformer transformer = transformerFactory.newTransformer(xslSource);

        Source xmlSource = new StreamSource(new File(Configs.FILE_PATH));

        StreamResult streamResult = new StreamResult(new StringWriter());
        transformer.setParameter(Configs.TYPE, Configs.CATEGORY);

        if(request.getParameter(Configs.CAT) != null) {
            transformer.setParameter(Configs.TYPE, Configs.SUBCATEGORY);
            transformer.setParameter(Configs.CAT, request.getParameter(Configs.CAT));

            if(request.getParameter(Configs.SUB) != null) {
                transformer.setParameter(Configs.TYPE, Configs.GOOD);
                transformer.setParameter(Configs.SUB, request.getParameter(Configs.SUB));
            }
        }

        if(request.getParameter(Configs.TYPE) != null) {
            transformer.setParameter(Configs.TYPE, request.getParameter(Configs.TYPE));
        }

        readWriteLock.writeLock().lock();
        transformer.transform(xmlSource, streamResult);
        readWriteLock.writeLock().unlock();

        response.getWriter().print(streamResult.getWriter());

        return super.execute(mapping, form, request, response);
    }
}
