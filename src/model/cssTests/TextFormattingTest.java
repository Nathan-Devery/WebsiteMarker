package model.cssTests;

import jdk.nashorn.api.tree.CompilationUnitTree;
import model.TestResult;
import model.Testable;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.jsoup.select.Selector;
import org.w3c.dom.css.*;

import java.util.ArrayList;

/**
 * Checks whether font-family, font-size and color are present in the css.
 * Checks whether html elements are referenced by this css.
 * Full marks: All properties present, all referencing a existing html element.
 */
public class TextFormattingTest extends Testable {

    private String report = "";

    public TextFormattingTest() {
        super("TEXT FORMATTING");
    }

    @Override
    public String getDescription() {
        return  "Checks whether font-family, font-size and color are present in the css.\n" +
                "Checks whether html elements are referenced by this css.\n" +
                "Full marks: All properties present, all referencing a existing html element.";
    }

    @Override
    public TestResult runTest(ArrayList<Document> documents, ArrayList<Document> xmlDocuments, CSSStyleSheet sheet, String cssDocString, CompilationUnitTree tree, double percentage) {
        String reportIntro = "";
        boolean allUsed = true;

        if(checkProperty(Property.FONTFAMILY, documents, sheet)){
            reportIntro += "font-family: present.\n";
        }else{
            reportIntro += "font-family: not present.\n";
            allUsed = false;
        }

        if(checkProperty(Property.FONTSIZE, documents, sheet)){
            reportIntro += "font-size: present.\n";
        }else{
            reportIntro += "font-size: not present.\n";
            allUsed = false;
        }

        if(checkProperty(Property.COLOR, documents, sheet)){
            reportIntro += "font-color: present.\n";
        }else{
            reportIntro += "font-color: not present.\n";
            allUsed = false;
        }

        double result = 0;
        if(allUsed){
            result = percentage;
            reportIntro = "All text formatting present.\nEach property references a HTML element at least once.\n\n" + reportIntro;
        }else{
            reportIntro = "All text formatting is not present.\nEach property does not reference a HTML element at least once. \n\n" + reportIntro;
        }

        report = reportIntro + "\n" + report;

        TestResult testResult = new TestResult(toString(), result, report);
        reset();
        return testResult;
    }

    private boolean checkProperty(Property property,ArrayList<Document> documents, CSSStyleSheet sheet){
        report += "\n" + property.propertyString + "\n\n";

        boolean result = false;

        CSSRuleList ruleList = sheet.getCssRules();
        for (int i = 0; i < ruleList.getLength(); i++) {
            CSSRule rule = ruleList.item(i);
            if (rule instanceof CSSStyleRule) {
                CSSStyleRule styleRule = (CSSStyleRule) rule;
                if (styleRule.getStyle().getLength() != 0) {   //Check css isn't empty
                    CSSStyleDeclaration styleDeclaration = styleRule.getStyle();
                    styles:for (int j = 0; j < styleDeclaration.getLength(); j++) {
                        String foundProperty = styleDeclaration.item(j);
                        //System.out.println(styleDeclaration.getPropertyCSSValue(foundProperty).getCssText());
                        if (foundProperty.equals(property.propertyString)) {
                            for (Document document : documents) {
                                //Ensure there is a effected html element
                                try {
                                    Elements affectedElements = document.select(styleRule.getSelectorText());   //Jsoup allows the searching of classes as divs directly; ie: .myClass, #myDiv
                                    if(!affectedElements.isEmpty()){
                                        report += rule.getCssText() + "\n";
                                        result = true;
                                        continue styles;
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
        return result;
    }

    private void reset(){
        report = "";
    }

}
