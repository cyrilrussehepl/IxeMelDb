package com.hepl.validator.dom;

import javax.xml.transform.dom.DOMSource;
import javax.xml.validation.Schema;

import com.hepl.validator.Utils;
import org.w3c.dom.Document;

public class DomValidatorXSD {
    public static void main(String[] args) throws Exception{
        Schema schema = Utils.loadSchema(Utils.XSD_FILE);
        Document document = Utils.parseXmlDom(Utils.XML_FILE, false);

        try {
            Utils.validateXSD(schema, new DOMSource(document));
        }catch (Exception e){
            System.out.println("Erreur lors de la validation : "+e.getMessage());
            return;
        }
        System.out.println("Validation ok");
    }
}
