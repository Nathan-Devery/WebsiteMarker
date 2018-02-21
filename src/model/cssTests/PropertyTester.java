package model.cssTests;

import model.Testable;
import org.jsoup.nodes.Document;
import org.w3c.dom.css.CSSStyleSheet;

import java.util.ArrayList;

public class PropertyTester extends Testable{

    public PropertyTester(String name) {
        super(name);
    }

    @Override
    public void runTest(ArrayList<Document> documents, CSSStyleSheet sheets) {

    }
}
