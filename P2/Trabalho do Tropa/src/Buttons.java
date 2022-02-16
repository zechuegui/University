import javax.swing.*;
import java.util.ArrayList;

import static java.awt.Color.black;

public class Buttons extends MyWindow {

    ArrayList<JButton> buttonsArray;

    final JButton b1, b2, startButton, addRule, addRule1, define, clear;

    public Buttons (){

        b1 = new JButton("", new ImageIcon(""));
        b2 = new JButton("", new ImageIcon(""));
        addRule1 = new JButton("Adicionar regra(s)");
        addRule = new JButton("Adicionar regra");
        define = new JButton("Definir");

        clear = new JButton("APAGAR");

        startButton = new JButton("START");

        buttonsArray = new ArrayList<>();

    }

    public ArrayList<JButton> click () {

        //b1.setBorder(BorderFactory.createLineBorder(black,1));
        //b1.setBounds(270, 550, 40, 40);
        //b2.setBorder(BorderFactory.createLineBorder(black,1));
        //b2.setBounds(330, 550, 40, 40);


        startButton.setVisible(true);
        startButton.setBounds(1000, 600, 80, 20);
        //startButton.setBorder(BorderFactory.createLineBorder(black,1));
        startButton.setBackground(color);

        addRule.setVisible(true);
        addRule.setBackground(color);
        addRule.setBounds(720, 390, 315, 40);

        addRule1.setVisible(true);
        addRule1.setBackground(color);
        addRule1.setBounds(785, 190, 215, 40);

        define.setBounds(910,520,80,20);
        define.setBackground(color);
        define.setVisible(true);

        clear.setBackground(color);
        clear.setVisible(true);
        clear.setBounds(880, 600, 100, 20);


        buttonsArray.add(b1);
        buttonsArray.add(b2);
        buttonsArray.add(startButton);
        buttonsArray.add(addRule);
        buttonsArray.add(addRule1);
        buttonsArray.add(define);
        buttonsArray.add(clear);



        return buttonsArray;

    }

}
