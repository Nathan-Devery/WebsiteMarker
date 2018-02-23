package model.htmlTests;

import model.TestResult;
import model.Testable;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.w3c.dom.css.CSSStyleSheet;

import java.util.ArrayList;

/**
 * Created by Nathan on 22/11/2017.
 */
public class TagSearchTest extends Testable {

    TagType tagType;

    public TagSearchTest(String name, TagType tagType) {
        super(name);
        this.tagType = tagType;
    }

    @Override
    public TestResult runTest(ArrayList<Document> documents, CSSStyleSheet sheet) {
        String report = "";
        boolean result = false;
        for(Document document: documents){
            Elements elements = document.select(tagType.searchString);
            if(!elements.isEmpty()){
                result = true;
                for(Element element: elements){
                    report += element;
                    report += "\n";
                }
            }
        }
        return new TestResult(this.toString(), result, report);
    }
}
