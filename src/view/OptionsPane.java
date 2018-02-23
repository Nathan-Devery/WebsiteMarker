package view;

import model.Model;
import model.Testable;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.*;

/**
 * Created by Nathan on 23/11/2017.
 */
public class OptionsPane extends JPanel {

    Model model;
    Controller controller;
    JTable table;
    JFrame frame;
    JLabel directoryLabel = new JLabel("");
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
        drawDirectory();

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

        for(int i = 0; i < availableTests.length; i++){
            data[i] = availableTests[i];    //get name
        }

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Tests", data);
        JTable table = new JTable(model);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        table.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );

        return table;
    }

    private void drawButton(){
        JButton button = new JButton("Test");
        button.addActionListener(k -> {
            //controller.setToTest(list.getSelectedValuesList());
            try {
                int[] selectedRows = table.getSelectedRows();
                ArrayList<Testable> selectedTests = new ArrayList<>();
                for(int i = 0; i < selectedRows.length; i++){
                    selectedTests.add(availableTests[selectedRows[i]]);
                }
                controller.runTests(selectedTests);  //This possibly breaks MVC philosophy
            }catch (Exception e){
                UI.displayError(frame, e);
            }
        });
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(button);
    }

    public void drawDirectory(){
        this.remove(this.directoryLabel);
        directoryLabel = new JLabel(model.getCurrentDirectory());
        directoryLabel.setForeground(Color.green.darker());
        directoryLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(directoryLabel);
        revalidate();
        repaint();
    }

}
