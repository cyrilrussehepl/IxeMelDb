package com.hepl.saxValidator;

import org.xml.sax.InputSource;

import javax.xml.XMLConstants;
import javax.xml.transform.sax.SAXSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;

public class SaxValidatorXSD {
    public static final String RESOURCES_PATH = System.getProperty("user.dir") + "\\src\\main\\resources";
    public static final String XML_FILE = RESOURCES_PATH + "\\movies.xml";
    public static final String XSD_FILE = RESOURCES_PATH + "\\movies.xsd";

    public static void main(String[] args) throws Exception {
        Schema schema = loadSchema(XSD_FILE);
        Validator validator = schema.newValidator();
        validator.validate(new SAXSource(new InputSource(XML_FILE)));
        System.out.println("The document was validated OK");
    }

    public static Schema loadSchema(String filename) throws Exception {
        SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        return factory.newSchema(new File(filename));
    }
}
