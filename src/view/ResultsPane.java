package view;

import model.Assignment;
import model.Model;
import model.TestResult;
import model.Testable;
import org.junit.Test;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nathan on 23/11/2017.
 */
public class ResultsPane extends JPanel {

    Model model;
    JFrame frame;
    Controller controller;
    ArrayList<Assignment> assignments;

    int selectedStudent = 0;
    int selectedTestResult = 0;

    public ResultsPane(Model model, JFrame frame, Controller controller){
        this.model = model;
        this.frame = frame;
        this.controller = controller;
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        redraw();
    }

    public void redraw(){
        assignments = model.getAssignments();
        removeAll();

        //ScrollPane
        JScrollPane studentPane = new JScrollPane();
        studentPane.setViewportView(createStudentTable());
        studentPane.setPreferredSize(new Dimension(UI.WIDTH * 2/3, 200)); //The scroll bars appear when preferred size <
        this.add(studentPane);

        JScrollPane scrollPane2 = new JScrollPane();
        scrollPane2.setViewportView(createTable());
        scrollPane2.setPreferredSize(new Dimension(UI.WIDTH * 2/3, 200));
        this.add(scrollPane2);

        JScrollPane scrollPane3 = new JScrollPane();
        scrollPane3.setViewportView(createTable());
        scrollPane3.setPreferredSize(new Dimension(UI.WIDTH * 2/3, 200));
        this.add(scrollPane3);

        revalidate();
        repaint();
    }

    private JTable createResultTable(){


        /*
        List<Testable> currentTests = model.getCurrentTests();

        Object[][] data = new Object[currentTests.size()][2];

        for(int i = 0; i < currentTests.size(); i++){
            data[i][0] = currentTests.get(i).toString();    //get name
            //data[i][1] = currentTests.get(i).getResult();   //get result
        }

        String[] columns = new String[]{"Test", "Result"};
        return new JTable(data, columns);
        */
    }

    private JTable createStudentTable(){
        ArrayList<Assignment> assignments = model.getAssignments();

        String[] data = new String[assignments.size()];

        for(int i = 0; i < assignments.size(); i++){
            data[i] = assignments.get(i).getNameID();    //get name
        }

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Name-ID", data);
        JTable table = new JTable(model);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        table.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );

        return table;
    }

    /*
    private JTable createLogTable(){

    }
    */



}
