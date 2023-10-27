package com.hepl.saxValidator;

import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

public class SaxValidatorDTD {
    public static final String RESOURCES_PATH = System.getProperty("user.dir") + "\\src\\main\\resources";
    public static final String XML_FILE = RESOURCES_PATH + "\\movies.xml";

    public static void main(String[] args) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            factory.setValidating(true);
            factory.setNamespaceAware(false);
            SAXParser sp = factory.newSAXParser();
            sp.getXMLReader().parse(XML_FILE);
        } catch (ParserConfigurationException pce) {
            System.out.println("ParserConfigurationException : " + pce.getMessage());
        } catch (IOException io) {
            System.out.println("IOException : " + io.getMessage());
        } catch (SAXException se) {
            System.out.println("SaxEXception : " + se.getMessage());
        }
    }
}
