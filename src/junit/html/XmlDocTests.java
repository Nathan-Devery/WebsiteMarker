package junit.html;

import model.htmlTests.*;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.Test;

import javax.print.Doc;
import java.util.ArrayList;

import static junit.TestUtility.getDocument;
import static junit.TestUtility.getXMLDocument;
import static org.junit.Assert.assertEquals;

/**
 * Test naming convention: [UnitOfWork__StateUnderTest__ExpectedBehavior]
 */
public class XmlDocTests {

    @Test
    public void structureTest_CorrectStructure1Percent_ReturnFull() {
        HtmlStructureTest structureTest = new HtmlStructureTest();
        assertEquals(1.0, structureTest.runTest(null, getXMLDocument("structure1"),null, null, null, 1).getResult(), 0);
    }

    @Test
    public void structureTest_noBodyClosingCorrectStructure9Percent_ReturnHalf() {
        HtmlStructureTest structureTest = new HtmlStructureTest();
        assertEquals(4.5, structureTest.runTest(null, getXMLDocument("structure2"), null, null, null, 4.5).getResult(),0);
    }

    @Test
    public void structureTest_CorrectHalfStructure3Percent_ReturnHalf() {
        HtmlStructureTest structureTest = new HtmlStructureTest();

        ArrayList<Document> xmlDocs;
        xmlDocs = getXMLDocument("structure2");
        xmlDocs.add(getXMLDocument("structure3").get(0));

        assertEquals(1.5, structureTest.runTest(null, xmlDocs,null, null, null, 3).getResult(),0);
    }

    @Test
    public void structureTest_AllCorrectMultiple_ReturnFull() {
        HtmlStructureTest structureTest = new HtmlStructureTest();

        ArrayList<Document> xmlDocs;
        xmlDocs = getXMLDocument("structure1");
        xmlDocs.add(getXMLDocument("structure1").get(0));
        xmlDocs.add(getXMLDocument("structure8").get(0));

        assertEquals(20, structureTest.runTest(null, xmlDocs,null, null,  null,20).getResult(),0);
    }

    @Test
    public void structureTest_MissingDoctype_Return0() {
        HtmlStructureTest structureTest = new HtmlStructureTest();
        assertEquals(0, structureTest.runTest(null, getXMLDocument("structure4"),null, null, null, 1).getResult(), 0);
    }

    @Test
    public void structureTest_MissingHead_Return0() {
        HtmlStructureTest structureTest = new HtmlStructureTest();
        assertEquals(0, structureTest.runTest(null, getXMLDocument("structure7"),null, null, null, 10).getResult(), 0);
    }

    @Test
    public void structureTest_MissingBody_Return0() {
        HtmlStructureTest structureTest = new HtmlStructureTest();
        assertEquals(0, structureTest.runTest(null, getXMLDocument("structure5"),null, null, null, 1).getResult(), 0);
    }

    @Test
    public void structureTest_MissingHtml_Return0() {
        HtmlStructureTest structureTest = new HtmlStructureTest();
        assertEquals(0, structureTest.runTest(null, getXMLDocument("structure6"),null, null, null, 1).getResult(), 0);
    }

    @Test
    public void structureTest_BodyOutsideHtml_Return0() {
        HtmlStructureTest structureTest = new HtmlStructureTest();
        assertEquals(0, structureTest.runTest(null, getXMLDocument("structure9"),null, null, null, 1).getResult(), 0);
    }

    @Test
    public void structureTest_HeadOutsideHtml_Return0() {
        HtmlStructureTest structureTest = new HtmlStructureTest();
        assertEquals(0, structureTest.runTest(null, getXMLDocument("structure10"),null, null, null, 1).getResult(), 0);
    }

    @Test
    public void structureTest_NoClosingCorrectOrder_Return0() {
        HtmlStructureTest structureTest = new HtmlStructureTest();
        assertEquals(1, structureTest.runTest(null, getXMLDocument("structure11"),null, null, null, 1).getResult(), 0);
    }

    @Test
    public void tableTest_allWorking_returnFull(){
        TableTest tableTest = new TableTest();
        ArrayList<Document> documents = getXMLDocument("table1");

        assertEquals(2, tableTest.runTest(null, documents, null, null, null, 2).getResult(),0);
    }

    @Test
    public void tableTest_1allWorking1NotWorking_returnFull(){
        TableTest tableTest = new TableTest();
        ArrayList<Document> documents = getXMLDocument("table1");
        documents.add(getDocument("table2").get(0));

        assertEquals(1, tableTest.runTest(null, documents, null, null, null, 1).getResult(),0);
    }

    @Test
    public void tableTest_allWorkingNoTable_returnFull(){
        TableTest tableTest = new TableTest();
        ArrayList<Document> documents = getXMLDocument("table1");
        documents.add(getDocument("table2").get(0));

        assertEquals(1, tableTest.runTest(null, documents, null, null, null, 1).getResult(),0);
    }

    @Test
    public void tableTest_allWorkingNoTh_returnHalf(){
        TableTest tableTest = new TableTest();
        ArrayList<Document> documents = getXMLDocument("table3");
        documents.add(getDocument("table2").get(0));

        assertEquals(1, tableTest.runTest(null, documents, null, null, null, 1).getResult(),0);
    }

    @Test
    public void tableTest_allWorkingNoTr_return0(){
        TableTest tableTest = new TableTest();
        ArrayList<Document> documents = getXMLDocument("table4");
        documents.add(getDocument("table2").get(0));

        assertEquals(0, tableTest.runTest(null, documents, null, null, null, 1).getResult(),0);
    }

    @Test
    public void tableTest_allWorkingNoTdHt_returnHalf(){
        TableTest tableTest = new TableTest();
        ArrayList<Document> documents = getXMLDocument("table2");
        documents.add(getDocument("table2").get(0));

        assertEquals(0, tableTest.runTest(null, documents, null, null, null, 20).getResult(),0);
    }

    @Test
    public void tableTest_allWorkingNoTdNoHt_return0(){
        TableTest tableTest = new TableTest();
        ArrayList<Document> documents = getXMLDocument("table6");

        assertEquals(0, tableTest.runTest(null, documents, null, null, null, 20).getResult(),0);
    }

}
