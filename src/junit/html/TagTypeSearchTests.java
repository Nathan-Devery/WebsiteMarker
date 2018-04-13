package junit.html;
import junit.TestUtility;
import model.htmlTests.TagSearchTest;
import model.htmlTests.TagType;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Nathan on 22/11/2017.
 * Test naming convention: [UnitOfWork__StateUnderTest__ExpectedBehavior]
 */
public class TagTypeSearchTests {

    @Test
    public void OrderedList_1Required1Present_ReturnFull() {
        ArrayList<Document> documents = TestUtility.getDocument("orderedList1");
        TagSearchTest test = new TagSearchTest(TagType.ORDEREDLIST, 1);
        assertEquals(0.5, test.runTest(documents, null, null, null, null, 0.5).getResult(), 0);
    }

    @Test
    public void OrderedList_2Required1Present_ReturnHalf() {
        ArrayList<Document> documents = TestUtility.getDocument("orderedList1");
        TagSearchTest test = new TagSearchTest(TagType.ORDEREDLIST, 2);
        assertEquals(0.5, test.runTest(documents, null,null, null, null, 1).getResult(), 0);
    }

    @Test
    public void OrderedList_2Required0Present_Return0() {
        ArrayList<Document> documents = TestUtility.getDocument("orderedList3");
        TagSearchTest test = new TagSearchTest(TagType.ORDEREDLIST, 2);
        assertEquals(0, test.runTest(documents, null,null, null, null, 1).getResult(), 0);
    }

    @Test
    public void unOrderedList_nonEmpty1Required_ReturnFull() {
        ArrayList<Document> documents = TestUtility.getDocument("unorderedList1");
        TagSearchTest test = new TagSearchTest(TagType.UNORDEREDLIST, 1);
        assertEquals(0.5, test.runTest(documents, null,null, null, null, 0.5).getResult(), 0);
    }

    @Test
    public void unOrderedList_nonEmpty_ReturnHalf() {
        ArrayList<Document> documents = TestUtility.getDocument("unorderedList1");
        TagSearchTest test = new TagSearchTest(TagType.UNORDEREDLIST, 2);
        assertEquals(0.5, test.runTest(documents, null,null, null, null, 1).getResult(), 0);
    }

    @Test
    public void image_2Required3Present_ReturnFull() {
        ArrayList<Document> documents = TestUtility.getDocument("image1");
        TagSearchTest test = new TagSearchTest(TagType.IMAGE, 2);
        assertEquals(2, test.runTest(documents, null,null, null, null, 2).getResult(), 0);
    }

    @Test
    public void image_3Required6Present_ReturnHalf() {
        ArrayList<Document> documents = TestUtility.getDocument("image2");
        TagSearchTest test = new TagSearchTest(TagType.IMAGE, 6);
        assertEquals(1, test.runTest(documents, null,null, null, null, 2).getResult(), 0);
    }

    @Test
    public void image_3Required0Present_Return0() {
        ArrayList<Document> documents = TestUtility.getDocument("image3");
        TagSearchTest test = new TagSearchTest(TagType.IMAGE, 6);
        assertEquals(0, test.runTest(documents, null, null, null, null, 2).getResult(), 0);
    }

    @Test
    public void googleMap_1Required1Present_ReturnFull() {
        ArrayList<Document> documents = TestUtility.getDocument("googleMap1");
        TagSearchTest test = new TagSearchTest(TagType.GOOGLEMAP, 1);
        assertEquals(1, test.runTest(documents, null, null, null, null, 1).getResult(), 0);
    }

    @Test
    public void googleMap_2Required1Present_ReturnHalf() {
        ArrayList<Document> documents = TestUtility.getDocument("googleMap2");
        TagSearchTest test = new TagSearchTest(TagType.GOOGLEMAP, 2);
        assertEquals(1, test.runTest(documents, null,  null, null, null, 1).getResult(), 0);
    }

    @Test
    public void googleMap_1Required0Present_Return0() {
        ArrayList<Document> documents = TestUtility.getDocument("googleMap3");
        TagSearchTest test = new TagSearchTest(TagType.GOOGLEMAP, 1);
        assertEquals(0, test.runTest(documents, null, null, null, null, 1).getResult(), 0);
    }

    @Test
    public void youtube_1Required1Present_ReturnFull() {
        ArrayList<Document> documents = TestUtility.getDocument("youtube1");
        TagSearchTest test = new TagSearchTest(TagType.YOUTUBE, 1);
        assertEquals(1, test.runTest(documents, null,  null, null, null, 1).getResult(), 0);
    }

    @Test
    public void youtube_2Required1Present_ReturnHalf() {
        ArrayList<Document> documents = TestUtility.getDocument("youtube2");
        TagSearchTest test = new TagSearchTest(TagType.YOUTUBE, 2);
        assertEquals(1, test.runTest(documents, null,  null, null, null, 1).getResult(), 0);
    }

    @Test
    public void youtube_1Required0Present_Return0() {
        ArrayList<Document> documents = TestUtility.getDocument("youtube3");
        TagSearchTest test = new TagSearchTest(TagType.YOUTUBE, 1);
        assertEquals(0, test.runTest(documents, null,  null, null, null, 1).getResult(), 0);
    }

    @Test
    public void form_1Required1Present_ReturnFull() {
        ArrayList<Document> documents = TestUtility.getDocument("form1");
        TagSearchTest test = new TagSearchTest(TagType.FORM, 1);
        assertEquals(1, test.runTest(documents, null,  null, null, null, 1).getResult(), 0);
    }

    @Test
    public void form_1Required0Present_Return0() {
        ArrayList<Document> documents = TestUtility.getDocument("structure1");
        TagSearchTest test = new TagSearchTest(TagType.FORM, 1);
        assertEquals(0, test.runTest(documents, null,  null, null, null, 1).getResult(), 0);
    }

    @Test
    public void textInput_1Required1Present_ReturnFull() {
        ArrayList<Document> documents = TestUtility.getDocument("textInput1");
        TagSearchTest test = new TagSearchTest(TagType.TEXTINPUT, 1);
        assertEquals(1, test.runTest(documents, null,  null, null, null, 1).getResult(), 0);
    }

    @Test
    public void textInput_1Required0Present_Return0() {
        ArrayList<Document> documents = TestUtility.getDocument("structure1");
        TagSearchTest test = new TagSearchTest(TagType.TEXTINPUT, 1);
        assertEquals(0, test.runTest(documents, null,  null, null, null, 1).getResult(), 0);
    }

    @Test
    public void submitButton_1Required1Present_ReturnFull() {
        ArrayList<Document> documents = TestUtility.getDocument("submitButton1");
        TagSearchTest test = new TagSearchTest(TagType.SUBMITBUTTON, 1);
        assertEquals(1, test.runTest(documents, null,  null, null, null, 1).getResult(), 0);
    }

    @Test
    public void submitButton_1Required0Present_Return0() {
        ArrayList<Document> documents = TestUtility.getDocument("structure1");
        TagSearchTest test = new TagSearchTest(TagType.SUBMITBUTTON, 1);
        assertEquals(0, test.runTest(documents, null,  null, null, null, 1).getResult(), 0);
    }

    @Test
    public void button_1Required1Present_ReturnFull() {
        ArrayList<Document> documents = TestUtility.getDocument("button1");
        TagSearchTest test = new TagSearchTest(TagType.BUTTON, 1);
        assertEquals(1, test.runTest(documents, null,  null, null, null, 1).getResult(), 0);
    }

    @Test
    public void button_1Required0Present_Return0() {
        ArrayList<Document> documents = TestUtility.getDocument("structure1");
        TagSearchTest test = new TagSearchTest(TagType.BUTTON, 1);
        assertEquals(0, test.runTest(documents, null,  null, null, null, 1).getResult(), 0);
    }

}
