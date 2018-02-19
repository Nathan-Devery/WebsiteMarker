package model.htmlTests;

import model.Testable;
import org.jsoup.nodes.Document;
import org.w3c.dom.css.CSSStyleSheet;

import java.util.ArrayList;

/**
 * Created by Nathan on 25/11/2017.
 */
public class PageCountTest extends Testable {

    int requirePageNumber;

    public PageCountTest(int requiredPageNumber) {
        super("PAGE COUNT(" + requiredPageNumber + ")");
        this.requirePageNumber = requiredPageNumber;
    }

    @Override
    public void runTest(ArrayList<Document> documents, ArrayList<CSSStyleSheet> sheets) {
        clear();

        if(documents.size() >= requirePageNumber){
            result = true;
            report += "Correct";
        }else{
            report += "Incorrect";
        }
        report += "- expected >=" + requirePageNumber + ", actual: " + documents.size();
    }
}
