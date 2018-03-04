package junit.css;

import model.cssTests.SelectorType;
import model.cssTests.SelectorTest;
import org.junit.Test;

import static junit.TestUtility.getCss;
import static junit.TestUtility.getDocument;
import static org.junit.Assert.assertEquals;

/**
 * Created by Nathan on 21/02/2018.
 * Test naming convention: [UnitOfWork__StateUnderTest__ExpectedBehavior]
 */
public class SelectorTests {

    /*
    @Test
    public void classTest_1Class_true() {
        SelectorTest test = new SelectorTest("CLASS" , SelectorType.CLASS);
        assertEquals(true, test.runTest(getDocument("class1"), getCss("class1")).getResult());
    }

    @Test
    public void classTest_htmlClassValueWrong_false() {
        SelectorTest test = new SelectorTest("CLASS" , SelectorType.CLASS);
        assertEquals(false, test.runTest(getDocument("class2"), getCss("class2")).getResult());
    }

    @Test
    public void classTest_cssClassEmpty_false() {
        SelectorTest test = new SelectorTest("CLASS" , SelectorType.CLASS);
        assertEquals(false, test.runTest(getDocument("class3"), getCss("class3")).getResult());
    }

    @Test
    public void classTest_1wrongClass1workingClass_true() {
        SelectorTest test = new SelectorTest("CLASS" , SelectorType.CLASS);
        assertEquals(true, test.runTest(getDocument("class4"), getCss("class4")).getResult());
    }

    @Test
    public void classTest_multipleClasses1empty2Correct_true() {
        SelectorTest test = new SelectorTest("CLASS" , SelectorType.CLASS);
        assertEquals(true, test.runTest(getDocument("class5"), getCss("class5")).getResult());
    }

    @Test
    public void divTest_1ID_true() {
        SelectorTest test = new SelectorTest("ID" , SelectorType.ID);
        assertEquals(true, test.runTest(getDocument("IDD1"), getCss("IDD1")).getResult());
    }

    @Test
    public void divTest_wrongID_false() {
        SelectorTest test = new SelectorTest("ID" , SelectorType.ID);
        assertEquals(false, test.runTest(getDocument("IDD2"), getCss("IDD2")).getResult());
    }

    @Test
    public void divTest_NoIDinHtml_false() {
        SelectorTest test = new SelectorTest("ID" , SelectorType.ID);
        assertEquals(false, test.runTest(getDocument("IDD3"), getCss("IDD3")).getResult());
    }

    @Test
    public void divTest_correctSurroundedByDifferent_true() {
        SelectorTest test = new SelectorTest("ID" , SelectorType.ID);
        assertEquals(true, test.runTest(getDocument("IDD4"), getCss("IDD4")).getResult());
    }
    */
}
