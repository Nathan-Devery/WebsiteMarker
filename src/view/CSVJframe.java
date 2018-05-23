package view;

import model.IllegalOperationException;
import model.Model;
import model.Testable;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;

/**
 * Encompasses all the functionality of the csv generator popup.
 */
public class CSVJframe extends JFrame {

    private static int WIDTH = 462;
    private static int HEIGHT = 346;

    private Model model;
    private Controller controller;

    private JFileChooser chooser;
    private JComboBox<String> userNameCol;
    private JComboBox<String> studentIdCol;
    private JComboBox<String> gradeCol;

    private File file;

    public CSVJframe(Model model, Controller controller) throws IllegalOperationException{
        super("CSV Appender");
        if(model.getAssignments().isEmpty()) throw new IllegalOperationException("No assignments are currently loaded");

        this.model = model;
        this.controller = controller;
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        initializeFrame();
        initializeChooser();
        initializeMenu();
        redraw();
    }

    private void initializeFrame(){
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        //frame.setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ImageIcon img = new ImageIcon("src/assets/tick.png");
        //ImageIcon img = new ImageIcon(getClass().getResource("src/assets/tick.png"));
        setIconImage(img.getImage());
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-WIDTH/2, dim.height/2-HEIGHT/2);
        pack();
        setVisible(true);
    }

    private void initializeChooser(){
        this.chooser = new JFileChooser();
        FileFilter filter = new FileNameExtensionFilter("CSV File", "csv");
        chooser.setFileFilter(filter);
    }

    private void initializeMenu(){
        JMenuBar menuBar;
        JMenu menu1;
        menuBar = new JMenuBar();
        menu1 = new JMenu("File");
        menuBar.add(menu1);

        JMenuItem menuItem = new JMenuItem("Open");
        menuItem.addActionListener(e -> {
            chooser.showOpenDialog(this);
            file = chooser.getSelectedFile();
            try {
                controller.loadCSV(file);
            }catch(IllegalOperationException exception){
                UI.displayError(this, exception);
            }
        });
        menu1.add(menuItem);

        this.setJMenuBar(menuBar);
    }

    /***
     * Creates and adds the JComponents to the frame
     */
    public void redraw(){
        this.getContentPane().removeAll();

        JLabel usernameLabel = new JLabel("Username Column");
        usernameLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        add(usernameLabel);
        userNameCol = createColSelection();
        add(userNameCol);

        JLabel studentLabel = new JLabel("Student ID Column");
        studentLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        add(studentLabel);
        studentIdCol = createColSelection();
        add(studentIdCol);

        JLabel gradeLabel = new JLabel("Grade Column");
        gradeLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        add(gradeLabel);
        gradeCol = createColSelection();
        add(gradeCol);

        JButton button = new JButton("Create CSV");
        button.addActionListener(k -> {
            try {
                controller.createCSV(userNameCol.getSelectedIndex(), studentIdCol.getSelectedIndex(), gradeCol.getSelectedIndex());
                JOptionPane.showMessageDialog(this, "CSV created, CSV NotFound report created. \n"
                                + model.getUnpairables().size() + " Assignments added to 'Requires Attention' tab.",
                        "CVS created",
                        JOptionPane.INFORMATION_MESSAGE);
            }catch(IllegalOperationException exception){
                UI.displayError(this, exception);
            }
            setVisible(false);
            dispose();
        });
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(button);

        revalidate();
        repaint();
    }

    private JComboBox<String> createColSelection(){
        JComboBox<String> columnSelection = new JComboBox<>();

        String[] columns = model.getColumns();
        for(int i = 0; i < columns.length; i++){
            columnSelection.addItem(columns[i]);
        }
        return columnSelection;
    }

}
