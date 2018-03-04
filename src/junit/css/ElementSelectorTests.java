package junit.css;

import model.cssTests.ElementSelectorTest;
import model.cssTests.SelectorTest;
import model.cssTests.SelectorType;
import org.junit.Test;

import static junit.TestUtility.getCss;
import static junit.TestUtility.getDocument;
import static org.junit.Assert.assertEquals;

/**
 * Created by Nathan on 21/02/2018.
 * Test naming convention: [UnitOfWork__StateUnderTest__ExpectedBehavior]
 */
public class ElementSelectorTests {

    /*
    @Test
    public void elementTest_1ElementCorrect_true() {
        ElementSelectorTest test = new ElementSelectorTest("ElementSelector");
        assertEquals(true, test.runTest(getDocument("element1"), getCss("element1")).getResult());
    }

    @Test
    public void elementTest_1ElementCssEmpty_false() {
        ElementSelectorTest test = new ElementSelectorTest("ElementSelector");
        assertEquals(false, test.runTest(getDocument("element2"), getCss("element2")).getResult());
    }

    @Test
    public void elementTest_h3CssUnclosedParagraphNoCss_true() {
        ElementSelectorTest test = new ElementSelectorTest("ElementSelector");
        assertEquals(true, test.runTest(getDocument("element4"), getCss("element4")).getResult());
    }

    @Test
    public void elementTest_h3CssNoClosingBracketsRedundantCssRule_true() {
        ElementSelectorTest test = new ElementSelectorTest("ElementSelector");
        assertEquals(true, test.runTest(getDocument("element5"), getCss("element5")).getResult());
    }

    @Test
    public void elementTest_h3NoCssNoClosingBracketsRedundantCssRule_false() {
        ElementSelectorTest test = new ElementSelectorTest("ElementSelector");
        assertEquals(false, test.runTest(getDocument("element6"), getCss("element6")).getResult());
    }
    */

}
