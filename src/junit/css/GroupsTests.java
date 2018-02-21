package junit.css;

import com.steadystate.css.parser.CSSOMParser;
import model.cssTests.GroupType;
import model.cssTests.GroupsTest;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;
import org.w3c.css.sac.InputSource;
import org.w3c.dom.css.CSSStyleSheet;

import java.io.*;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * Created by Nathan on 21/02/2018.
 * Test naming convention: [UnitOfWork__StateUnderTest__ExpectedBehavior]
 */
public class GroupsTests {

    @Test
    public void classTest_1Class_true() {
        GroupsTest test = new GroupsTest("CLASS" ,GroupType.CLASS);
        test.runTest(getDocument("class1"), getCss("class1"));
        assertEquals(true, test.getResult());
    }

    @Test
    public void classTest_htmlClassValueWrong_false() {
        GroupsTest test = new GroupsTest("CLASS" ,GroupType.CLASS);
        test.runTest(getDocument("class2"), getCss("class2"));
        assertEquals(false, test.getResult());
    }

    @Test
    public void classTest_cssClassEmpty_false() {
        GroupsTest test = new GroupsTest("CLASS" ,GroupType.CLASS);
        test.runTest(getDocument("class3"), getCss("class3"));
        assertEquals(false, test.getResult());
    }

    @Test
    public void classTest_1wrongClass1workingClass_true() {
        GroupsTest test = new GroupsTest("CLASS" ,GroupType.CLASS);
        test.runTest(getDocument("class4"), getCss("class4"));
        assertEquals(true, test.getResult());
    }

    @Test
    public void classTest_multipleClasses1empty2Correct_true() {
        GroupsTest test = new GroupsTest("CLASS" ,GroupType.CLASS);
        test.runTest(getDocument("class5"), getCss("class5"));
        assertEquals(true, test.getResult());
    }

    @Test
    public void divTest_1ID_true() {
        GroupsTest test = new GroupsTest("ID" ,GroupType.ID);
        test.runTest(getDocument("IDD1"), getCss("IDD1"));
        assertEquals(true, test.getResult());
    }

    @Test
    public void divTest_wrongID_false() {
        GroupsTest test = new GroupsTest("ID" ,GroupType.ID);
        test.runTest(getDocument("IDD2"), getCss("IDD2"));
        assertEquals(false, test.getResult());
    }

    @Test
    public void divTest_NoIDinHtml_false() {
        GroupsTest test = new GroupsTest("ID" ,GroupType.ID);
        test.runTest(getDocument("IDD3"), getCss("IDD3"));
        assertEquals(false, test.getResult());
    }

    @Test
    public void divTest_correctSurroundedByDifferent_true() {
        GroupsTest test = new GroupsTest("ID" ,GroupType.ID);
        test.runTest(getDocument("IDD4"), getCss("IDD4"));
        assertEquals(true, test.getResult());
    }

    private ArrayList<Document> getDocument(String fileName){   //Duplicated method, create a utility class?
        File file = new File("src/testFiles/" + fileName + ".html");
        try {
            String currentDirectory = file.getAbsolutePath().substring(0, file.getAbsolutePath().length() - file.getName().length());
            Document doc = Jsoup.parse(file, "UTF-8", currentDirectory);
            ArrayList toReturn = new ArrayList();
            toReturn.add(doc);
            return toReturn;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    private CSSStyleSheet getCss(String fileName){
        File file = new File("src/testFiles/" + fileName + ".css");
        try {
            InputStream stream = new FileInputStream(file);
            InputSource source = new InputSource(new InputStreamReader(stream));
            CSSOMParser parser = new CSSOMParser();
            CSSStyleSheet stylesheet = parser.parseStyleSheet(source, null, null);  //These can be stored
            return stylesheet;
        }catch(IOException e){
            e.printStackTrace();
        }
        return null;
    }
}
