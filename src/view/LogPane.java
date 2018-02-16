package view;

import model.Model;
import model.Testable;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Created by Nathan on 23/11/2017.
 */
public class LogPane extends JPanel {

    Model model;
    JFrame frame;
    Controller controller;

    public LogPane(Model model, JFrame frame,Controller controller){
        this.model = model;
        this.frame = frame;
        this.controller = controller;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        redraw();
    }

    public void redraw(){
        removeAll();

        //Add gap
        this.add(Box.createRigidArea(new Dimension(0, UI.HEIGHT/20)));

        //Create scrollPane
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(createLogTextPane());
        scrollPane.setBounds(UI.WIDTH/24, UI.HEIGHT/3, UI.WIDTH * 4/5, UI.HEIGHT * 2/3);

        scrollPane.setPreferredSize(new Dimension(UI.WIDTH * 4/5, 200)); //The scroll bars appear when preferred size <
        scrollPane.setAlignmentX(Component.CENTER_ALIGNMENT);

        scrollPane.setMaximumSize(new Dimension(UI.WIDTH * 4/5, UI.HEIGHT * 2/3));    //BoxLayout only honors max and min siz -,-

        this.add(scrollPane);

        revalidate();
        repaint();
    }

    public JTextPane createLogTextPane(){
        JTextPane textPane = new JTextPane();
        textPane.setEditable(false);

        List<Testable> currentTests = model.getCurrentTests();

        String report = "";
        for(Testable test: currentTests){
            report += "**" + test.toString() + "**";
            report += "\n";
            report += "\n";
            report += test.getReport();
            report += "\n";
            report += "------------------------------------------------------------------------------------------------";
            report += "\n";
            report += "\n";
        }
        textPane.setText(report);

        return textPane;
    }
}
