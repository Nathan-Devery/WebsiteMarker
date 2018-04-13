package view;

import model.Model;
import model.Malformed;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

/**
 * Pane that displays assignments that could not be marked for various reasons.
 * Displays assignments that could not be matched to a pupil in an imported csv (when attempting to export a csv).
 */
public class NeedsAttentionPane extends JPanel{

    Model model;

    public NeedsAttentionPane(Model model){
        this.model = model;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        redraw();
    }

    public void redraw(){
        removeAll();

        addMalformedSection("Unmarkable", model.getUnmarkables());
        addMalformedSection("Not found in CSV", model.getUnpairables());

        revalidate();
        repaint();
    }

    private void addMalformedSection(String title, ArrayList<Malformed> malformeds){
        JScrollPane scrollPane = new JScrollPane();
        add(createLabel(title));
        scrollPane.setViewportView(createMalformedTable(malformeds));
        scrollPane.setPreferredSize(new Dimension(UI.WIDTH * 2/3, 200));
        this.add(scrollPane);
    }

    private JLabel createLabel(String labelText){
        JLabel unmarkableLabel = new JLabel(labelText);
        unmarkableLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        return unmarkableLabel;
    }


    private JTable createMalformedTable(ArrayList<Malformed> malformeds) {
        String[][] data = new String[malformeds.size()][2];
        for (int i = 0; i < malformeds.size(); i++) {
            data[i][0] = malformeds.get(i).getAssignmentName();
            data[i][1] = malformeds.get(i).getReason();
        }

        String[] columns = new String[]{"Assignment Name", "Reason"};

        DefaultTableModel model = new DefaultTableModel(data, columns);
        return new JTable(model);
    }


}
