package junit.javascript;

import jdk.nashorn.api.tree.CompilationUnitTree;
import jdk.nashorn.api.tree.Parser;
import model.htmlTests.TableTest;
import model.javascript.JavascriptTest;
import org.jsoup.nodes.Document;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;

import static junit.TestUtility.getDocument;
import static junit.TestUtility.getXMLDocument;
import static org.junit.Assert.assertEquals;

/***
 * Test naming convention: [UnitOfWork__StateUnderTest__ExpectedBehavior]
 */
public class JavascriptTests {

    @Test
    public void javascript_All_returnFull(){
        JavascriptTest test = new JavascriptTest();
        CompilationUnitTree tree = loadJs("javascript1");
        ArrayList<Document> documents = getDocument("javascriptLinked");

        assertEquals(1, test.runTest(documents, null, null, null, tree, 1).getResult(),0);
    }

    @Test
    public void javascript_AllMultiFunctionNoLink_returnFull(){
        JavascriptTest test = new JavascriptTest();
        CompilationUnitTree tree = loadJs("javascript5");
        ArrayList<Document> documents = getDocument("javascriptLinked");

        assertEquals(0.5, test.runTest(documents, null, null, null, tree, 1).getResult(),0);
    }

    @Test
    public void javascript_AllNoLinkNoIf_return0(){
        JavascriptTest test = new JavascriptTest();
        CompilationUnitTree tree = loadJs("javascript2");
        ArrayList<Document> documents = getDocument("structure1");

        assertEquals(0, test.runTest(documents, null, null, null, tree, 1).getResult(),0);
    }

    @Test
    public void javascript_AllNoLinkNoVar_return0(){
        JavascriptTest test = new JavascriptTest();
        CompilationUnitTree tree = loadJs("javascript3");
        ArrayList<Document> documents = getDocument("structure1");

        assertEquals(0, test.runTest(documents, null, null, null, tree, 1).getResult(),0);
    }

    @Test
    public void javascript_AllNoLinkNoOperator_return0(){
        JavascriptTest test = new JavascriptTest();
        CompilationUnitTree tree = loadJs("javascript4");
        ArrayList<Document> documents = getDocument("structure1");

        assertEquals(0, test.runTest(documents, null, null, null, tree, 1).getResult(),0);
    }

    @Test
    public void javascript_EmptyJavascript_return0(){
        JavascriptTest test = new JavascriptTest();
        CompilationUnitTree tree = loadJs("javascript6");
        ArrayList<Document> documents = getDocument("structure1");

        assertEquals(0, test.runTest(documents, null, null, null, tree, 10).getResult(),0);
    }

    @Test
    public void javascript_emptyMethodNoJavascriptRequirements_return0(){
        JavascriptTest test = new JavascriptTest();
        CompilationUnitTree tree = loadJs("javascript7");
        ArrayList<Document> documents = getDocument("structure1");

        assertEquals(0, test.runTest(documents, null, null, null, tree, 10).getResult(),0);
    }

    private CompilationUnitTree loadJs(String fileName){
        CompilationUnitTree tree = null;
        try {
            File file = new File("src/conditionTestFiles/" + fileName + ".js");
            Parser parser = Parser.create();
            tree = parser.parse(file, null);
        }catch(Exception e){
            e.printStackTrace();
        }
        return tree;
    }

}
