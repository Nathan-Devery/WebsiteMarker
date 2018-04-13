package model;

import jdk.nashorn.api.tree.CompilationUnitTree;
import org.jsoup.nodes.Document;
import org.w3c.dom.css.CSSStyleSheet;

import java.util.ArrayList;

/**
 * The interface implemented by all condition tests.
 */
public abstract class Testable {

    private String name = "n/a";

    public Testable(String name){
        this.name = name;
    }

    @Override
    public String toString(){
        return name;
    }

    public abstract String getDescription();

    public abstract TestResult runTest(ArrayList<Document> documents, ArrayList<Document> xmlDocuments, CSSStyleSheet sheet, String cssDocString, CompilationUnitTree tree, double percentage);

}
