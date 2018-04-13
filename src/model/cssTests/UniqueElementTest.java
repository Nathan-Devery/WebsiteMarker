package model.cssTests;

import jdk.nashorn.api.tree.CompilationUnitTree;
import model.TestResult;
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
 * Checks if element exists in html.
 * Checks if css contains a reference to that html element. This css rule must be non-empty.
 * Full marks: present and referenced.
 */
public class UniqueElementTest extends Testable {

    UniqueElement divSpan;    //Div or span depending on enum argument

    public UniqueElementTest(UniqueElement divSpan) {
        super(divSpan.name());
        this.divSpan = divSpan;
    }

    @Override
    public String getDescription() {
        return  "Checks if element exists in html.\n" +
                "Checks if css contains a reference to that html element. This css rule must be non-empty.\n" +
                "Full marks: present and referenced.";
    }

    @Override
    public TestResult runTest(ArrayList<Document> documents, ArrayList<Document> xmlDocs, CSSStyleSheet sheet, String cssDocString, CompilationUnitTree tree, double percentage) {
        double result = 0;
        String report = "";

        for (Document document : documents) {
            report += "\n-" + document.location() + "-\n\n";
            Elements elements = document.select(divSpan.htmlAttribute);
            for (Element element : elements) {
                CSSStyleRule rule;
                if (element.attr("class").length() > 0) {
                    rule = searchCss("." + element.attr("class"), sheet);
                    if(rule != null && rule.getStyle().getLength() != 0){
                        report += "Element:\n" + element + "\n\n";
                        report += "Corresponding CSS:\n" + rule + "\n\n";
                        result = percentage;
                    }
                } else if (element.attr("id").length() > 0) {
                    rule = searchCss("#" + element.attr("id"), sheet);
                    if(rule != null && rule.getStyle().getLength() != 0){
                        report += "Element:\n" + element + "\n\n";
                        report += "Corresponding CSS\n" + rule + "\n\n";
                        result = percentage;
                    }
                } else {
                    rule = searchCss(divSpan.htmlAttribute, sheet);
                    if(rule != null && rule.getStyle().getLength() != 0){
                        report += "Element:\n" + element + "\n\n";
                        report += "Corresponding CSS\n" + rule + "\n\n";
                        result = percentage;
                    }
                }
            }
        }

        if(result > 0){
            report = "Present in html.\nCorresponding css present.\n\n" + report;
        }else{
            report = "Not present in html.\nCorresponding css not present.\n\n" + report;
        }

        return new TestResult(toString(), result, report);
    }

    /***
     * Searches through the css for a rule with the selector specified.
     * @param selector
     * @return The corresponding rule or null if not found
     */
    private CSSStyleRule searchCss(String selector, CSSStyleSheet sheet){
        CSSRuleList ruleList = sheet.getCssRules();

        for (int i = 0; i < ruleList.getLength(); i++) {
            CSSRule rule = ruleList.item(i);
            if (rule instanceof CSSStyleRule) {
                CSSStyleRule styleRule = (CSSStyleRule) rule;
                //Check prefix and whether the check whether rule is empty
                String selectorText = styleRule.getSelectorText();
                if(selectorText.equals(selector)) return styleRule;
            }
        }
        return null;
    }

}
