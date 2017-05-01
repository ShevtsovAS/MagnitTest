<?xml version="1.0" encoding="UTF-8" ?>

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:output method="xml" standalone="no"/>

    <xsl:template match="/">
        <xsl:text>&#xA;</xsl:text>
        <entries>
            <xsl:apply-templates/>
        </entries>
    </xsl:template>

    <xsl:template match="entry">
        <entry field="{field}"/>
    </xsl:template>

</xsl:stylesheet>
