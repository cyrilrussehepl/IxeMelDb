package com.hepl.validator.sax;

import com.hepl.validator.Utils;
import org.xml.sax.InputSource;

import javax.xml.transform.sax.SAXSource;
import javax.xml.validation.Schema;

public class SaxValidatorXSD {
    public static void main(String[] args) throws Exception {
        Schema schema = Utils.loadSchema(Utils.XSD_FILE);

        Utils.validateXSD(schema, new SAXSource(new InputSource(Utils.XML_FILE)));
    }
}
