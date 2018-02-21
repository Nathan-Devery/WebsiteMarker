package model.cssTests;

import model.Testable;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.w3c.dom.css.*;
import java.util.ArrayList;

public class GroupsTest extends Testable {

    GroupType groupType;

    public GroupsTest(String name, GroupType groupType) {
        super(name);
        this.groupType = groupType;
    }

    @Override
    public void runTest(ArrayList<Document> documents, CSSStyleSheet sheet) {
        CSSRuleList ruleList = sheet.getCssRules();

        for (int i = 0; i < ruleList.getLength(); i++) {
            CSSRule rule = ruleList.item(i);
            if (rule instanceof CSSStyleRule) {
                CSSStyleRule styleRule = (CSSStyleRule) rule;
                //Check prefix and whether the check whether rule is empty
                String selectorText = styleRule.getSelectorText();
                if (selectorText.startsWith(groupType.prefix) && styleRule.getStyle().getLength() != 0) {
                    String htmlAttributeValue = selectorText.substring(groupType.prefix.length(), selectorText.length());
                    //Check there is a groupType.html attribute paired with the attribute value
                    for (Document document : documents) {
                        Elements affectedElements = document.select("[" + groupType.htmlAttribute + "=" + htmlAttributeValue + "]");
                        for(Element element: affectedElements){
                            if(!(element.childNodes().isEmpty())) result = true;    //Check if corresponding html element actually holds something for the css to apply to
                        }
                    }
                }
            }
        }
    }
}
