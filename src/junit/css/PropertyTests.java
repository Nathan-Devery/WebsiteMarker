package junit.css;

import model.cssTests.Property;
import model.cssTests.PropertyTester;
import org.junit.Test;

import static junit.TestUtility.getCss;
import static junit.TestUtility.getDocument;
import static org.junit.Assert.assertEquals;

/**
 * Created by Nathan on 21/02/2018.
 * Test naming convention: [UnitOfWork__StateUnderTest__ExpectedBehavior]
 */
public class PropertyTests {

    /*
    @Test
    public void elementTest_1ElementFontFamilyCorrect_true() {
        PropertyTester test = new PropertyTester("PropertyTests", Property.FONTFAMILY);
        assertEquals(true, test.runTest(getDocument("property1"), getCss("property1")).getResult());
    }

    @Test
    public void elementTest_1ElementFontFamilyCorrectClassUsed_true() {
        PropertyTester test = new PropertyTester("PropertyTests", Property.FONTFAMILY);
        assertEquals(true, test.runTest(getDocument("property2"), getCss("property2")).getResult());
    }

    @Test
    public void elementTest_1ElementFontSizeCorrectIDUsed_true() {
        PropertyTester test = new PropertyTester("PropertyTests", Property.FONTSIZE);
        assertEquals(true, test.runTest(getDocument("property3"), getCss("property3")).getResult());
    }

    @Test
    public void elementTest_1ElementFontSizeCssEmptyIDUsed_false() {
        PropertyTester test = new PropertyTester("PropertyTests", Property.FONTSIZE);
        assertEquals(false, test.runTest(getDocument("property4"), getCss("property4")).getResult());
    }

    @Test
    public void elementTest_1ElementFontSizeHtmlEmptyIDUsed_false() {
        PropertyTester test = new PropertyTester("PropertyTests", Property.FONTSIZE);
        assertEquals(false, test.runTest(getDocument("property5"), getCss("property5")).getResult());
    }

    @Test
    public void elementTest_1ElementColorHtmlCorrect_true() {
        PropertyTester test = new PropertyTester("PropertyTests", Property.COLOR);
        assertEquals(true, test.runTest(getDocument("property6"), getCss("property6")).getResult());
    }

    @Test
    public void elementTest_1ElementColorHtmlWrongClassUsed_false() {
        PropertyTester test = new PropertyTester("PropertyTests", Property.COLOR);
        assertEquals(false, test.runTest(getDocument("property7"), getCss("property7")).getResult());
    }

    @Test
    public void elementTest_5ElementCombinationCorrect_true() {
        PropertyTester test = new PropertyTester("PropertyTests", Property.COLOR);
        assertEquals(true, test.runTest(getDocument("property8"), getCss("property8")).getResult());
    }
    */


    /***
     * The invalid values must be prefixed with a hash, extremely invalid are let through; however, they are unlikely.
     */

    /*
    @Test
    public void elementTest_1ElementColorIncorrectValue_false() {
        PropertyTester test = new PropertyTester("PropertyTests", Property.COLOR);
        assertEquals(false, test.runTest(getDocument("property9"), getCss("property9")).getResult());
    }
    */

}
