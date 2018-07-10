package junit.css;

import junit.TestUtility;
import model.cssTests.*;
import org.jsoup.nodes.Document;
import org.junit.Test;
import org.w3c.dom.css.CSSStyleSheet;

import java.io.File;
import java.util.ArrayList;

import static junit.TestUtility.getCss;
import static junit.TestUtility.getCssString;
import static junit.TestUtility.getDocument;
import static org.junit.Assert.assertEquals;


public class cssHtmlTests {
    //'All' keyword refers to all conditions being met.

    @Test
    public void commentTest_AllHtmlCommentsCssComments_returnFull(){
        CommentTest test = new CommentTest();
        ArrayList<Document> documents = getDocument("commentH1");
        documents.add(getDocument("commentH2").get(0));
        String cssString = getCssString("commentC1");

        assertEquals(1, test.runTest(documents, null, null, cssString, null, 1).getResult(),0);
    }

    @Test
    public void commentTest_1HtmlPageCommentsCssComments_returnFull(){
        CommentTest test = new CommentTest();
        ArrayList<Document> documents = getDocument("commentH1");
        String cssString = getCssString("commentC1");

        assertEquals(1, test.runTest(documents, null, null, cssString, null, 1).getResult(),0);
    }

    @Test
    public void commentTest_1HtmlComment1NotCssComments_returnHalf(){
        CommentTest test = new CommentTest();
        ArrayList<Document> documents = getDocument("commentH1");
        documents.add(getDocument("commentH3").get(0));
        String cssString = getCssString("commentC1");

        assertEquals(0.5, test.runTest(documents, null, null, cssString, null, 1).getResult(),0);
    }

    @Test
    public void commentTest_0HtmlCommentCssComments_return0(){
        CommentTest test = new CommentTest();
        ArrayList<Document> documents = getDocument("commentH3");
        String cssString = getCssString("commentC1");

        assertEquals(0, test.runTest(documents, null, null, cssString, null, 10).getResult(),0);
    }

    @Test
    public void commentTest_AllHtmlCommentsCssNoComments_return0(){
        CommentTest test = new CommentTest();
        ArrayList<Document> documents = getDocument("commentH1");
        documents.add(getDocument("commentH2").get(0));
        String cssString = getCssString("commentC2");

        assertEquals(0, test.runTest(documents, null, null, cssString, null, 3).getResult(),0);
    }

    @Test
    public void commentTest_NoHtmlCommentsCssNoComments_return0(){
        CommentTest test = new CommentTest();
        ArrayList<Document> documents = getDocument("commentH3");
        documents.add(getDocument("commentH3").get(0));
        String cssString = getCssString("commentC2");

        assertEquals(0, test.runTest(documents, null, null, cssString, null, 3).getResult(),0);
    }

    @Test
    public void TextFormattingTest_All_returnFull(){
        TextFormattingTest test = new TextFormattingTest();
        ArrayList<Document> documents = getDocument("textFormatting1");
        CSSStyleSheet sheet = getCss("textFormatting1");

        assertEquals(3, test.runTest(documents, null, sheet, null, null, 3).getResult(),0);
    }

    @Test
    public void TextFormattingTest_AllNoFamily_return0(){
        TextFormattingTest test = new TextFormattingTest();
        ArrayList<Document> documents = getDocument("textFormatting1");
        CSSStyleSheet sheet = getCss("textFormatting2");

        assertEquals(0, test.runTest(documents, null, sheet, null, null, 3).getResult(),0);
    }

    @Test
    public void TextFormattingTest_AllNoSize_return0(){
        TextFormattingTest test = new TextFormattingTest();
        ArrayList<Document> documents = getDocument("textFormatting1");
        CSSStyleSheet sheet = getCss("textFormatting3");

        assertEquals(0, test.runTest(documents, null, sheet, null, null, 3).getResult(),0);
    }

    @Test
    public void TextFormattingTest_AllNoColor_return0(){
        TextFormattingTest test = new TextFormattingTest();
        ArrayList<Document> documents = getDocument("textFormatting1");
        CSSStyleSheet sheet = getCss("textFormatting5");

        assertEquals(0, test.runTest(documents, null, sheet, null, null, 3).getResult(),0);
    }

    @Test
    public void TextFormattingTest_AllNoFamilyNoElement_return0(){
        TextFormattingTest test = new TextFormattingTest();
        ArrayList<Document> documents = getDocument("textFormatting1");
        CSSStyleSheet sheet = getCss("textFormatting6");

        assertEquals(0, test.runTest(documents, null, sheet, null, null, 3).getResult(),0);
    }

    @Test
    public void TextFormattingTest_MultipleAll_return0(){
        TextFormattingTest test = new TextFormattingTest();
        ArrayList<Document> documents = getDocument("textFormatting1");
        documents.add(getDocument("textFormatting2").get(0));
        CSSStyleSheet sheet = getCss("textFormatting7");

        assertEquals(0.5, test.runTest(documents, null, sheet, null, null, 0.5).getResult(),0);
    }

    @Test
    public void SelectorType_AllClass_returnFull(){
        SelectorTest test = new SelectorTest(SelectorType.CLASS);
        ArrayList<Document> documents = getDocument("class1");
        //documents.add(getDocument("textFormatting2").get(0));
        CSSStyleSheet sheet = getCss("class1");

        assertEquals(1, test.runTest(documents, null, sheet, null, null, 1).getResult(),0);
    }

    @Test
    public void SelectorType_AllClassWrongClass_return0(){
        SelectorTest test = new SelectorTest(SelectorType.CLASS);
        ArrayList<Document> documents = getDocument("class1");
        //documents.add(getDocument("textFormatting2").get(0));
        CSSStyleSheet sheet = getCss("class2");

        assertEquals(0, test.runTest(documents, null, sheet, null, null, 1).getResult(),0);
    }

    @Test
    public void SelectorType_AllHtmlNoClass_return0(){
        SelectorTest test = new SelectorTest(SelectorType.CLASS);
        ArrayList<Document> documents = getDocument("class2");
        //documents.add(getDocument("textFormatting2").get(0));
        CSSStyleSheet sheet = getCss("class1");

        assertEquals(0, test.runTest(documents, null, sheet, null, null, 1).getResult(),0);
    }

    @Test
    public void SelectorType_HtmlWrongCssWrongIdsUsed_return0(){
        SelectorTest test = new SelectorTest(SelectorType.CLASS);
        ArrayList<Document> documents = getDocument("class3");
        //documents.add(getDocument("textFormatting2").get(0));
        CSSStyleSheet sheet = getCss("class3");

        assertEquals(0, test.runTest(documents, null, sheet, null, null, 1).getResult(),0);
    }

    @Test
    public void SelectorType_AllId_returnFull(){
        SelectorTest test = new SelectorTest(SelectorType.ID);
        ArrayList<Document> documents = getDocument("id1");
        //documents.add(getDocument("textFormatting2").get(0));
        CSSStyleSheet sheet = getCss("id1");

        assertEquals(1, test.runTest(documents, null, sheet, null, null, 1).getResult(),0);
    }

    @Test
    public void SelectorType_AllClassWrongClassCss_return0(){
        SelectorTest test = new SelectorTest(SelectorType.ID);
        ArrayList<Document> documents = getDocument("id1");
        //documents.add(getDocument("textFormatting2").get(0));
        CSSStyleSheet sheet = getCss("id2");

        assertEquals(0, test.runTest(documents, null, sheet, null, null, 1).getResult(),0);
    }

    @Test
    public void SelectorType_AllWrongId_return0(){
        SelectorTest test = new SelectorTest(SelectorType.ID);
        ArrayList<Document> documents = getDocument("id2");
        //documents.add(getDocument("textFormatting2").get(0));
        CSSStyleSheet sheet = getCss("id1");

        assertEquals(0, test.runTest(documents, null, sheet, null, null, 1).getResult(),0);
    }

    @Test
    public void UniqueElementTest_AllSpanId_returnFull(){
        UniqueElementTest test = new UniqueElementTest(UniqueElement.SPAN);
        ArrayList<Document> documents = getDocument("uniqueElement1");
        //documents.add(getDocument("textFormatting2").get(0));
        CSSStyleSheet sheet = getCss("uniqueElement1");

        assertEquals(1, test.runTest(documents, null, sheet, null, null, 1).getResult(),0);
    }

    @Test
    public void UniqueElementTest_AllDivClass_returnFull(){
        UniqueElementTest test = new UniqueElementTest(UniqueElement.DIV);
        ArrayList<Document> documents = getDocument("uniqueElement2");
        //documents.add(getDocument("textFormatting2").get(0));
        CSSStyleSheet sheet = getCss("uniqueElement2");

        assertEquals(0.5, test.runTest(documents, null, sheet, null, null, 0.5).getResult(),0);
    }

    @Test
    public void UniqueElementTest_AllSpanTag_returnFull(){
        UniqueElementTest test = new UniqueElementTest(UniqueElement.SPAN);
        ArrayList<Document> documents = getDocument("uniqueElement3");
        documents.add(getDocument("uniqueElement1").get(0));
        CSSStyleSheet sheet = getCss("uniqueElement3");

        assertEquals(0.5, test.runTest(documents, null, sheet, null, null, 0.5).getResult(),0);
    }

    @Test
    public void UniqueElementTest_AllSpanEmptyCss_return0(){
        UniqueElementTest test = new UniqueElementTest(UniqueElement.SPAN);
        ArrayList<Document> documents = getDocument("uniqueElement3");
        documents.add(getDocument("uniqueElement1").get(0));
        CSSStyleSheet sheet = getCss("uniqueElement4");

        assertEquals(0, test.runTest(documents, null, sheet, null, null, 20).getResult(),0);
    }

    @Test
    public void UniqueElementTest_AllSpanWrongIDCss_return0(){
        UniqueElementTest test = new UniqueElementTest(UniqueElement.SPAN);
        ArrayList<Document> documents = getDocument("uniqueElement1");
        //documents.add(getDocument("uniqueElement1").get(0));
        CSSStyleSheet sheet = getCss("uniqueElement5");

        assertEquals(0, test.runTest(documents, null, sheet, null, null, 20).getResult(),0);
    }

    @Test
    public void UniqueElementTest_AllDivWrongClassHtml_return0(){
        UniqueElementTest test = new UniqueElementTest(UniqueElement.DIV);
        ArrayList<Document> documents = getDocument("uniqueElement6");
        //documents.add(getDocument("uniqueElement1").get(0));
        CSSStyleSheet sheet = getCss("uniqueElement6");

        assertEquals(0, test.runTest(documents, null, sheet, null, null, 20).getResult(),0);
    }

    @Test
    public void UniqueElementTest_AllSpanWrongTagCss_return0(){
        UniqueElementTest test = new UniqueElementTest(UniqueElement.SPAN);
        ArrayList<Document> documents = getDocument("uniqueElement3");
        //documents.add(getDocument("uniqueElement1").get(0));
        CSSStyleSheet sheet = getCss("uniqueElement7");

        assertEquals(0, test.runTest(documents, null, sheet, null, null, 20).getResult(),0);
    }

    @Test
    public void UniqueElementTest_SpanWithStyle_return1(){
        UniqueElementTest test = new UniqueElementTest(UniqueElement.SPAN);
        ArrayList<Document> documents = getDocument("uniqueElement8");
        //documents.add(getDocument("uniqueElement1").get(0));
        CSSStyleSheet sheet = getCss("uniqueElement8");
        test.runTest(documents, null, sheet, null, null, 1).getResult();
        assertEquals(1, test.runTest(documents, null, sheet, null, null, 1).getResult(),0);
    }

}
