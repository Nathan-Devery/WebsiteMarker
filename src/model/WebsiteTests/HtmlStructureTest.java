package model.WebsiteTests;

import model.Testable;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Nathan on 24/11/2017.
 */
public class HtmlStructureTest extends Testable {

    public HtmlStructureTest() {
        super("HTML STRUCTURE");
    }

    @Override
    public void runTest(ArrayList<Document> documents) {
        clear();

        int correct = 0;
        for(Document document: documents){
            //Check composite structure
            for(Element element: document.select("html")){
                if(!element.select("head").isEmpty() && !element.select("body").isEmpty() && !element.select("title").isEmpty()){
                    correct++;
                }
            }
        }
        if(correct == documents.size()){
            result = true;
            report += "Html structure correct for all " + documents.size() + " pages: (html, title, body)";
        }else{
            report += "Wrong html structure for " + (documents.size() - correct) + "pages";
        }
    }
}
