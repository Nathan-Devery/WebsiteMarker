package view;

import model.IllegalOperationException;
import model.Model;
import model.Testable;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * The controller in a MVC pattern.
 * The UI will only interact with the model through this class.
 */
public class Controller {

    Model model;

    public Controller(Model model) {
        this.model = model;
    }

    public void runTests(List<Testable> tests, ArrayList<Double> percentages) throws IllegalOperationException{
        model.runTests(tests, percentages);
    }

    public void loadFolders(File[] folders) throws IllegalOperationException{
        model.loadFiles(folders);
    }

    public void closeFiles(){
        model.closeFiles();
    }

    public void loadCSV(File file) throws IllegalOperationException{
        model.loadCSV(file);
    }

    public void createCSV(int usernameCol, int studentIdCol, int gradeCol) throws IllegalOperationException{
        model.createCSV(usernameCol, studentIdCol, gradeCol);
    }

    public void loadConfig(File file) throws IllegalOperationException{
        model.loadConfig(file);
    }

    public void createConfigFile(File path){
        model.createConfigFile(path);
    }
}
