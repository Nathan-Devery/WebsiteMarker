package model.javascript;

import jdk.nashorn.api.tree.*;
import model.TestResult;
import model.Testable;
import org.jsoup.nodes.Document;
import org.w3c.dom.css.CSSStyleSheet;

import java.util.ArrayList;

/***
 * Checks if operator used
 * Full marks: operator present.
 */
public class OperatorTest extends Testable {

    int evidenceCount = 0;

    public OperatorTest() {
        super("OPERATOR");
    }

    @Override
    public String getDescription() {
        return  "Checks if operator used\n" +
                "Full marks: operator present.";
    }

    @Override
    public TestResult runTest(ArrayList<Document> documents, ArrayList<Document> xmlDocs, CSSStyleSheet sheet, String cssDocString, CompilationUnitTree tree, double percentage) {

        //TODO utility method to check link

        tree.accept(new SimpleTreeVisitorES6<Void, Void>() {
            @Override
            public Void visitBinary(BinaryTree node, Void r) {
                evidenceCount++;
                return null;
            }
        }, null);

        double result;
        if(evidenceCount >= 1){
            result = percentage;
        }else{
            result = 0;
        }

        evidenceCount = 0;  //TODO resets for next text, think of better fix.
        return new TestResult(toString(), result, "");  //TODO finish evidence log

    }
}
