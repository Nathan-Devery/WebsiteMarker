package view;

import model.Config;
import model.IllegalOperationException;
import model.Model;
import model.Testable;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by Nathan on 23/11/2017.
 */
public class OptionsPane extends JPanel {

    Model model;
    Controller controller;
    JTable table;
    JFrame frame;
    Testable[] availableTests;


    public OptionsPane(Model model, Controller controller, JFrame frame){
        this.model = model;
        this.controller = controller;
        this.table = createTable();
        this.frame = frame;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        redraw();
    }

    public void redraw(){
        removeAll();

        drawList();
        drawButton();

        applyConfig(this.table, availableTests);

        revalidate();
        repaint();
    }

    private void drawList(){
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(table);
        scrollPane.setPreferredSize(new Dimension(UI.WIDTH * 2/3, 200));
        this.add(scrollPane);
    }

    private JTable createTable(){

        availableTests = model.getAvailableTests();

        Testable[] data = new Testable[availableTests.length];
        String[] data2 = new String[availableTests.length];

        for(int i = 0; i < availableTests.length; i++){
            data[i] = availableTests[i];    //get name
            data2[i] = "1.0"; //TODO create arraylist with default values
        }

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Tests", data);
        model.addColumn("Percentage", data2);
        JTable table = new JTable(model);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        table.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
        table.getColumnModel().getColumn(1).setCellRenderer( centerRenderer );

        return table;
    }

    /***
     * Applies the configuration to the options table. This specifying which tests are selected to run and their
     * corresponding weighting (percent).
     * @param table
     * @param availableTests
     */
    private void applyConfig(JTable table, Testable[] availableTests){
        try {
            Config config = model.getConfig();

            List<String> selectedTestsNames = config.getSelectedTestsNames();
            List<Double> testPercentages = config.getSelectedTestPercentages();


            for (int i = 0; i < selectedTestsNames.size(); i++) {
                String testName = selectedTestsNames.get(i);
                for (int j = 0; j < availableTests.length; j++) {
                    if (availableTests[j].toString().equals(testName)) {
                        table.getModel().setValueAt(String.valueOf(testPercentages.get(i)), j, 1);
                        table.changeSelection(j, 0, true, false);
                    }
                }
            }
        }catch(IllegalOperationException e){
            UI.displayError(this.frame, e);
        }

    }

    private void drawButton(){
        JButton button = new JButton("Test");
        button.addActionListener(k -> {
            try {
                int[] selectedRows = table.getSelectedRows();

                ArrayList<Testable> selectedTests = new ArrayList<>();
                ArrayList<Double> percentages = new ArrayList<>();
                for (int i = 0; i < selectedRows.length; i++) {
                    selectedTests.add(availableTests[selectedRows[i]]);
                    percentages.add(Double.valueOf((String) (table.getModel().getValueAt(selectedRows[i], 1))));
                }
                controller.runTests(selectedTests, percentages);  //TODO fix to MVC paradigm?
            }catch(IllegalOperationException exception){
                UI.displayError(frame, exception);
            }catch (Exception e){
                e.printStackTrace();
            }
        });
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(button);
    }

}
