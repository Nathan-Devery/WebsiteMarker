package junit.html;

import model.htmlTests.*;
import org.jsoup.nodes.Document;
import org.junit.Test;

import java.util.ArrayList;

import static junit.TestUtility.getDocument;
import static org.junit.Assert.assertEquals;

/**
 * Test naming convention: [UnitOfWork__StateUnderTest__ExpectedBehavior]
 */
public class HtmlDocTests {

    @Test
    public void pageCountIndex__CorrectNumPagesIndexPresent_ReturnFull() {
        PageCountIndexTest pageCountIndexTest = new PageCountIndexTest(4);
        ArrayList<Document> documents;
        documents = getDocument("structure1");
        documents.add(getDocument("structure2").get(0));
        documents.add(getDocument("structure3").get(0));
        documents.add(getDocument("index").get(0));
        assertEquals(6, pageCountIndexTest.runTest(documents, null,null, null, null,6).getResult(),0);
    }

    @Test
    public void pageCountIndex__HalfNumPagesIndexPresent_Return0() {
        PageCountIndexTest pageCountIndexTest = new PageCountIndexTest(5);
        ArrayList<Document> documents;
        documents = getDocument("structure1");
        documents.add(getDocument("structure2").get(0));
        documents.add(getDocument("structure3").get(0));
        documents.add(getDocument("index").get(0));
        assertEquals(0, pageCountIndexTest.runTest(documents, null,null, null, null,6).getResult(),0);
    }

    @Test
    public void pageCountIndex__AllNumPagesNoIndexPresent_Return0() {
        PageCountIndexTest pageCountIndexTest = new PageCountIndexTest(1);
        ArrayList<Document> documents;
        documents = getDocument("structure1");
        assertEquals(0, pageCountIndexTest.runTest(documents, null, null, null, null, 6).getResult(),0);
    }


    @Test
    public void title_2Required_ReturnFull() {
        TitleTest titleTest = new TitleTest(1);
        ArrayList<Document> documents = getDocument("title1");
        documents.add(getDocument("title2").get(0));

        assertEquals(1, titleTest.runTest(documents, null,null, null, null, 1).getResult(),0);
    }

    @Test
    public void title_someTitle_Return0() {
        TitleTest titleTest = new TitleTest(3);
        ArrayList<Document> documents = getDocument("title1");
        documents.add(getDocument("title3").get(0));

        assertEquals(0, titleTest.runTest(documents, null,null, null, null, 1).getResult(),0);
    }

    @Test
    public void title_noTitle_Return0() {
        TitleTest titleTest = new TitleTest(2);
        ArrayList<Document> documents = getDocument("title3");
        documents.add(getDocument("title3").get(0));
        documents.add(getDocument("title3").get(0));
        documents.add(getDocument("title3").get(0));

        assertEquals(0, titleTest.runTest(documents, null,null, null, null, 6).getResult(),0);
    }

    @Test
    public void altTextTest_1DocImagesAltVideoAlt_ReturnFull() {
        AltTextTest altTextTest = new AltTextTest();
        ArrayList<Document> documents = getDocument("alt1");

        assertEquals(2, altTextTest.runTest(documents, null,null, null, null, 2).getResult(),0);
    }

    @Test
    public void altTextTest_3DocImagesAltVideoAlt_ReturnFull() {
        AltTextTest altTextTest = new AltTextTest();
        ArrayList<Document> documents = getDocument("alt1");
        documents.add(getDocument("alt1").get(0));
        documents.add(getDocument("alt2").get(0));

        assertEquals(2, altTextTest.runTest(documents, null,null, null, null, 2).getResult(),0);
    }

    @Test
    public void altTextTest_3DocImages1NotVideoAlt_ReturnFull() {
        AltTextTest altTextTest = new AltTextTest();
        ArrayList<Document> documents = getDocument("alt1");
        documents.add(getDocument("alt1").get(0));
        documents.add(getDocument("alt3").get(0));

        assertEquals(2, altTextTest.runTest(documents, null,null, null, null, 2).getResult(),0);
    }

    @Test
    public void altTextTest_1DocImages1NoAltVideoAlt_ReturnFull() {
        AltTextTest altTextTest = new AltTextTest();
        ArrayList<Document> documents = getDocument("alt6");

        assertEquals(2, altTextTest.runTest(documents, null,null, null, null, 2).getResult(),0);
    }
    @Test
    public void AltTextTest_3DocImages1NotVideoNot1_Return0() {
        AltTextTest altTextTest = new AltTextTest();
        ArrayList<Document> documents = getDocument("alt4");
        documents.add(getDocument("alt4").get(0));
        documents.add(getDocument("alt4").get(0));

        assertEquals(0, altTextTest.runTest(documents, null,null, null, null, 2).getResult(),0);
    }

    @Test
    public void altTextTest_3DocNoImagesNoVideo_Return0() {
        AltTextTest altTextTest = new AltTextTest();
        ArrayList<Document> documents = getDocument("alt5");
        documents.add(getDocument("alt5").get(0));
        documents.add(getDocument("alt5").get(0));

        assertEquals(0, altTextTest.runTest(documents, null,null, null, null, 2).getResult(),0);
    }

    @Test
    public void altTextTest_1Doc1ImageAltNoVideoAlt_Return0() {
        AltTextTest altTextTest = new AltTextTest();
        ArrayList<Document> documents = getDocument("alt7");

        assertEquals(1, altTextTest.runTest(documents, null,null, null, null, 2).getResult(),0);
    }

    @Test
    public void altTextTest_2DocNoImageAlt1VideoAlt_Return0() {
        AltTextTest altTextTest = new AltTextTest();
        ArrayList<Document> documents = getDocument("alt8");
        documents.add(getDocument("alt5").get(0));

        assertEquals(0.5, altTextTest.runTest(documents, null,null, null, null, 1).getResult(),0);
    }

    @Test
    public void navigationBar_MultiFile1UnorderedBar1CorrectLink_ReturnFull() {
        NavigationBarTest navigationBarTest = new NavigationBarTest();
        ArrayList<Document> documents = getDocument("navigationBar1");
        documents.add(getDocument("navigationBar2").get(0));

        assertEquals(6, navigationBarTest.runTest(documents, null, null, null, null, 6).getResult(),0);
    }

    @Test
    public void navigationBar_1File1UnorderedBar1CorrectLink_ReturnFull() {
        NavigationBarTest navigationBarTest = new NavigationBarTest();
        ArrayList<Document> documents = getDocument("navigationBar1");

        assertEquals(6, navigationBarTest.runTest(documents, null, null, null, null, 6).getResult(),0);
    }

    @Test
    public void navigationBar_1File1UnorderedBar1LinksIncorrect_Return0() {
        NavigationBarTest navigationBarTest = new NavigationBarTest();
        ArrayList<Document> documents = getDocument("navigationBar4");

        assertEquals(0, navigationBarTest.runTest(documents, null, null, null, null, 6).getResult(),0);
    }

    @Test
    public void navigationBar_MultiFile1orderedBar1LiCorrectLink_ReturnFull() {
        NavigationBarTest navigationBarTest = new NavigationBarTest();
        ArrayList<Document> documents = getDocument("navigationBar3");
        documents.add(getDocument("navigationBar2").get(0));
        documents.add(getDocument("navigationBar2").get(0));

        assertEquals(1, navigationBarTest.runTest(documents, null, null, null, null, 1).getResult(),0);
    }

    @Test
    public void navigationBar_1File1orderedBar1LiCorrectLink_ReturnFull() {
        NavigationBarTest navigationBarTest = new NavigationBarTest();
        ArrayList<Document> documents = getDocument("navigationBar3");

        assertEquals(1, navigationBarTest.runTest(documents, null, null, null, null, 1).getResult(),0);
    }

    @Test
    public void navigationBar_1File1orderedBar1LinksIncorrect_Return0() {
        NavigationBarTest navigationBarTest = new NavigationBarTest();
        ArrayList<Document> documents = getDocument("navigationBar5");

        assertEquals(0, navigationBarTest.runTest(documents, null, null, null, null, 6).getResult(),0);
    }

    @Test
    public void navigationBar_3File1UnOrderedBar1CorrectLink1OrderedLiCorrect_ReturnFull() {
        NavigationBarTest navigationBarTest = new NavigationBarTest();
        ArrayList<Document> documents = getDocument("navigationBar1");
        documents.add(getDocument("navigationBar3").get(0));
        documents.add(getDocument("navigationBar2").get(0));

        assertEquals(10, navigationBarTest.runTest(documents, null, null, null, null, 10).getResult(),0);
    }

    @Test
    public void navigationBar_3File1UnOrderedBar1IncorrectLinks1OrderedLiIncorrectLinks_Return0() {
        NavigationBarTest navigationBarTest = new NavigationBarTest();
        ArrayList<Document> documents = getDocument("navigationBar5");
        documents.add(getDocument("navigationBar4").get(0));
        documents.add(getDocument("navigationBar2").get(0));

        assertEquals(0, navigationBarTest.runTest(documents, null, null, null, null, 10).getResult(),0);
    }

    @Test
    public void navigationBar_2FilesUnorderedOrderedNotLinks_return0(){
        NavigationBarTest navigationBarTest = new NavigationBarTest();
        ArrayList<Document> documents = getDocument("navigationBar6");
        documents.add(getDocument("navigationBar6").get(0));

        assertEquals(0, navigationBarTest.runTest(documents, null, null, null, null, 10).getResult(),0);
    }

    @Test
    public void navigationBar_2FilesUnorderedNotJustLinks1CorrectLink_returnFull(){
        NavigationBarTest navigationBarTest = new NavigationBarTest();
        ArrayList<Document> documents = getDocument("navigationBar7");
        documents.add(getDocument("navigationBar2").get(0));

        assertEquals(10, navigationBarTest.runTest(documents, null, null, null, null, 10).getResult(),0);
    }

    @Test
    public void navigationBar_2FilesUnorderedNotJustLinksNoCorrectLink_returnFull(){
        NavigationBarTest navigationBarTest = new NavigationBarTest();
        ArrayList<Document> documents = getDocument("navigationBar8");
        documents.add(getDocument("navigationBar2").get(0));

        assertEquals(0, navigationBarTest.runTest(documents, null, null, null, null, 10).getResult(),0);
    }

    @Test
    public void formsTest_AllWorkButton_returnFull(){
        FormsTest formsTest = new FormsTest();
        ArrayList<Document> documents = getDocument("formTest1");
        documents.add(getDocument("structure1").get(0));

        assertEquals(10, formsTest.runTest(documents, null, null, null, null, 10).getResult(),0);
    }

    @Test
    public void formsTest_AllWorkSubmitButton_returnFull(){
        FormsTest formsTest = new FormsTest();
        ArrayList<Document> documents = getDocument("formTest2");
        documents.add(getDocument("structure1").get(0));

        assertEquals(1, formsTest.runTest(documents, null, null, null, null, 1).getResult(),0);
    }

    @Test
    public void formsTest_AllWorkNoForm_return0(){
        FormsTest formsTest = new FormsTest();
        ArrayList<Document> documents = getDocument("formTest3");
        documents.add(getDocument("structure1").get(0));

        assertEquals(0, formsTest.runTest(documents, null, null, null, null, 1).getResult(),0);
    }

    @Test
    public void formsTest_AllWorkNoTextInput_return0(){
        FormsTest formsTest = new FormsTest();
        ArrayList<Document> documents = getDocument("formTest4");
        documents.add(getDocument("structure1").get(0));

        assertEquals(0, formsTest.runTest(documents, null, null, null, null, 1).getResult(),0);
    }

    @Test
    public void formsTest_1WorkingPage1NotWokring_return1(){
        FormsTest formsTest = new FormsTest();
        ArrayList<Document> documents = getDocument("formTest1");
        documents.add(getDocument("formTest4").get(0));

        assertEquals(2, formsTest.runTest(documents, null, null, null, null, 2).getResult(),0);
    }

    @Test
    public void formsTest_allWorkingNoButtons_return0(){
        FormsTest formsTest = new FormsTest();
        ArrayList<Document> documents = getDocument("formTest5");

        assertEquals(0, formsTest.runTest(documents, null, null, null, null, 2).getResult(),0);
    }


}
