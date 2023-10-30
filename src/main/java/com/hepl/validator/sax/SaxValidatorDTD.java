package com.hepl.validator.sax;

import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import com.hepl.validator.Utils;
import org.xml.sax.SAXException;

public class SaxValidatorDTD {
    public static void main(String[] args) {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        factory.setValidating(true);
        factory.setNamespaceAware(false);
        try {
            SAXParser sp = factory.newSAXParser();
            sp.getXMLReader().parse(Utils.XML_FILE);
        } catch (ParserConfigurationException e) {
            System.out.println("ParserConfigurationException : " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IOException : " + e.getMessage());
        } catch (SAXException e) {
            System.out.println("SaxEXception : " + e.getMessage());
        }
    }
}
