package model;

import javafx.beans.Observable;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nathan on 22/11/2017.
 */
public class Model extends java.util.Observable {

    ArrayList<Document> htmlDocs = new ArrayList<>();
    //ArrayList<Document> cssDocs = new ArrayList<>();  //TODO change generic type to appropriate
    //ArrayList<Document> javaScriptDocs = new ArrayList<>();

    //TODO remove the default arraylist, this should be initialized by the testmanager
    ArrayList<Testable> currentHtmlTests = new ArrayList<Testable>();
    ArrayList<Testable> currentJavascriptTests = new ArrayList<Testable>();
    ArrayList<Testable> currentCssTests = new ArrayList<Testable>();


    ArrayList<Testable> availableTests;
    List<Testable> currentTests = new ArrayList<>();
    String currentDirectory = "";

    public Model() {
        availableTests = TestManager.initializeTests();
    }

    public void loadFiles(File[] folders) {
        for(int i = 0; i < folders.length; i++) {
            for (final File fileEntry : folders[i].listFiles()) {
                String fileName = fileEntry.getName().toLowerCase();

                //TODO load in files
                if(fileName.endsWith(".html")){

                }else if(fileName.endsWith(".css")){

                }else if(fileName.endsWith(".js")){

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
        if (htmlDocs.isEmpty()) throw new Exception("No files open");

        for (Testable test : tests) {
            test.runTest(htmlDocs);
        }
        setChanged();
        notifyObservers();
    }

    public Testable[] getAvailableTests() {
        ArrayList<Testable> availableTests = TestManager.initializeTests();
        return availableTests.toArray(new Testable[availableTests.size()]);
    }

    public List<Testable> getCurrentTests() {
        ArrayList<Testable> currentTests = new ArrayList<>();
        currentTests.addAll(currentHtmlTests);
        currentTests.addAll(currentCssTests);
        currentTests.addAll(currentJavascriptTests);

        return currentTests;
    }

    public String getCurrentDirectory() {
        return currentDirectory;
    }

    public void setToTest(List<Testable> currentTests) {
        this.currentTests = currentTests;
    }
}
