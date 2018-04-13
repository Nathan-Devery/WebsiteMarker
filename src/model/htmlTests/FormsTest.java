package model.htmlTests;

import jdk.nashorn.api.tree.CompilationUnitTree;
import model.TestResult;
import model.Testable;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.w3c.dom.css.CSSStyleSheet;

import java.util.ArrayList;

public class FormsTest extends Testable {

    /**
     * Checks one html page contains: form tag, input = text, button or input type = submit.
     * Full marks: All elements present.
     */
    public FormsTest() {
        super("HTML FORMS");
    }

    @Override
    public String getDescription() {
        return  "Checks one html page contains: form tag, input = text, button or input type = submit.\n" +
                "Full marks: All elements present.";
    }

    @Override
    public TestResult runTest(ArrayList<Document> documents, ArrayList<Document> xmlDocs, CSSStyleSheet sheet, String cssDocString, CompilationUnitTree tree, double percentage) {

        String report = "";
        double result = 0;

        for(Document document: documents){
            Elements forms = document.select("form");
            Elements textInputs = document.select("input[type=text]");
            Elements buttons = document.select("button");
            Elements submitButtons = document.select("input[type=submit]");

            if(!forms.isEmpty() && !textInputs.isEmpty() && (!buttons.isEmpty() || !submitButtons.isEmpty())){
                report += "\n-" + document.location() + "-\n\n";
                report += "Form tag present.\nText input present.\nButton/submit present\n";
                report += "\nForm tags:\n\n" + forms + "\n";
                report += "\nText inputs:\n\n" +textInputs + "\n";
                report += "\nButton tags:\n\n" + buttons + "\n";
                report += "\nSubmit buttons:\n\n" + submitButtons + "\n";
                result = percentage;
                break;
            }
        }
        if(result == 0) report = "Form, input and submit; are not all present in one HTML file.\n\n" + report;
        return new TestResult(toString(), result, report);
    }

}
