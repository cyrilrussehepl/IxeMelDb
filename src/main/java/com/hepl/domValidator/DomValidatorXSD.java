package com.hepl.domValidator;

import javax.xml.transform.dom.DOMSource;
import javax.xml.validation.Schema;
import javax.xml.validation.Validator;

import com.hepl.saxValidator.SaxValidatorXSD;
import org.w3c.dom.Document;

public class DomValidatorXSD {
    public static void main(String[] args) throws Exception{
        Schema schema = SaxValidatorXSD.loadSchema(SaxValidatorXSD.XSD_FILE);
        Document document = DomValidatorDTD.parseXmlDom(DomValidatorDTD.XML_FILE, false);

        Validator validator = schema.newValidator();
        try {
            validator.validate(new DOMSource(document));
        }catch (Exception e){
            System.out.println("Erreur lors de la validation : "+e.getMessage());
            return;
        }
        System.out.println("Validation ok");
    }
}
