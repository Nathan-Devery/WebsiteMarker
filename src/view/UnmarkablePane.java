package view;

import model.Model;
import model.Testable;
import model.Unmarkable;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class UnmarkablePane extends JPanel{

    Model model;

    public UnmarkablePane(Model model){
        this.model = model;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

    public void redraw(){
        removeAll();

        drawTable();

        revalidate();
        repaint();
    }

    private void drawTable(){
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(createTable());
        scrollPane.setPreferredSize(new Dimension(UI.WIDTH * 2/3, 200));
        this.add(scrollPane);
    }

    private JTable createTable() {
        ArrayList<Unmarkable> unmarkables = model.getUnmarkables();

        String[][] data = new String[unmarkables.size()][2];
        for (int i = 0; i < unmarkables.size(); i++) {
            data[i][0] = unmarkables.get(i).getAssignmentName();
            data[i][1] = unmarkables.get(i).getReason();
        }

        String[] columns = new String[]{"Assignment Name", "Reason"};

        DefaultTableModel model = new DefaultTableModel(data, columns);
        return new JTable(model);
}

}
