package view;

import model.Model;
import model.Testable;

import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.*;

public class TestDetailFrame extends JFrame {

    private static int WIDTH = 462;
    private static int HEIGHT = 346;

    private Model model;

    public TestDetailFrame(Model model){
        super("Test Descriptions");
        this.model = model;
        initalizeFrame();
        add(this.createDiscriptionScroll());
    }

    private void initalizeFrame(){
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ImageIcon img = new ImageIcon("src/assets/tick.png");
        setIconImage(img.getImage());
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-WIDTH/2, dim.height/2-HEIGHT/2);
        pack();
        setVisible(true);
    }

    private JScrollPane createDiscriptionScroll(){
        String descriptionReport = "";

        Testable[] availableTests = model.getAvailableTests();
        for(int i = 0; i < availableTests.length; i++) {
            Testable test = availableTests[i];
            descriptionReport += test.toString() + "\n";
            descriptionReport += test.getDescription() + "\n\n";
        }
        JTextPane jTextPane = new JTextPane();

        DefaultCaret caret = (DefaultCaret) jTextPane.getCaret();
        caret.setUpdatePolicy(DefaultCaret.NEVER_UPDATE);

        jTextPane.setText(descriptionReport);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(jTextPane);


        return scrollPane;
    }

}
