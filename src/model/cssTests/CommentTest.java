package model.cssTests;

import jdk.nashorn.api.tree.CompilationUnitTree;
import model.TestResult;
import model.Testable;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Node;
import org.w3c.dom.css.CSSStyleSheet;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Checks whether comments found in all html and css files.
 * Half mark: At least one html and css present
 * Half mark: All html and css pages have html and css comments (if css.count > 1 and html.count > 1)
 */
public class CommentTest extends Testable {

    String report = "";

    public CommentTest() {
        super("COMMENT TEST");
    }

    @Override
    public String getDescription() {
        return  "Checks whether comments found in all html and css files.\n" +
                "Half marks: At least one html and css present\n" +
                "Half marks: All html and css pages have html and css comments (if css.count > 1 and html.count > 1)";
    }

    @Override
    public TestResult runTest(ArrayList<Document> documents, ArrayList<Document> xmlDocuments, CSSStyleSheet sheet, String cssDocString, CompilationUnitTree tree, double percentage) {
        int htmlPagesWithComment = getHtmlPagesWithComment(documents);
        boolean containsCssComment = checkCssComment(cssDocString);

        double result = 0;
        if (htmlPagesWithComment >= 1 && containsCssComment) {
            result += percentage / 2;
        }

        if (htmlPagesWithComment >= documents.size() && containsCssComment) {
            result += percentage / 2;
            report = "Commenting is present in all HTML files and CSS file.\n\n" + report;
        }else{
            report = "Commenting is not present in all HTML files and CSS file.\n\n" + report;
        }

        TestResult testResult = new TestResult(toString(), result, report);
        reset();
        return testResult;
    }

    private int getHtmlPagesWithComment(ArrayList<Document> documents) {
        String internalReport = "";

        int comments = 0;
        for (Document document : documents) {
            String documentComments = getComments(document);
            internalReport += "\n-" + document.location() + "-\n";
            if (!documentComments.equals("")) {
                internalReport += documentComments + "\n";
                comments++;
            }
        }

        if (comments >= 1) {
            report += "Html comments(s):\n\n" + internalReport;
        } else {
            report += "\nNo html comment(s) found:\n\n";
        }
        return comments;
    }

    private String getComments(Node node) {
        String internalReport = "";
        if (node.nodeName().equals("#comment")) {
            internalReport += node;
        }

        if (node.childNodeSize() == 0) return internalReport;

        for (Node child : node.childNodes()) {
            internalReport += getComments(child);
        }
        return internalReport;
    }

    private boolean checkCssComment(String cssDocString) {
        String internalReport = "";
        int comments = 0;

        Pattern commentPattern = Pattern.compile("\\/\\*[^*]*\\*+([^/*][^*]*\\*+)*\\/\n");
        Matcher m = commentPattern.matcher(cssDocString);

        internalReport += "\n-CssFile-\n\n";

        while (m.find()) {
            internalReport += m.group();
            comments++;
        }
        if (comments >= 1) {
            report += "Css comment(s) found:\n\n" + internalReport;
            return true;
        }

        report += "\nNo css comment(s) found:\n\n";
        return false;
    }

    private void reset() {
        report = "";
    }

}
