package model.htmlTests;

import jdk.nashorn.api.tree.CompilationUnitTree;
import model.TestResult;
import model.Testable;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.w3c.dom.css.CSSStyleSheet;
import java.util.ArrayList;

/**
 * Checks whether a ol and or ul list is present.
 * Checks whether that list contains at least one link to a local website.
 * (Given the broad implementations of navigation bars, these checks gave consistent results).
 * Full marks: ol or ul present, contains at least one local link.
 */
public class NavigationBarTest extends Testable {

    private String report = "";

    public NavigationBarTest() {
        super("NAVIGATION BAR");
    }

    @Override
    public String getDescription() {
        return  "Checks whether a ol and or ul list is present.\n" +
                "Checks whether that list contains at least one link to a local website.\n" +
                "(Given the broad implementations of navigation bars, these checks gave consistent results).\n" +
                "Full marks: ol or ul present, contains at least one local link.";
    }

    @Override
    public TestResult runTest(ArrayList<Document> documents, ArrayList<Document> xmlDocs, CSSStyleSheet sheet, String cssDocString, CompilationUnitTree tree, double percentage) {
        double result = 0;
        if(checkNavigation(TagType.ORDEREDLIST, documents) || checkNavigation(TagType.UNORDEREDLIST, documents)){
            result = percentage;
        }

        if(report.equals("")) report += "No evidence of a navigation bar using <ul> or <ol>.";

        TestResult testResult = new TestResult(toString(), result, report);
        reset();
        return testResult;

    }

    private boolean checkNavigation(TagType listType, ArrayList<Document> documents){
        boolean result = false;

        for(Document document: documents) {
            Elements elements = document.select(listType.searchString);
            lists: for(Element element: elements){
                for(Element child: element.children()){
                    if(child.children().size() >0 && child.tag().getName().equals("li")){
                        child = child.child(0);
                    }

                    if(!child.tag().getName().equals("a")) continue lists;    //List must contain only links <a>
                    for(Document document1: documents){
                       if(child.attr("href").equals(document1.location())){
                           report += "\n-" + document.location() + "-\n";
                           report += "\n" + element + "\n";
                           result = true;
                           return result;
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
