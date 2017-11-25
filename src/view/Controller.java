package view;

import model.Model;
import model.Testable;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nathan on 22/11/2017.
 */
public class Controller {

    Model model;

    public Controller(Model model) {
        this.model = model;
    }

    public void runTests() throws Exception{
        model.runTests();
    }

    public void setToTest(List<Testable> tests){
        model.setToTest(tests);
    }

    public void loadFiles(File[] files){
        model.loadFiles(files);
    }

    public void closeFiles(){
        model.closeFiles();
    }
}
