package view;

import model.Assignment;
import model.Model;
import model.TestResult;
import model.Testable;
import org.junit.Test;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
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

    JTable studentTable;
    JTable resultsTable;
    JTextPane evidencePane;

    int selectedStudent = 0;
    int selectedTestResult = 0;

    public ResultsPane(Model model, JFrame frame, Controller controller) {
        this.model = model;
        this.frame = frame;
        this.controller = controller;
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        studentTable = createStudentTable();
        resultsTable = createResultTable();
        redraw();
    }

    public void redraw() {
        removeAll();

        //ScrollPane
        JScrollPane studentPane = new JScrollPane();
        studentTable.setModel(getStudentData());
        studentPane.setViewportView(studentTable);
        studentPane.setPreferredSize(new Dimension(UI.WIDTH * 2 / 3, UI.HEIGHT * 1/2)); //The scroll bars appear when preferred size <
        this.add(studentPane);

        JScrollPane resultsPane = new JScrollPane();
        resultsTable.setModel(getResultData());
        resultsPane.setViewportView(resultsTable);
        resultsPane.setPreferredSize(new Dimension(UI.WIDTH * 2 / 3, UI.HEIGHT * 1/2));
        this.add(resultsPane);

        JScrollPane scrollPane = new JScrollPane();
        evidencePane = createEvidencePane();
        evidencePane.setText(getEvidenceString());
        scrollPane.setViewportView(evidencePane);
        scrollPane.setPreferredSize(new Dimension(UI.WIDTH * 2 / 3, UI.HEIGHT * 1/2));
        this.add(scrollPane);

        revalidate();
        repaint();
    }

    private JTable createStudentTable() {
        JTable table = new JTable(getStudentData());

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);

        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        ListSelectionModel selectionModel = table.getSelectionModel();
        selectionModel.addListSelectionListener(e -> {
            selectedStudent = table.getSelectedRow();
            DefaultTableModel model = getResultData();
            resultsTable.setModel(model);
            evidencePane.setText(getEvidenceString());
            resultsTable.changeSelection(selectedTestResult, 0, true, false);
        });

        table.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                selectedStudent = table.rowAtPoint(evt.getPoint());
                DefaultTableModel model = getResultData();
                resultsTable.setModel(model);
                evidencePane.setText(getEvidenceString());
                resultsTable.changeSelection(selectedTestResult, 0, true, false);
            }
        });
        return table;
    }

    private JTable createResultTable() {
        DefaultTableModel resultsData = getResultData();
        JTable table = new JTable();
        table.setModel(resultsData);

        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        ListSelectionModel selectionModel = table.getSelectionModel();
        selectionModel.addListSelectionListener(e -> {
            if(table.getSelectedRow() == -1) return;
            selectedTestResult =  table.getSelectedRow();
            evidencePane.setText(getEvidenceString());
        });

        table.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                selectedTestResult = table.rowAtPoint(evt.getPoint());
                evidencePane.setText(getEvidenceString());
            }
        });
        return table;
    }

    /**
     * Creates evidence pane. Not necessary, but keeps convention of calling a create method.
     * @return  the created text pane
     */
    private JTextPane createEvidencePane() {
        JTextPane pane = new JTextPane();
        pane.setEditable(false);
        return pane;
    }

    private DefaultTableModel getResultData() {
        ArrayList<Assignment> assignments = model.getAssignments();
        ArrayList<TestResult> testResults = new ArrayList<>();
        if (!assignments.isEmpty() && !assignments.get(0).getResults().isEmpty()){
            testResults = assignments.get(selectedStudent).getResults();
        }

        String[][] data = new String[testResults.size()][2];
        for (int i = 0; i < testResults.size(); i++) {
            data[i][0] = testResults.get(i).getTestName();    //get name
            data[i][1] = String.valueOf(testResults.get(i).getResult());   //get result
        }
        //data[testResults.size() + 1][0] = "TOTAL";
        //if(!assignments.isEmpty()) data[testResults.size() + 1][1] = String.valueOf(assignments.get(selectedStudent).getTotalPercentage());

        String[] columns = new String[]{"Test", "Result"};
        return new DefaultTableModel(data, columns);
    }

    private DefaultTableModel getStudentData() {
        ArrayList<Assignment> assignments = model.getAssignments();
        String[] data = new String[assignments.size()];

        for (int i = 0; i < assignments.size(); i++) {
            data[i] = assignments.get(i).getNameID();    //get name
        }

        DefaultTableModel studentData = new DefaultTableModel();
        studentData.addColumn("Name-ID", data);
        return studentData;
    }

    private String getEvidenceString(){
        if(model.getAssignments().isEmpty() || model.getAssignments().get(selectedStudent).getResults().isEmpty()) return "";
        TestResult result = model.getAssignments().get(selectedStudent).getResults().get(selectedTestResult);

        String evidence = "";
        evidence += result.getTestName() + "\n";
        evidence += result.getEvidenceLog();
        return evidence;
    }
}
