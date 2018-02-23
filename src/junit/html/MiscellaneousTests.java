package junit.html;

import model.htmlTests.HtmlStructureTest;
import model.htmlTests.IndexTest;
import model.htmlTests.LinkedPagesTest;
import model.htmlTests.PageCountTest;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;
import org.w3c.dom.css.CSSStyleSheet;

import java.io.File;
import java.util.ArrayList;

import static junit.TestUtility.getDocument;
import static org.junit.Assert.assertEquals;

/**
 * Created by Nathan on 24/11/2017.
 * Test naming convention: [UnitOfWork__StateUnderTest__ExpectedBehavior]
 */
public class MiscellaneousTests {

    CSSStyleSheet cssDoc = null;   //Not used, just for testing sake

    @Test
    public void StructureTest_correctStructure_ReturnTrue() {
        HtmlStructureTest structureTest = new HtmlStructureTest();
        assertEquals(true, structureTest.runTest(getDocument("structure1"), cssDoc).getResult());
    }

    @Test
    public void LinkedPagesTest_2Linked_ReturnTrue() {
        LinkedPagesTest pagesTest = new LinkedPagesTest();
        ArrayList<Document> documents = new ArrayList<>();
        documents.add(getDocument("linkedPages1").get(0));
        documents.add(getDocument("linkedPages2").get(0));

        assertEquals(true, pagesTest.runTest(documents, cssDoc).getResult());
    }

    @Test
    public void LinkedPagesTest_4Linked_ReturnTrue() {
        LinkedPagesTest pagesTest = new LinkedPagesTest();
        ArrayList<Document> documents = new ArrayList<>();
        documents.add(getDocument("linkedPages3").get(0));
        documents.add(getDocument("linkedPages4").get(0));
        documents.add(getDocument("linkedPages5").get(0));
        documents.add(getDocument("linkedPages6").get(0));

        assertEquals(true, pagesTest.runTest(documents, cssDoc).getResult());
    }

    @Test
    public void LinkedPagesTest_2Linked1Unlinked_ReturnFalse() {
        LinkedPagesTest pagesTest = new LinkedPagesTest();
        ArrayList<Document> documents = new ArrayList<>();
        documents.add(getDocument("linkedPages3").get(0));
        documents.add(getDocument("linkedPages4").get(0));
        documents.add(getDocument("linkedPages6").get(0));

        assertEquals(false, pagesTest.runTest(documents, cssDoc).getResult());
    }

    @Test
    public void PageCount_3_ReturnTrue() {
        PageCountTest pagesTest = new PageCountTest(3);
        ArrayList<Document> documents = new ArrayList<>();
        documents.add(getDocument("linkedPages3").get(0));
        documents.add(getDocument("linkedPages4").get(0));
        documents.add(getDocument("linkedPages6").get(0));

        assertEquals(true, pagesTest.runTest(documents, cssDoc).getResult());
    }

    @Test
    public void PageCount_5_ReturnFalse() {
        PageCountTest pagesTest = new PageCountTest(5);
        ArrayList<Document> documents = new ArrayList<>();
        documents.add(getDocument("linkedPages3").get(0));
        documents.add(getDocument("linkedPages4").get(0));
        documents.add(getDocument("linkedPages6").get(0));

        assertEquals(false, pagesTest.runTest(documents, cssDoc).getResult());
    }

    @Test
    public void PageCount_2_ReturnTrue() {
        PageCountTest pagesTest = new PageCountTest(2);
        ArrayList<Document> documents = new ArrayList<>();
        documents.add(getDocument("linkedPages3").get(0));
        documents.add(getDocument("linkedPages4").get(0));
        documents.add(getDocument("linkedPages6").get(0));

        assertEquals(true, pagesTest.runTest(documents, cssDoc).getResult());
    }

    @Test
    public void IndexPage_IndexPresent_ReturnTrue() {
        IndexTest indexTest = new IndexTest();
        assertEquals(true, indexTest.runTest(getDocument("index"), cssDoc).getResult());
    }



}
