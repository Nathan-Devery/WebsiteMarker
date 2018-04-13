package model.htmlTests;

import jdk.nashorn.api.tree.CompilationUnitTree;
import model.TestResult;
import model.Testable;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.w3c.dom.css.CSSStyleSheet;

import javax.print.Doc;
import java.util.ArrayList;

/**
 * Checks if table tag present, tr present, th present, td present, nested inside the table tag.
 * Half marks: Table found with tr and td tags
 * Half marks: Table found with th and tr tags
 */
public class TableTest extends Testable {

    public TableTest() {
        super("TABLE TEST");
    }

    @Override
    public String getDescription() {
        return  "Checks if table tag present, tr present, th present, td present, nested inside the table tag.\n" +
                "Half marks: Table found with tr and td tags\n" +
                "Half marks: Table found with th and tr tags";
    }

    @Override
    public TestResult runTest(ArrayList<Document> documents, ArrayList<Document> xmlDocs, CSSStyleSheet sheet, String cssDocString, CompilationUnitTree tree, double percentage) {
        String report = "";
        double result = 0;

        boolean foundTd = false;
        boolean foundTh = false;
        for(Document document: xmlDocs){
            for(Element table: document.select("table")){
                report += "\n\n-" + document.location() + "-\n\n";
                report += table;
                Elements tr = table.select("tr");
                for(Element row: tr){
                    Elements th = row.select("th");
                    Elements td = row.select("td");

                    if(!th.isEmpty()){
                        foundTh = true;
                    }

                    if(!td.isEmpty()){
                        foundTd = true;
                    }
                }
            }
        }
        String reportStart = "";
        if(foundTh){
            reportStart = "<th>,<tr> tags present.\n";
            result += percentage/2;
        }else{
            reportStart += "<th> tags with <tr> not present.\n";
        }

        if(foundTd){
            reportStart += "<td>,<tr> tags present.\n";
            result += percentage/2;
        }else{
            reportStart += "<td> tags with <tr> not present.\n";
        }

        report = reportStart + "\n" + report;

        if(result > percentage) result = percentage;
        return new TestResult(toString(), result, report);
    }

}
