package model;

import jdk.nashorn.api.tree.CompilationUnitTree;
import org.jsoup.nodes.Document;
import org.w3c.dom.css.CSSStyleSheet;

import javax.print.Doc;
import java.util.ArrayList;

/**
 * Data structure that represents a assignment.
 */
public class Assignment {

    private String nameID;
    private ArrayList<TestResult> results = new ArrayList<>();
    private ArrayList<Document> htmlDocs;
    private ArrayList<Document> xmlDocs;
    private CSSStyleSheet cssDocs;
    private String cssDocString;
    private CompilationUnitTree javaScriptDocs;

    /***
     *
     * @param nameID
     * @param htmlDocs  html files
     * @param xmldocs   html files parsed as xml (stopped auto completion)
     * @param cssDocs
     * @param cssDocString  A css string representation of css. Stops removal of comments (for searching)
     * @param javaScriptDocs
     */
    public Assignment(String nameID, ArrayList<Document> htmlDocs, ArrayList<Document> xmldocs, CSSStyleSheet cssDocs, String cssDocString,CompilationUnitTree javaScriptDocs) {
        this.nameID = nameID;
        this.htmlDocs = htmlDocs;
        this.xmlDocs = xmldocs;
        this.cssDocs = cssDocs;
        this.cssDocString = cssDocString;
        this.javaScriptDocs = javaScriptDocs;
    }

    public void addResults(TestResult result) {
        this.results.add(result);
    }

    public String getNameID() {
        return nameID;
    }

    public ArrayList<TestResult> getResults() {
        return results;
    }

    /***
     * Gets the total grade percentage.
     * @return
     */
    public double getTotalPercentage(){
        double total = 0;
        for(TestResult result: results){
            total += result.getResult();
        }
        return total;
    }

    public ArrayList<Document> getHtmlDocs() {
        return htmlDocs;
    }

    public ArrayList<Document> getXmlDocs() {
        return xmlDocs;
    }

    public CSSStyleSheet getCssDocs() {
        return cssDocs;
    }

    public String getCssDocString() {
        return cssDocString;
    }

    public CompilationUnitTree getJavaScriptDocs() {
        return javaScriptDocs;
    }

    public void clearResults(){
        results = new ArrayList<>();
    }
}
