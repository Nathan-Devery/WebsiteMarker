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
 * Created by Nathan on 22/11/2017.
 */
public class TagSearchTest extends Testable {

    TagType tagType;
    int numberRequired;

    public TagSearchTest(TagType tagType, int numberRequired) {
        super(tagType.name());
        this.tagType = tagType;
        this.numberRequired = numberRequired;
    }

    @Override
    public TestResult runTest(ArrayList<Document> documents, CSSStyleSheet sheet, CompilationUnitTree tree, double percentage) {
        String report = "";
        double result = 0;
        int elementCount = 0;
        for(Document document: documents){
            Elements elements = document.select(tagType.searchString);
            if(!elements.isEmpty()){
                elementCount += elements.size();
                for(Element element: elements){
                    report += element;
                    report += "\n";
                }
            }
        }

        if(elementCount >= numberRequired) result = percentage;
        return new TestResult(this.toString(), result, report);
    }
}
