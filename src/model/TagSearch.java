package model;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

/**
 * Created by Nathan on 22/11/2017.
 */
public class TagSearch extends Testable {

    TagType tagType;

    public TagSearch(String name, TagType tagType) {
        super(name);
        this.tagType = tagType;
    }

    @Override
    public void runTest(ArrayList<Document> documents) {
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
