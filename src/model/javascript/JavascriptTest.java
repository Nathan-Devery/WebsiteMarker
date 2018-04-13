package model.javascript;

import jdk.nashorn.api.tree.*;
import model.TestResult;
import model.Testable;
import org.jsoup.nodes.Document;
import org.w3c.dom.css.CSSStyleSheet;

import javax.print.Doc;
import java.util.ArrayList;

/**
 * Checks whether a If, Variable and operator are present in the javascript.
 * Checks whether at least one html file contains a link to the javascript file.
 * Half marks: If, Variable, Operator are all present.
 * Half marks: At least one html file links to the javascript
 */
public class JavascriptTest extends Testable {

    int ifCount = 0;
    int operatorCount = 0;
    int variableCount = 0;

    public JavascriptTest() {
        super("JAVASCRIPT(IF VARIABLE OPERATOR)");
    }

    @Override
    public String getDescription() {
        return  "Checks whether a If, Variable and operator are present in the javascript.\n" +
                "Checks whether at least one html file contains a link to the javascript file.\n" +
                "Half marks: If, Variable, Operator are all present.\n" +
                "Half marks: At least one html file links to the javascript";
    }

    @Override
    public TestResult runTest(ArrayList<Document> documents, ArrayList<Document> xmlDocs, CSSStyleSheet sheet, String cssDocString, CompilationUnitTree tree, double percentage) {
        if(tree == null) return new TestResult(toString(), 0, "Javascript cannot compile or does not exist.\n");

        String report = "-" + tree.getSourceName() + "-\n\n";

        if(testIf(tree)){
            report += "If statement present.\n";
        }else{
            report += "If statement not present.\n";
        }

        if(testOperator(tree)){
            report += "Operator present.\n";
        }else{
            report += "Operator not present.\n";
        }

        if(testVariable(tree)){
            report += "Variable present.\n";
        }else{
            report += "Variable not present.\n";
        }

        double result = 0;

        if(ifCount > 0 && operatorCount > 0 && variableCount > 0) result += percentage/2;

        if(javascriptLinked(documents, tree)){
            report += "Javascript file linked to at least one html file.\n";
            result += percentage/2;
        }

        report += "\n";

        TestResult testResult = new TestResult(toString(), result, report);
        reset();
        return testResult;
    }

    private boolean testIf(CompilationUnitTree tree){
        tree.accept(new SimpleTreeVisitorES6<Void, Void>() {
            @Override
            public Void visitIf(IfTree node, Void r) {
                ifCount ++;
                return null;
            }
        }, null);

        if(ifCount >= 1) {
            return true;
        }
        return false;
    }

    private boolean testOperator(CompilationUnitTree tree) {
        tree.accept(new SimpleTreeVisitorES6<Void, Void>() {
            @Override
            public Void visitBinary(BinaryTree node, Void r) {
                operatorCount++;
                return null;
            }
        }, null);

        if (operatorCount >= 1) {
            return true;
        }
        return false;
    }

    private boolean testVariable(CompilationUnitTree tree) {
        tree.accept(new SimpleTreeVisitorES6<Void, Void>() {
            @Override
            public Void visitVariable(VariableTree node, Void r) {
                variableCount++;
                return null;
            }
        }, null);

        if(variableCount >= 1){
            return true;
        }
        return false;
    }

    private boolean javascriptLinked(ArrayList<Document> documents, CompilationUnitTree tree){
        for(Document document: documents){
            if(!document.select("script[src=" + tree.getSourceName() + "]").isEmpty()) return true;
        }
        return false;
    }

    private void reset(){
        ifCount = 0;
        operatorCount = 0;
        variableCount = 0;
    }

}
