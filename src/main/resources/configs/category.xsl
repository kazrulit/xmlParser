<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:xsd="shop.xsd"
                exclude-result-prefixes="xsd">

    <xsl:param name="type" />
    <xsl:param name="cat"/>
    <xsl:param name="sub"/>

    <xsl:template match="/">
        <html>
            <head>
                <style>
                    .good {
                        margin: 10px 0px;
                        padding: 10px;
                        border: 1px solid #000;
                    }
                </style>
            </head>
            <body>
                <xsl:choose>

                    <xsl:when test="$type = 'category'">
                        <h2>Categories</h2>
                        <ul>
                            <xsl:for-each select="xsd:shop/xsd:category">
                                <li><a href='/show.do?cat={@name}'><xsl:value-of select="@name"/></a></li>
                            </xsl:for-each>
                        </ul>
                    </xsl:when>

                    <xsl:when test="$type = 'subcategory'">
                        <h2><xsl:value-of select="$cat"/></h2>
                        <ul>
                            <xsl:for-each select="xsd:shop/xsd:category[@name=$cat]/xsd:sub_category">
                                <li><a href='/show.do?cat={$cat}&amp;sub={@name}'><xsl:value-of select="@name"/></a></li>
                            </xsl:for-each>
                        </ul>
                    </xsl:when>

                    <xsl:when test="$type = 'good'">
                        <h2><xsl:value-of select="$cat"/> : <xsl:value-of select="$sub"/></h2>
                        <xsl:for-each select="xsd:shop/xsd:category[@name=$cat]/xsd:sub_category[@name=$sub]/xsd:good">
                            <div class="good">
                                <p><b>Product name:</b><xsl:value-of select="xsd:product_name"/></p>
                                <p><b>Provider:</b><xsl:value-of select="xsd:provider"/></p>
                                <p><b>Model:</b><xsl:value-of select="xsd:model"/></p>
                                <p><b>Date:</b><xsl:value-of select="xsd:date"/></p>
                                <p><b>Color:</b><xsl:value-of select="xsd:color"/></p>
                                <p><b>Price:</b><xsl:value-of select="xsd:price"/></p>
                            </div>
                        </xsl:for-each>
                    </xsl:when>
                </xsl:choose>
            </body>
        </html>
    </xsl:template>

</xsl:stylesheet>