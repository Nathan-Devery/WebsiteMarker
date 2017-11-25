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
public class Model extends java.util.Observable{

    ArrayList<Document> documents = new ArrayList<>();

    ArrayList<Testable> availableTests;
    List<Testable> currentTests = new ArrayList<>();
    String currentDirectory = "";

    public Model() {
        availableTests = TestManager.initializeTests();
    }

    public void loadFiles(File[] files){
        currentDirectory = files[0].getParent();

        //Could replace with enhanced for loop
        for(int i = 0; i < files.length; i++){
            try {
                String currentDirectory = files[i].getAbsolutePath().substring(0, files[i].getAbsolutePath().length()
                        - files[i].getName().length());
                documents.add(Jsoup.parse(files[i], "UTF-8", currentDirectory));
            }catch(Exception e){
                e.printStackTrace();
            }
        }

        setChanged();
        notifyObservers();
    }

    /**
     * Removes the currently loaded in files/documents
     */
    public void closeFiles(){
        this.currentDirectory = "";
        this.documents = new ArrayList<>();
        this.currentTests = new ArrayList<>();
        setChanged();
        notifyObservers();
    }

    public void runTests() throws Exception{
        if(documents.isEmpty()) throw new Exception("No files open");

        for(Testable test: currentTests){
            test.runTest(documents);
        }
        setChanged();
        notifyObservers();
    }

    public Testable[] getAvailableTests() {
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
}
