package model;

import jdk.nashorn.api.tree.CompilationUnitTree;
import org.jsoup.nodes.Document;
import org.w3c.dom.css.CSSStyleSheet;

import java.util.ArrayList;

public class Assignment {

    private String nameID;
    private ArrayList<TestResult> results = new ArrayList<>();
    private ArrayList<Document> htmlDocs;
    private CSSStyleSheet cssDocs;
    private CompilationUnitTree javaScriptDocs;

    public Assignment(String nameID, ArrayList<Document> htmlDocs, CSSStyleSheet cssDocs, CompilationUnitTree javaScriptDocs) {
        this.nameID = nameID;
        this.htmlDocs = htmlDocs;
        this.cssDocs = cssDocs;
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

    public CSSStyleSheet getCssDocs() {
        return cssDocs;
    }

    public CompilationUnitTree getJavaScriptDocs() {
        return javaScriptDocs;
    }

    public void clearResults(){
        results = new ArrayList<>();
    }
}
