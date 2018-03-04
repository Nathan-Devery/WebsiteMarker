package junit.css;

import model.cssTests.UniqueElement;
import model.cssTests.UniqueElementTest;
import org.junit.Test;

import static junit.TestUtility.getCss;
import static junit.TestUtility.getDocument;
import static org.junit.Assert.assertEquals;

/**
 * Created by Nathan on 21/02/2018.
 * Test naming convention: [UnitOfWork__StateUnderTest__ExpectedBehavior]
 */
public class UniqueElementTests {

    //TODO add log checking to the tests. Possibly mention whether class/id/element found?

    /*
    @Test
    public void spanTest_elementCorrect_true() {
        UniqueElementTest test = new UniqueElementTest("Span", UniqueElement.SPAN);
        assertEquals(true, test.runTest(getDocument("span1"), getCss("span1")).getResult());
    }

    @Test
    public void spanTest_classCorrect_true() {
        UniqueElementTest test = new UniqueElementTest("Span", UniqueElement.SPAN);
        assertEquals(true, test.runTest(getDocument("span2"), getCss("span2")).getResult());
    }

    @Test
    public void spanTest_classCorrectEmpty_false() {
        UniqueElementTest test = new UniqueElementTest("Span", UniqueElement.SPAN);
        assertEquals(false, test.runTest(getDocument("span3"), getCss("span3")).getResult());
    }

    @Test
    public void spanTest_divCorrect_true() {
        UniqueElementTest test = new UniqueElementTest("Span", UniqueElement.SPAN);
        assertEquals(true,  test.runTest(getDocument("span4"), getCss("span4")).getResult());
    }

    @Test
    public void spanTest_divEmpty_false() {
        UniqueElementTest test = new UniqueElementTest("Span", UniqueElement.SPAN);
        assertEquals(false, test.runTest(getDocument("span5"), getCss("span5")).getResult());
    }

    @Test
    public void spanTest_elementEmpty_false() {
        UniqueElementTest test = new UniqueElementTest("Span", UniqueElement.SPAN);
        assertEquals(false, test.runTest(getDocument("span6"), getCss("span6")).getResult());
    }


    //TODO test if the div contains something, maybe apply to rest of tests?
    @Test
    public void divTest_elementCssEmpty_false() {
        UniqueElementTest test = new UniqueElementTest("Div", UniqueElement.DIV);
        assertEquals(false, test.runTest(getDocument("div1"), getCss("div1")).getResult());
    }

    @Test
    public void divTest_elementCorrect_true() {
        UniqueElementTest test = new UniqueElementTest("Div", UniqueElement.DIV);
        assertEquals(true, test.runTest(getDocument("div2"), getCss("div2")).getResult());
    }

    @Test
    public void divTest_classCorrect_true() {
        UniqueElementTest test = new UniqueElementTest("Div", UniqueElement.DIV);
        assertEquals(true, test.runTest(getDocument("div3"), getCss("div3")).getResult());
    }

    @Test
    public void divTest_classCssEmpty_false() {
        UniqueElementTest test = new UniqueElementTest("Div", UniqueElement.DIV);
        assertEquals(false, test.runTest(getDocument("div4"), getCss("div4")).getResult());
    }

    @Test
    public void divTest_IDCssEmpty_false() {
        UniqueElementTest test = new UniqueElementTest("Div", UniqueElement.DIV);
        assertEquals(false, test.runTest(getDocument("div5"), getCss("div5")).getResult());
    }

    @Test
    public void divTest_IDCssCorrect_true() {
        UniqueElementTest test = new UniqueElementTest("Div", UniqueElement.DIV);
        assertEquals(true, test.runTest(getDocument("div6"), getCss("div6")).getResult());
    }

    @Test
    public void divCombination_divCorrect_true() {
        UniqueElementTest test = new UniqueElementTest("Div", UniqueElement.DIV);
        assertEquals(true, test.runTest(getDocument("spanDiv"), getCss("spanDiv")).getResult());
    }

    @Test
    public void divCombination_classCorrect_true() {
        UniqueElementTest test = new UniqueElementTest("Div", UniqueElement.SPAN);
        assertEquals(true, test.runTest(getDocument("spanDiv"), getCss("spanDiv")).getResult());
    }
    */

}
