package view;

import model.Model;
import model.Testable;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Nathan on 23/11/2017.
 */
public class OptionsPane extends JPanel {

    Model model;
    Controller controller;
    JList<Testable> list;
    JFrame frame;
    JLabel directoryLabel = new JLabel("");

    public OptionsPane(Model model, Controller controller, JFrame frame){
        this.model = model;
        this.controller = controller;
        this.frame = frame;

        list = new JList<>(model.getAvailableTests());

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        redraw();
    }

    public void redrawList(){
        drawList();
    }

    public void redraw(){
        removeAll();
        //Add gap
        this.add(Box.createRigidArea(new Dimension(0, UI.HEIGHT/20)));
        drawList();

        //Add gap
        this.add(Box.createRigidArea(new Dimension(0, UI.HEIGHT/20)));
        drawButton();

        this.add(Box.createRigidArea(new Dimension(0, UI.HEIGHT/20)));

        drawDirectory();

        revalidate();
        repaint();
    }

    private void drawList(){
        int cellWidth = UI.WIDTH * 4/5;
        int cellHeight = UI.HEIGHT / 2;

        list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        list.setVisibleRowCount(-1);
        list.setFixedCellWidth(cellWidth);

        //Center text
        DefaultListCellRenderer renderer = (DefaultListCellRenderer) list.getCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);

        //Create scrollPane
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(list);
        scrollPane.setBounds(UI.WIDTH/24, UI.HEIGHT/3, cellWidth, cellHeight);

        scrollPane.setPreferredSize(new Dimension(cellWidth, 200)); //The scroll bars appear when preferred size <
        scrollPane.setAlignmentX(Component.CENTER_ALIGNMENT);

        scrollPane.setMaximumSize(new Dimension(cellWidth, cellHeight));    //BoxLayout only honors max and min siz -,-

        this.add(scrollPane);
    }

    private void drawButton(){
        JButton button = new JButton("Test");
        button.addActionListener(e -> {
            controller.setToTest(list.getSelectedValuesList());
            try {
                controller.runTests();
            }catch (Exception k){
                JOptionPane.showMessageDialog(frame, k.getMessage(),
                        "Operation Error",
                        JOptionPane.WARNING_MESSAGE);
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
