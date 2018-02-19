package view;

import model.Model;
import model.Testable;
import org.junit.Test;

import javax.swing.*;
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


    public ResultsPane(Model model, JFrame frame, Controller controller){
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

        //ScrollPane
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(createTable());
        scrollPane.setBounds(UI.WIDTH/24, UI.HEIGHT/3, UI.WIDTH * 4/5, UI.HEIGHT * 2/3);

        scrollPane.setPreferredSize(new Dimension(UI.WIDTH * 2/3, 200)); //The scroll bars appear when preferred size <
        scrollPane.setAlignmentX(Component.CENTER_ALIGNMENT);

        scrollPane.setMaximumSize(new Dimension(UI.WIDTH * 4/5, UI.HEIGHT * 2/3));    //BoxLayout only honors max and min siz -,-

        this.add(scrollPane);

        revalidate();
        repaint();
    }

    private JTable createTable(){
        List<Testable> currentTests = model.getCurrentTests();

        Object[][] data = new Object[currentTests.size()][2];

        for(int i = 0; i < currentTests.size(); i++){
            data[i][0] = currentTests.get(i).toString();    //get name
            data[i][1] = currentTests.get(i).getResult();   //get result
        }

        String[] columns = new String[]{"Test", "Result"};
        return new JTable(data, columns);
    }


}
