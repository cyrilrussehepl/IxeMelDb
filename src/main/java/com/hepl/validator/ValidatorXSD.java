package com.hepl.validator;

import org.xml.sax.InputSource;

import javax.xml.XMLConstants;
import javax.xml.transform.sax.SAXSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;

public class ValidatorXSD {
    public static final String RESOURCES_PATH = System.getProperty("user.dir") + "\\src\\main\\resources";
    public static final String XML_FILE = RESOURCES_PATH + "\\movies.xml";
    public static final String XSD_FILE = RESOURCES_PATH + "\\movies.xsd";

    public static void main(String[] args) throws Exception {
        SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = factory.newSchema(new File(XSD_FILE));

        Validator validator = schema.newValidator();

        validator.validate(new SAXSource(new InputSource(XML_FILE)));

        System.out.println("The document was validated OK");
    }
}
