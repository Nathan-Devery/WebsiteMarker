package view;

import model.Model;

import javax.swing.*;
import java.awt.*;

public class CSVJframe extends JFrame {

    private static int WIDTH = 350;
    private static int HEIGHT = 260;
    private Model model;

    public CSVJframe(Model model){
        super("CSV Generator");
        this.model = model;
    }

    private void initializeFrame(){
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        //frame.setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        ImageIcon img = new ImageIcon("src/assets/tick.png");
        setIconImage(img.getImage());
        pack();
        setVisible(true);
    }


}
