package model.cssTests;

import jdk.nashorn.api.tree.CompilationUnitTree;
import model.TestResult;
import model.Testable;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.w3c.dom.css.*;
import java.util.ArrayList;

/**
 * Checks the html file contains the groupType enum.
 * Checks the groupType exists in the css (direct tag reference, id or class).
 * Full marks: Selector exists in html and css.
 */
public class SelectorTest extends Testable {

    SelectorType groupType;

    public SelectorTest(SelectorType groupType) {
        super(groupType.name());
        this.groupType = groupType;
    }

    @Override
    public String getDescription() {
        return  "Checks the html file contains " + groupType.name() + ".\n" +
                "Checks if it exists in the css (direct tag reference, id or class)." +
                "\nFull marks: Selector exists in html and css. ";
    }

    @Override
    public TestResult runTest(ArrayList<Document> documents, ArrayList<Document> xmlDocs, CSSStyleSheet sheet, String cssDocString, CompilationUnitTree tree, double percentage) {
        double result = 0;
        String report = "";

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
                        if(!affectedElements.isEmpty()) report += "\n-" + document.location() + "-\n\n";
                        for(Element element: affectedElements){
                            report += "CSS:\n" + rule.getCssText() + "\n\n";
                            report += "Corresponding HTML element:\n" +element + "\n";
                            result = percentage;
                        }
                    }
                }
            }
        }

        if(result == percentage){
            report = "Present in css.\nCorresponding element(s) present in html.\n\n" + report;
        }else{
            report = "Not present in css.\nCorresponding element(s) not present in html.\n\n" + report;
        }

        return new TestResult(toString(), result, report);
    }
}
