package com.hepl.domValidator;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class DomValidatorDTD {
    public static final String RESOURCES_PATH = System.getProperty("user.dir") + "\\src\\main\\resources";
    public static final String XML_FILE = RESOURCES_PATH + "\\movies.xml";

    public static void main(String[] args) {
        parseXmlDom(XML_FILE, true);
    }

    public static Document parseXmlDom(String filename, boolean validateDTD) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        Document xml = null;
        try {
            if (validateDTD)
                factory.setValidating(true);

            DocumentBuilder builder = factory.newDocumentBuilder();
            xml = builder.parse(new File(filename));
            Element root = xml.getDocumentElement();
            System.out.println("root element : "+root.getNodeName());
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        return xml;
    }
}
