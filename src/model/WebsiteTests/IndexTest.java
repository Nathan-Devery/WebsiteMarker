package model.WebsiteTests;

import model.Testable;
import org.jsoup.nodes.Document;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Nathan on 25/11/2017.
 */
public class IndexTest extends Testable {

    public IndexTest() {
        super("INDEX PAGE");
    }

    @Override
    public void runTest(ArrayList<Document> documents) {
        String path = documents.get(0).location();
        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();
        for(File file: listOfFiles){
            if(file.getName().equals("index.html")){
                result = true;
                report += "Index present at: "+ path + file.getName();
                return;
            }
        }
        report += "No index page present in " + path;
    }
}
