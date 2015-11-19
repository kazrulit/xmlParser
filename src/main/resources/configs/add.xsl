<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:validator="xalan://kz.epam.validator.ValidatorXML"
                xmlns:good="xalan://kz.epam.entity.Good"
        >
    <xsl:param name="type"/>
    <xsl:param name="cat"/>
    <xsl:param name="sub"/>
    <xsl:param name="good"/>
    <xsl:param name="validate" select="validator:validate($good)"/>

    <xsl:template match="@*|node()">
        <xsl:if test="$validate">
            <xsl:copy>
                <xsl:apply-templates select="@*|node()"/>
            </xsl:copy>
        </xsl:if>
    </xsl:template>

    <xsl:template match="shop/category[@name=$cat]/sub_category[@name=$sub]">
        <xsl:copy>
            <xsl:apply-templates select="@* | *"/>
            <xsl:element name="good">
                <xsl:element name="product_name">
                    <xsl:value-of select="good:getProductName($good)"/>
                </xsl:element>
                <xsl:element name="provider">
                    <xsl:value-of select="good:getProvider($good)"/>
                </xsl:element>
                <xsl:element name="model">
                    <xsl:value-of select="good:getModel($good)"/>
                </xsl:element>
                <xsl:element name="date">
                    <xsl:value-of select="good:getDate($good)"/>
                </xsl:element>
                <xsl:element name="color">
                    <xsl:value-of select="good:getColor($good)"/>
                </xsl:element>
                <xsl:element name="price">
                    <xsl:value-of select="good:getPrice($good)"/>
                </xsl:element>
            </xsl:element>
        </xsl:copy>
    </xsl:template>
</xsl:stylesheet>