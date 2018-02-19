package model;

import javafx.beans.Observable;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import com.steadystate.css.parser.CSSOMParser;
import org.w3c.css.sac.InputSource;
import org.w3c.dom.css.CSSStyleSheet;
import org.w3c.dom.css.CSSRuleList;
import org.w3c.dom.css.CSSRule;
import org.w3c.dom.css.CSSStyleRule;
import org.w3c.dom.css.CSSStyleDeclaration;

/**
 * Created by Nathan on 22/11/2017.
 */
public class Model extends java.util.Observable {

    ArrayList<Document> htmlDocs = new ArrayList<>();
    ArrayList<CSSStyleSheet> cssDocs = new ArrayList<>();
    ArrayList<Document> javaScriptDocs = new ArrayList<>();

    //TODO make into 1
    List<Testable> currentTests = new ArrayList<>();
    /*
    ArrayList<Testable> currentHtmlTests = new ArrayList<Testable>();
    ArrayList<Testable> currentJavascriptTests = new ArrayList<Testable>();
    ArrayList<Testable> currentCssTests = new ArrayList<Testable>();
    */


    //ArrayList<Testable> availableTests;
    //List<Testable> currentTests = new ArrayList<>();

    String currentDirectory = "";

    public Model() {

    }

    public void loadFiles(File[] folders) {
        for (int i = 0; i < folders.length; i++) {
            for (final File file : folders[i].listFiles()) {
                String fileName = file.getName().toLowerCase();
                String currentDirectory = file.getAbsolutePath().substring(0, file.getAbsolutePath().length() - file.getName().length());

                try {
                    if (fileName.endsWith(".html")) {
                        htmlDocs.add(Jsoup.parse(file, "UTF-8", file.getName()));
                    } else if (fileName.endsWith(".css")) {
                        cssDocs.add(parseCss(file));
                    } else if (fileName.endsWith(".js")) {

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    //TODO add something to log
                }
            }
        }

        /*
        currentDirectory = files[0].getParent();

        //Could replace with enhanced for loop
        for (int i = 0; i < files.length; i++) {
            try {
                String currentDirectory = files[i].getAbsolutePath().substring(0, files[i].getAbsolutePath().length()
                        - files[i].getName().length());
                documents.add(Jsoup.parse(files[i], "UTF-8", currentDirectory));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        setChanged();
        notifyObservers();
        */
    }

    /**
     * Removes the currently loaded in files/documents
     */
    public void closeFiles() {
        this.currentDirectory = "";
        this.htmlDocs = new ArrayList<>();

        this.currentTests = new ArrayList<>();

        setChanged();
        notifyObservers();
    }

    public void runTests(List<Testable> tests) throws Exception {
        this.setToTest(tests);
        if (htmlDocs.isEmpty() && cssDocs.isEmpty() && javaScriptDocs.isEmpty()) throw new Exception("No files open");

        for (Testable test : tests) {
            test.runTest(htmlDocs, cssDocs);
        }
        setChanged();
        notifyObservers();
    }

    public Testable[] getAvailableTests() {
        ArrayList<Testable> availableTests = TestManager.getTests();
        return availableTests.toArray(new Testable[availableTests.size()]);
    }

    public List<Testable> getCurrentTests() {
        return currentTests;
    }

    public String getCurrentDirectory() {
        return currentDirectory;
    }

    public void setToTest(List<Testable> currentTests) {
        this.currentTests = currentTests;
    }

    private CSSStyleSheet parseCss(File file) throws IOException {
        InputStream stream = new FileInputStream(file);
        InputSource source = new InputSource(new InputStreamReader(stream));
        CSSOMParser parser = new CSSOMParser();
        CSSStyleSheet stylesheet = parser.parseStyleSheet(source, null, null);  //These can be stored
        return stylesheet;
    }
}
