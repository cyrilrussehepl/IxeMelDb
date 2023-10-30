package com.hepl.validator.dom;

import com.hepl.validator.Utils;
import org.w3c.dom.Document;

public class DomValidatorDTD {
    public static void main(String[] args) {

        Document document = Utils.parseXmlDom(Utils.XML_FILE, true);

    }
}
