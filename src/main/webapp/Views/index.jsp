<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib prefix="bean" uri="http://struts.apache.org/tags-bean" %>
<html>
<head>
    <title></title>
</head>
<body>
<a href="/main.do">SAX</a>
<a href="/dom.do">DOM</a>
<a href="/stax.do">STAX</a>
<div class="tree">
  <ul>
  <logic:iterate id="categoryList" name="shopList">
      <li>
        Category
        <ul>
          <bean:define id="categoryIterator" name="categoryList" property="subCategories" />
          <logic:iterate id="subCategorList" name="categoryIterator">
            <li>
              SubCategory
              <ul>
                <bean:define id="goodsIterator" name="subCategorList" property="goodList" />
                <logic:iterate id="good" name="goodsIterator">
                  <li>
                    Good
                    <ul>
                      <li><b>Product name:</b> ${good.productName}</li>
                      <li><b>Provider:</b> ${good.provider}</li>
                      <li><b>Model:</b> ${good.model}</li>
                      <li><b>Date:</b> ${good.date}</li>
                      <li><b>Color:</b> ${good.color}</li>
                      <li><b>Price:</b> ${good.price}</li>
                    </ul>
                  </li>
                </logic:iterate>
              </ul>
            </li>
          </logic:iterate>
        </ul>
      </li>
  </logic:iterate>
  </ul>
</div>
</body>
</html>
