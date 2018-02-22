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

    public void runTests(List<Testable> tests) throws Exception{
        model.runTests(tests);
    }

    public void loadFolders(File[] folders){
        model.loadFiles(folders);
    }

    public void closeFiles(){
        model.closeFiles();
    }
}
