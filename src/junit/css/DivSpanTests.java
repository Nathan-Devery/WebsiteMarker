package junit.css;

import com.steadystate.css.parser.CSSOMParser;
import model.cssTests.DivSpan;
import model.cssTests.DivSpanTest;
import model.cssTests.GroupType;
import model.cssTests.GroupsTest;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;
import org.w3c.css.sac.InputSource;
import org.w3c.dom.css.CSSStyleSheet;

import java.io.*;
import java.util.ArrayList;

import static junit.TestUtility.getCss;
import static junit.TestUtility.getDocument;
import static org.junit.Assert.assertEquals;

/**
 * Created by Nathan on 21/02/2018.
 * Test naming convention: [UnitOfWork__StateUnderTest__ExpectedBehavior]
 */
public class DivSpanTests {

    //TODO add log checking to the tests. Possibly mention whether class/id/element found?

    @Test
    public void spanTest_elementCorrect_true() {
        DivSpanTest test = new DivSpanTest("Span", DivSpan.SPAN);
        test.runTest(getDocument("span1"), getCss("span1"));
        assertEquals(true, test.getResult());
    }

    @Test
    public void spanTest_classCorrect_true() {
        DivSpanTest test = new DivSpanTest("Span", DivSpan.SPAN);
        test.runTest(getDocument("span2"), getCss("span2"));
        assertEquals(true, test.getResult());
    }

    @Test
    public void spanTest_classCorrectEmpty_false() {
        DivSpanTest test = new DivSpanTest("Span", DivSpan.SPAN);
        test.runTest(getDocument("span3"), getCss("span3"));
        assertEquals(false, test.getResult());
    }

    @Test
    public void spanTest_divCorrect_true() {
        DivSpanTest test = new DivSpanTest("Span", DivSpan.SPAN);
        test.runTest(getDocument("span4"), getCss("span4"));
        assertEquals(true, test.getResult());
    }

    @Test
    public void spanTest_divEmpty_false() {
        DivSpanTest test = new DivSpanTest("Span", DivSpan.SPAN);
        test.runTest(getDocument("span5"), getCss("span5"));
        assertEquals(false, test.getResult());
    }

    @Test
    public void spanTest_elementEmpty_false() {
        DivSpanTest test = new DivSpanTest("Span", DivSpan.SPAN);
        test.runTest(getDocument("span6"), getCss("span6"));
        assertEquals(false, test.getResult());
    }


    //TODO test if the div contains something, maybe apply to rest of tests?
    @Test
    public void divTest_elementCssEmpty_false() {
        DivSpanTest test = new DivSpanTest("Div", DivSpan.DIV);
        test.runTest(getDocument("div1"), getCss("div1"));
        assertEquals(false, test.getResult());
    }

    @Test
    public void divTest_elementCorrect_true() {
        DivSpanTest test = new DivSpanTest("Div", DivSpan.DIV);
        test.runTest(getDocument("div2"), getCss("div2"));
        assertEquals(true, test.getResult());
    }

    @Test
    public void divTest_classCorrect_true() {
        DivSpanTest test = new DivSpanTest("Div", DivSpan.DIV);
        test.runTest(getDocument("div3"), getCss("div3"));
        assertEquals(true, test.getResult());
    }

    @Test
    public void divTest_classCssEmpty_false() {
        DivSpanTest test = new DivSpanTest("Div", DivSpan.DIV);
        test.runTest(getDocument("div4"), getCss("div4"));
        assertEquals(false, test.getResult());
    }

    @Test
    public void divTest_IDCssEmpty_false() {
        DivSpanTest test = new DivSpanTest("Div", DivSpan.DIV);
        test.runTest(getDocument("div5"), getCss("div5"));
        assertEquals(false, test.getResult());
    }

    @Test
    public void divTest_IDCssCorrect_true() {
        DivSpanTest test = new DivSpanTest("Div", DivSpan.DIV);
        test.runTest(getDocument("div6"), getCss("div6"));
        assertEquals(true, test.getResult());
    }

    @Test
    public void divCombination_divCorrect_true() {
        DivSpanTest test = new DivSpanTest("Div", DivSpan.DIV);
        test.runTest(getDocument("spanDiv"), getCss("spanDiv"));
        assertEquals(true, test.getResult());
    }

    @Test
    public void divCombination_classCorrect_true() {
        DivSpanTest test = new DivSpanTest("Div", DivSpan.SPAN);
        test.runTest(getDocument("spanDiv"), getCss("spanDiv"));
        assertEquals(true, test.getResult());
    }

}
