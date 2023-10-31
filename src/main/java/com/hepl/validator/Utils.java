package com.hepl.validator;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

public class Utils {
    public static final String RESOURCES_PATH = System.getProperty("user.dir") + "\\src\\main\\resources";
    public static final String XML_FILE = RESOURCES_PATH + "\\movies.xml";
    public static final String XSD_FILE = RESOURCES_PATH + "\\movies.xsd";
    public static Schema loadSchema(String filename) throws Exception {
        SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        return factory.newSchema(new File(filename));
    }
    public static Document parseXmlDom(String filename, boolean validateDTD) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        Document xml = null;
        try {
            factory.setValidating(validateDTD);

            DocumentBuilder builder = factory.newDocumentBuilder();
            xml = builder.parse(new File(filename));
            Element root = xml.getDocumentElement();
            System.out.println("Root element : "+root.getNodeName());
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        return xml;
    }

    public static void validateXSD(Schema schema, Source source)throws Exception{
        Validator validator = schema.newValidator();
        validator.validate(source);
    }
}
