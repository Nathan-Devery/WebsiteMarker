package model;

import org.jsoup.nodes.Document;
import org.w3c.dom.css.CSSStyleSheet;

import java.util.ArrayList;

public class Assignment {

    private String nameID;
    private ArrayList<TestResult> results = new ArrayList<>();
    private ArrayList<Document> htmlDocs = new ArrayList<>();
    private CSSStyleSheet cssDocs;
    private Document javaScriptDocs;

    public Assignment(String nameID, ArrayList<Document> htmlDocs, CSSStyleSheet cssDocs, Document javaScriptDocs) {
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

    public ArrayList<Document> getHtmlDocs() {
        return htmlDocs;
    }

    public CSSStyleSheet getCssDocs() {
        return cssDocs;
    }

    public Document getJavaScriptDocs() {
        return javaScriptDocs;
    }
}
