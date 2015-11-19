<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:xsd="shop.xsd"
                xmlns:validator="xalan://kz.epam.validator.ValidatorXML"
                xmlns:good="xalan://kz.epam.entity.Good"
                exclude-result-prefixes="xsd"
        >
    <xsl:param name="type" />
    <xsl:param name="cat"/>
    <xsl:param name="sub"/>
    <xsl:param name="good"/>
    <xsl:param name="validate" select="validator:validate($good)"/>
    
    <xsl:template match="@*|node()">
        <xsl:copy>
            <xsl:apply-templates select="@*|node()"/>
        </xsl:copy>
    </xsl:template>

    <xsl:template match="xsd:shop/xsd:category[@name=$cat]/xsd:sub_category[@name=$sub]">
        <xsl:copy>
            <xsl:choose>

                <xsl:when test="$validate">
                    <xsl:apply-templates select="@* | *"/>
                    <xsl:element name="good">
                        <xsl:element name="product_name"><xsl:value-of select="good:getProductName($good)"/></xsl:element>
                        <xsl:element name="provider"><xsl:value-of select="good:getProvider($good)"/></xsl:element>
                        <xsl:element name="model"><xsl:value-of select="good:getModel($good)"/></xsl:element>
                        <xsl:element name="date"><xsl:value-of select="good:getDate($good)"/></xsl:element>
                        <xsl:element name="color"><xsl:value-of select="good:getColor($good)"/></xsl:element>
                        <xsl:element name="price"><xsl:value-of select="good:getPrice($good)"/></xsl:element>
                    </xsl:element>
                </xsl:when>

                <xsl:otherwise>
                   <!-- <xsl:call-template name="returnToForm"/>-->
                </xsl:otherwise>

            </xsl:choose>
        </xsl:copy>
    </xsl:template>

    <!--<xsl:template name="returnToProducts">
        <html>
            <head>
                <meta http-equiv="refresh"
                      content="0;url=show.do/?cat={$cat}&amp;subc={$sub}" />
            </head>
        </html>
    </xsl:template>

    <xsl:template name="returnToForm">
        <html>
            <head>
                <meta http-equiv="refresh"
                      content="0;url=show.do/?type=form&amp;cat={$cat}&amp;subc={$sub}" />
            </head>
        </html>
    </xsl:template>-->

</xsl:stylesheet>