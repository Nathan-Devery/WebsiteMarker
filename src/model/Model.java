package model;

import javafx.beans.Observable;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nathan on 22/11/2017.
 */
public class Model extends java.util.Observable{

    ArrayList<Document> documents = new ArrayList<>();

    ArrayList<Testable> availableTests = new ArrayList<>();
    List<Testable> currentTests = new ArrayList<>();

    public Model() {
        availableTests = TestManager.initializeTests();
    }

    public void loadFiles(File[] files){
        //Could replace with enhanced for loop
        for(int i = 0; i < files.length; i++){
            try {
                documents.add(Jsoup.parse(files[i], "UTF-8", "http://example.com/"));
            }catch(Exception e){
                e.printStackTrace();
            }
        }
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

    public void setToTest(List<Testable> currentTests) {
        this.currentTests = currentTests;
    }
}
