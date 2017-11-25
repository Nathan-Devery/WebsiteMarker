package model.WebsiteTests;

import model.TagType;
import model.Testable;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

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
    public void runTest(ArrayList<Document> documents) {
        clear();

        for(Document document: documents){
            Elements elements = document.select(tagType.searchString);
            if(!elements.isEmpty()){
                this.result = true;
                for(Element element: elements){
                    report += element;
                    report += "\n";
                }
            }
        }
    }
}
