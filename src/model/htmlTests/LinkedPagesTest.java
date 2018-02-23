package model.htmlTests;

import model.TestResult;
import model.Testable;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.w3c.dom.css.CSSStyleSheet;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Nathan on 25/11/2017.
 * Checks whether whole website can be navigated from at least one entry point.
 * Checks if connected graph
 */

//TODO Make this functional
public class LinkedPagesTest extends Testable {

    ArrayList<Document> visited;
    String path;

    public LinkedPagesTest() {
        super("NAVIGABLE TO ALL PAGES");
    }

    @Override
    public TestResult runTest(ArrayList<Document> documents, CSSStyleSheet sheet) {
        boolean result = false;
        String report = "";

        visited = new ArrayList<>();
        path = "";

        path = documents.get(0).location();

        for(Document document: documents){
            visit(document);
        }
        if(visited.size() == documents.size()){
            result = true;
            report += documents.size() + " pages can be navigated from some entry point";
        }else{
            report += documents.size() - visited.size() + "pages cannot be visited from all possible entry points";
        }
        return new TestResult(toString(), result, report);
    }

    private void visit(Document document){
        for(Document aVisited: visited){
            if(document.html().equals(aVisited.html())){
                return;
            }
        }
        visited.add(document);
        Elements children = document.select("a[href$=html]");
        for(Element child: children){
            //load document
            String childFileName = child.attr("href");
            File childPath = new File(path + childFileName);
            try {
                Document childDoc = Jsoup.parse(childPath, "UTF-8", "Temp");
                visit(childDoc);
            }catch(Exception e){
                e.printStackTrace();
            }

        }
    }
}
