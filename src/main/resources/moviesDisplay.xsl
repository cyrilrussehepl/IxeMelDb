<?xml version="1.0" encoding="iso-8859-1" ?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match="/">
        <html>
            <head>
                <title>Movies</title>
            </head>
            <body>
                <table border="1">
                    <tr>
                        <th>Title</th>
                        <th>Original Title</th>
                        <th>releaseDate</th>
                        <th>status</th>
                        <th>voteAverage</th>
                        <th>voteCount</th>
                        <th>runtime</th>
                        <th>certification</th>
                        <th>posterPath</th>
                        <th>budget</th>
                        <th>tagline</th>
                    </tr>
                    <xsl:for-each select="movieList/movie">
                        <tr>
                            <td>
                                <xsl:value-of select="title"/>
                            </td>
                            <td>
                                <xsl:value-of select="originalTitle"/>
                            </td>
                            <td>
                                <xsl:value-of select="releaseDate"/>
                            </td>
                            <td>
                                <xsl:value-of select="status"/>
                            </td>
                            <td>
                                <xsl:value-of select="voteAverage"/>
                            </td>
                            <td>
                                <xsl:value-of select="voteCount"/>
                            </td>
                            <td>
                                <xsl:value-of select="runtime"/>
                            </td>
                            <td>
                                <xsl:value-of select="certification"/>
                            </td>
                            <td>
                                <xsl:value-of select="posterPath"/>
                            </td>
                            <td>
                                <xsl:value-of select="budget"/>
                            </td>
                            <td>
                                <xsl:value-of select="tagline"/>
                            </td>
                        </tr>
                    </xsl:for-each>
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>