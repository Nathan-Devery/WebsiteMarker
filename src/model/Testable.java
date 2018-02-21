package model;

import org.jsoup.nodes.Document;
import org.w3c.dom.css.CSSStyleSheet;

import java.util.ArrayList;

/**
 * Created by Nathan on 22/11/2017.
 */
public abstract class Testable {

    private String name = "n/a";
    protected boolean result;
    protected String report = "";


    public Testable(String name){
        this.name = name;
    }

    @Override
    public String toString(){
        return name;
    }

    public boolean getResult(){
        return result;
    }

    public String getReport() {
        return report;
    }

    public abstract void runTest(ArrayList<Document> documents, CSSStyleSheet sheet);

    public void clear(){
        result = false;
        report = "";
    }
}
