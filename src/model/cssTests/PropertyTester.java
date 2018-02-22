package model.cssTests;

import model.Testable;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.jsoup.select.Selector;
import org.w3c.css.sac.CSSParseException;
import org.w3c.dom.css.*;

import java.util.ArrayList;

public class PropertyTester extends Testable {

    Property property;

    public PropertyTester(String name, Property property) {
        super(name);
        this.property = property;
    }

    @Override
    public void runTest(ArrayList<Document> documents, CSSStyleSheet sheet) {
        CSSRuleList ruleList = sheet.getCssRules();

        //TODO consider breaking this up, excessively large method
        for (int i = 0; i < ruleList.getLength(); i++) {
            CSSRule rule = ruleList.item(i);
            if (rule instanceof CSSStyleRule) {
                CSSStyleRule styleRule = (CSSStyleRule) rule;
                if (styleRule.getStyle().getLength() != 0) {   //Check css isn't empty
                    CSSStyleDeclaration styleDeclaration = styleRule.getStyle();
                    for (int j = 0; j < styleDeclaration.getLength(); j++) {
                        String foundProperty = styleDeclaration.item(j);
                        //System.out.println(styleDeclaration.getPropertyCSSValue(foundProperty).getCssText());
                        if (foundProperty.equals(property.propertyString)) {
                            for (Document document : documents) {
                                //Ensure there is a effected html element
                                try {
                                    Elements affectedElements = document.select(styleRule.getSelectorText());   //Jsoup allows the searching of classes as divs directly; ie: .myClass, #myDiv
                                    for (Element element : affectedElements) {
                                        if (!(element.childNodes().isEmpty())) {
                                            result = true;  //Check if corresponding html element actually holds something for the css to apply to
                                            return;
                                        }
                                    }
                                }catch(Selector.SelectorParseException e){
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}