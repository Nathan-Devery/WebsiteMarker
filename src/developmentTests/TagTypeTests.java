package developmentTests;
import model.TagType;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.File;

/**
 * Created by Nathan on 22/11/2017.
 * Test naming convention: [UnitOfWork__StateUnderTest__ExpectedBehavior]
 */
public class TagTypeTests {

    @Test
    public void Paragraph_1_1Return() {
        assertEquals(1, getNumber("p1", TagType.PARAGRAPH));
    }

    @Test
    public void Paragraph_4_4Return() {
        assertEquals(4, getNumber("p2", TagType.PARAGRAPH));
    }

    @Test
    public void Link_2_2Return() {
        assertEquals(2, getNumber("l1", TagType.LINK));
    }

    @Test
    public void Link_5_5Return() {
        assertEquals(5, getNumber("l2", TagType.LINK));
    }

    @Test
    public void Image_2_2Return() {
        assertEquals(2, getNumber("i1", TagType.IMAGE));
    }

    @Test
    public void Image_5_5Return() {
        assertEquals(5, getNumber("i2", TagType.IMAGE));
    }

    @Test
    public void UnOrderList_3_3Return() {
        assertEquals(3, getNumber("u1", TagType.UNORDEREDLIST));
    }

    @Test
    public void UnOrderedList_6_6Return() {
        assertEquals(6, getNumber("u2", TagType.UNORDEREDLIST));
    }

    @Test
    public void OrderedList_3_3Return() {
        assertEquals(3, getNumber("o1", TagType.ORDEREDLIST));
    }

    @Test
    public void OrderedList_4_4Return() {
        assertEquals(4, getNumber("o2", TagType.ORDEREDLIST));
    }

    @Test
    public void ImageAlt_2_2Return() {
        assertEquals(2, getNumber("a1", TagType.IMAGEALT));
    }

    @Test
    public void ImageAlt_5_5Return() {
        assertEquals(5, getNumber("a2", TagType.IMAGEALT));
    }

    @Test
    public void Youtube_2_2Return() {
        assertEquals(2, getNumber("v1", TagType.YOUTUBE));
    }

    @Test
    public void Youtube_4_4Return() {
        assertEquals(4, getNumber("v2", TagType.YOUTUBE));
    }

    @Test
    public void Form_2_2Return() {
        assertEquals(2, getNumber("f1", TagType.FORM));
    }

    @Test
    public void Form_5_2Return() {
        assertEquals(5, getNumber("f2", TagType.FORM));
    }

    @Test
    public void TextInput_3_3Return() {
        assertEquals(3, getNumber("fi1", TagType.TEXTINPUT));
    }

    @Test
    public void TextInput_2_2Return() {
        assertEquals(2, getNumber("fi2", TagType.TEXTINPUT));
    }

    @Test
    public void RadioButton_2_2Return() {
        assertEquals(2, getNumber("r1", TagType.RADIOBUTTON));
    }

    @Test
    public void RadioButton_5_5Return() {
        assertEquals(5, getNumber("r2", TagType.RADIOBUTTON));
    }

    @Test
    public void SubmitButton_2_2Return() {
        assertEquals(2, getNumber("s1", TagType.SUBMITBUTTON));
    }

    @Test
    public void SubmitButton_3_3Return() {
        assertEquals(3, getNumber("s2", TagType.SUBMITBUTTON));
    }

    @Test
    public void Class_2_2Return() {
        assertEquals(2, getNumber("c1", TagType.CLASS));
    }

    @Test
    public void Class_5_5Return() {
        assertEquals(5, getNumber("c2", TagType.CLASS));
    }

    @Test
    public void Id_1_1Return() {
        assertEquals(2, getNumber("id1", TagType.ID));
    }

    @Test
    public void Id_7_7Return() {
        assertEquals(7, getNumber("id2", TagType.ID));
    }

    @Test
    public void Span_1_1Return() {
        assertEquals(1, getNumber("sp1", TagType.SPAN));
    }

    @Test
    public void Span_3_3Return() {
        assertEquals(3, getNumber("sp2", TagType.SPAN));
    }

    @Test
    public void Div_2_2Return() {
        assertEquals(2, getNumber("dv1", TagType.DIV));
    }

    @Test
    public void Div_3_3Return() {
        assertEquals(3, getNumber("dv2", TagType.DIV));
    }

    /***
     * Gets number of tags in file
     * @param fileName Name of file, no directory included
     * @param tag Enum which corresponds to the tag
     * @return
     */
    private int getNumber(String fileName, TagType tag){
        Document document = getDocument(fileName);
        Elements elements = document.select(tag.searchString);
        return elements.size();
    }

    /**
     * Returns html document under the given name. Root directory 'testFiles'
     * @param testFileName
     * @return html Document
     */
    private Document getDocument(String testFileName){
        File input = new File("src/testFiles/" + testFileName + ".html");
        try {
            Document doc = Jsoup.parse(input, "UTF-8", "http://example.com/");
            return doc;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
