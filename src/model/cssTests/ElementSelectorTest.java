package model.cssTests;

import model.Testable;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.w3c.dom.css.CSSRule;
import org.w3c.dom.css.CSSRuleList;
import org.w3c.dom.css.CSSStyleRule;
import org.w3c.dom.css.CSSStyleSheet;

import java.util.ArrayList;

/**
 * Tests there is the use of an element selector. Tests the html element and css rule are not empty.
 */
public class ElementSelectorTest extends Testable {

    public ElementSelectorTest(String name) {
        super(name);
    }

    @Override
    public void runTest(ArrayList<Document> documents, CSSStyleSheet sheet) {
        CSSRuleList ruleList = sheet.getCssRules();

        for (int i = 0; i < ruleList.getLength(); i++) {
            CSSRule rule = ruleList.item(i);
            if (rule instanceof CSSStyleRule) {
                CSSStyleRule styleRule = (CSSStyleRule) rule;
                String prefix = styleRule.getSelectorText();
                if(!(prefix.startsWith(".") || prefix.startsWith("#")) && styleRule.getStyle().getLength() != 0){   //Check css isn't empty
                    for (Document document : documents) {
                        Elements affectedElements = document.select(styleRule.getSelectorText());
                        for(Element element: affectedElements){
                            if(!(element.childNodes().isEmpty())){
                                result = true;  //Check if corresponding html element actually holds something for the css to apply to
                                return;
                            }
                        }
                    }
                }
            }
        }
    }
}
