import javax.swing.*;
import javax.swing.plaf.basic.BasicComboBoxRenderer;
import java.util.ArrayList;

public class SubMenu extends MyWindow {

    ArrayList<JComboBox> arrayBox;
    ArrayList<JTextField> textarrayBox;

    final JComboBox<String> menu, choiceBoxes;
    final JComboBox<Integer> iterBox;

    public SubMenu () {

         arrayBox = new ArrayList<>();
         textarrayBox = new ArrayList<>();

         String[] choices = { "LSystem Customizado", "Koch Curve","Koch Snowflake","Sierpinski Triangle","Sierpinski Arrowhead","DragonCurve", "CantorSet"};
         String[] textChoice = {"Escolha uma opção", "Forward", "Turn", "Leap", "Pen up","Pen down"};

         menu = new JComboBox<>(choices);
         choiceBoxes = new JComboBox<>(textChoice);
         iterBox = new JComboBox<>();

         iterBox.addItem(0);
         iterBox.addItem(1);
         iterBox.addItem(2);
         iterBox.addItem(3);
         iterBox.addItem(4);
         iterBox.addItem(5);
         iterBox.addItem(6);
         iterBox.addItem(7);
         iterBox.addItem(8);
         iterBox.addItem(9);
         iterBox.addItem(10);
         iterBox.addItem(11);
         iterBox.addItem(12);
         iterBox.addItem(13);
         iterBox.addItem(14);
         iterBox.addItem(15);
         iterBox.addItem(16);
         iterBox.addItem(17);
         iterBox.addItem(18);
         iterBox.addItem(19);
         
    }

    public JComboBox iter() {

        BasicComboBoxRenderer.UIResource UIResource = new BasicComboBoxRenderer.UIResource();
        UIResource.setHorizontalAlignment(SwingConstants.CENTER);

        iterBox.setBounds(720,520, 80,20);
        iterBox.setRenderer(UIResource);
        iterBox.setBackground(color);
        iterBox.setOpaque(true);
        iterBox.setVisible(true);

        return iterBox;
    }


    public ArrayList<JComboBox> menu () {

        BasicComboBoxRenderer.UIResource UIResource = new BasicComboBoxRenderer.UIResource();
        UIResource.setHorizontalAlignment(SwingConstants.CENTER);

        menu.setBounds(785,15,215,20);
        //menu.setHorizontalAlignment(CENTER);
        menu.setRenderer(UIResource);
        menu.setBackground(color);
        menu.setOpaque(true);
        menu.setVisible(true);

        choiceBoxes.setBounds(770,350,215,20);
        //choiceBoxes.setHorizontalAlignment(CENTER);
        choiceBoxes.setRenderer(UIResource);
        choiceBoxes.setBackground(color);
        choiceBoxes.setOpaque(true);
        choiceBoxes.setVisible(true);

        arrayBox.add(menu);
        arrayBox.add(choiceBoxes);

        return arrayBox;
    }

}
