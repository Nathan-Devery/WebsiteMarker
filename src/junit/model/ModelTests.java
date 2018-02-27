package junit.model;

import model.Model;
import model.htmlTests.HtmlStructureTest;
import org.jsoup.Jsoup;
import org.junit.Test;
import org.jsoup.nodes.Document;
import org.w3c.dom.css.CSSStyleSheet;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static junit.TestUtility.getDocument;
import static org.junit.Assert.assertEquals;

/**
 * Created by Nathan on 27/02/2018.
 * Test naming convention: [UnitOfWork__StateUnderTest__ExpectedBehavior]
 */
public class ModelTests {

    private static String testFilePath = "src/junit/model/modelTestFiles/";

    @Test
    public void LoadFiles_3correct_CorrectlyParsedStored() throws IOException{
        Model model = new Model();
        File[] files = new File[1];
        files[0] = new File(testFilePath + "loadFiles1");
        files[0] = new File(testFilePath + "loadFiles1");
        files[0] = new File(testFilePath + "loadFiles1");
        model.loadFiles(files);

        assertEquals(1, model.getAssignments().size());
        Document document = model.getAssignments().get(0).getHtmlDocs().get(0);
        assertEquals(3, document.getAllElements().size());

    }

    /*
    private ArrayList<Document> loadHtml(String fileName) throws IOException{
        ArrayList<Document> documents = new ArrayList<>();
        File file = new File(testFilePath + fileName + ".html");
        documents.add(Jsoup.parse(file, "UTF-8", "");;
        return documents;
    }
    */



}
