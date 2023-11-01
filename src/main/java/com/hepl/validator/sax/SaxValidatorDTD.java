package com.hepl.validator.sax;

import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import com.hepl.validator.Utils;
import org.xml.sax.*;

public class SaxValidatorDTD {
    public static void main(String[] args) {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        factory.setValidating(true);
        factory.setNamespaceAware(false);

        try {
            SAXParser sp = factory.newSAXParser();
            XMLReader reader = getXmlReaderWithErrorHandler(sp);
            reader.parse(Utils.XML_FILE);
        } catch (ParserConfigurationException e) {
            System.out.println("ParserConfigurationException : " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IOException : " + e.getMessage());
        } catch (SAXException e) {
            return;
        }
        System.out.println("Validation successful!");
    }

    private static XMLReader getXmlReaderWithErrorHandler(SAXParser sp) throws SAXException {
        XMLReader reader = sp.getXMLReader();

        reader.setErrorHandler(new ErrorHandler() {
            public void warning(SAXParseException e){
                System.out.println("WARNING : " + e.getMessage());
            }

            public void error(SAXParseException e) throws SAXException {
                System.out.println("ERROR : " + e.getMessage());
                throw e;
            }

            public void fatalError(SAXParseException e) throws SAXException {
                System.out.println("FATAL : " + e.getMessage());
                throw e;
            }
        });
        return reader;
    }
}
