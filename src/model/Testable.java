package model;

import jdk.nashorn.api.tree.CompilationUnitTree;
import org.jsoup.nodes.Document;
import org.w3c.dom.css.CSSStyleSheet;

import java.util.ArrayList;

/**
 * Created by Nathan on 22/11/2017.
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

    public abstract TestResult runTest(ArrayList<Document> documents, CSSStyleSheet sheet, CompilationUnitTree tree, double percentage);

}
