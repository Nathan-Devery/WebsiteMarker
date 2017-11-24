package launch;

import model.Model;
import view.UI;

import javax.swing.*;

/**
 * Created by Nathan on 7/10/2017.
 */
public class Main {
    public static void main(String [ ] args){
        SwingUtilities.invokeLater(()->new UI(new Model()));
    }
}
