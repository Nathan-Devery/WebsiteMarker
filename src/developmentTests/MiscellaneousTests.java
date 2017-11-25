package developmentTests;

import model.WebsiteTests.HtmlStructureTest;
import model.WebsiteTests.IndexTest;
import model.WebsiteTests.LinkedPagesTest;
import model.WebsiteTests.PageCountTest;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * Created by Nathan on 24/11/2017.
 * Test naming convention: [UnitOfWork__StateUnderTest__ExpectedBehavior]
 */
public class MiscellaneousTests {

    @Test
    public void StructureTest_correctStructure_ReturnTrue() {
        HtmlStructureTest structureTest = new HtmlStructureTest();
        structureTest.runTest(getDocument("structure1"));
        assertEquals(true, structureTest.getResult());
    }

    @Test
    public void LinkedPagesTest_2Linked_ReturnTrue() {
        LinkedPagesTest pagesTest = new LinkedPagesTest();
        ArrayList<Document> documents = new ArrayList<>();
        documents.add(getDocument("linkedPages1").get(0));
        documents.add(getDocument("linkedPages2").get(0));

        pagesTest.runTest(documents);
        assertEquals(true, pagesTest.getResult());
    }

    @Test
    public void LinkedPagesTest_4Linked_ReturnTrue() {
        LinkedPagesTest pagesTest = new LinkedPagesTest();
        ArrayList<Document> documents = new ArrayList<>();
        documents.add(getDocument("linkedPages3").get(0));
        documents.add(getDocument("linkedPages4").get(0));
        documents.add(getDocument("linkedPages5").get(0));
        documents.add(getDocument("linkedPages6").get(0));

        pagesTest.runTest(documents);
        assertEquals(true, pagesTest.getResult());
    }

    @Test
    public void LinkedPagesTest_2Linked1Unlinked_ReturnFalse() {
        LinkedPagesTest pagesTest = new LinkedPagesTest();
        ArrayList<Document> documents = new ArrayList<>();
        documents.add(getDocument("linkedPages3").get(0));
        documents.add(getDocument("linkedPages4").get(0));
        documents.add(getDocument("linkedPages6").get(0));

        pagesTest.runTest(documents);
        assertEquals(false, pagesTest.getResult());
    }

    @Test
    public void PageCount_3_ReturnTrue() {
        PageCountTest pagesTest = new PageCountTest(3);
        ArrayList<Document> documents = new ArrayList<>();
        documents.add(getDocument("linkedPages3").get(0));
        documents.add(getDocument("linkedPages4").get(0));
        documents.add(getDocument("linkedPages6").get(0));

        pagesTest.runTest(documents);
        assertEquals(true, pagesTest.getResult());
    }

    @Test
    public void PageCount_5_ReturnFalse() {
        PageCountTest pagesTest = new PageCountTest(5);
        ArrayList<Document> documents = new ArrayList<>();
        documents.add(getDocument("linkedPages3").get(0));
        documents.add(getDocument("linkedPages4").get(0));
        documents.add(getDocument("linkedPages6").get(0));

        pagesTest.runTest(documents);
        assertEquals(false, pagesTest.getResult());
    }

    @Test
    public void PageCount_2_ReturnTrue() {
        PageCountTest pagesTest = new PageCountTest(2);
        ArrayList<Document> documents = new ArrayList<>();
        documents.add(getDocument("linkedPages3").get(0));
        documents.add(getDocument("linkedPages4").get(0));
        documents.add(getDocument("linkedPages6").get(0));

        pagesTest.runTest(documents);
        assertEquals(true, pagesTest.getResult());
    }

    @Test
    public void IndexPage_IndexPresent_ReturnTrue() {
        IndexTest indexTest = new IndexTest();
        indexTest.runTest(getDocument("index"));
        assertEquals(true, indexTest.getResult());
    }

    private ArrayList<Document> getDocument(String testFileName){
        File input = new File("src/testFiles/" + testFileName + ".html");
        try {
            String currentDirectory = input.getAbsolutePath().substring(0, input.getAbsolutePath().length() - input.getName().length());
            Document doc = Jsoup.parse(input, "UTF-8", currentDirectory);
            ArrayList toReturn = new ArrayList();
            toReturn.add(doc);
            return toReturn;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
