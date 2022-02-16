import javax.swing.*;

import static java.awt.Color.black;

public class TextAreas extends MyWindow {

    protected JTextArea ta1;

    public TextAreas () {

        ta1 = new JTextArea();

    }

    public JTextArea ret () {

        //lSystem
        ta1.setBounds(15, 15, 650, 525);
        ta1.setBorder(BorderFactory.createLineBorder(black,1));
        ta1.setEditable(false);
        ta1.setVisible(true);

        return ta1;
    }

}
