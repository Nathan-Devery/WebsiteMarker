package view;

import model.Model;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by Nathan on 22/11/2017.
 */
public class UI implements Observer{

    Model model;
    Controller controller;
    JFileChooser chooser = new JFileChooser();
    JTabbedPane tabbedPane = new JTabbedPane();

    //Swing related
    public static final int WIDTH = 700;
    public static final int HEIGHT = 525;
    JFrame frame = new JFrame();

    //Panes
    OptionsPane optionsPane;
    ResultsPane resultsPane;
    UnmarkablePane unmarkablePane;

    public UI(Model model) {
        this.model = model;
        model.addObserver(this);
        controller = new Controller(model);

        initializeFrame();
        initializeMenu();

        chooser.setMultiSelectionEnabled(true);
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        optionsPane = new OptionsPane(model, controller, frame);
        resultsPane = new ResultsPane(model, frame, controller);
        unmarkablePane = new UnmarkablePane(model);

        tabbedPane.addTab("Options", optionsPane);
        tabbedPane.addTab("Results", resultsPane);
        tabbedPane.add("ManualCheck", unmarkablePane);
        frame.add(tabbedPane);
    }

    private void initializeFrame(){
        frame = new JFrame("INFO101: Website Marker");
        frame.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        //frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        ImageIcon img = new ImageIcon("src/assets/tick.png");
        frame.setIconImage(img.getImage());
        frame.pack();
        frame.setVisible(true);
    }

    private void initializeMenu(){
        JMenuBar menuBar;
        JMenu menu1;
        JMenu menu2;
        JMenu menu3;
        JMenuItem menuItem;
        menuBar = new JMenuBar();

        menu1 = new JMenu("File");
        menu2 = new JMenu("Help");
        menu3 = new JMenu("Generate");
        menuBar.add(menu1);
        menuBar.add(menu3);
        menuBar.add(menu2);

        frame.setJMenuBar(menuBar);

        menuItem = new JMenuItem("Open");
        menuItem.addActionListener(e -> {
            model.closeFiles();
            chooser.showOpenDialog(frame);
            controller.loadFolders(chooser.getSelectedFiles());
        });
        menu1.add(menuItem);

        menuItem = new JMenuItem("Upload");
        menuItem.addActionListener(e -> {
            //TODO complete upload
            /*
            model.closeFiles();
            chooser.showOpenDialog(frame);
            controller.loadFolders(chooser.getSelectedFiles());
            */
        });
        menu1.add(menuItem);

        menuItem = new JMenuItem("Close");
        menuItem.addActionListener(e -> {
            controller.closeFiles();
        });
        menu1.add(menuItem);

        menuItem = new JMenuItem("About");
        menuItem.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame,
                    "Build 1.1\nAuthor: Nathan Devery",
                    "About",
                    JOptionPane.INFORMATION_MESSAGE,
                    new ImageIcon("src/assets/victoriaLogo.png"));
        });
        menu2.add(menuItem);

        menuItem = new JMenuItem("Support");
        menuItem.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame,
                    "Email:\ninsertEmail@gmail.com",
                    "Support",
                    JOptionPane.INFORMATION_MESSAGE);
        });
        menu2.add(menuItem);

        menuItem = new JMenuItem("log.txt");
        menuItem.addActionListener(e -> {
                    //TODO: implement
                });
        menu3.add(menuItem);

        menuItem = new JMenuItem("config.tst");
        menuItem.addActionListener(e -> {
            //TODO: implement
        });
        menu3.add(menuItem);
    }

    public static void displayError(JFrame frame, Exception e){
        JOptionPane.showMessageDialog(frame, e.getMessage(),
                "Operation Error",
                JOptionPane.WARNING_MESSAGE);
        e.printStackTrace();
    }

    @Override
    public void update(Observable o, Object arg) {
        resultsPane.redraw();
        unmarkablePane.redraw();
    }
}
