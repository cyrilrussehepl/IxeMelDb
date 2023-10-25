package com.hepl.validator;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class ValidatorApp {
    public static final String RESOURCES_PATH = System.getProperty("user.dir") + "\\src\\main\\resources";
    public static void main(String[] args) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            factory.setValidating(true);
            factory.setNamespaceAware(true);
            SAXParser saxParser = factory.newSAXParser();
            saxParser.getXMLReader().parse(RESOURCES_PATH+"\\movies.xml");
        }catch (Exception e){
            e.printStackTrace();
            return;
        }
        System.out.println("Validation successful\n");
    }
}
