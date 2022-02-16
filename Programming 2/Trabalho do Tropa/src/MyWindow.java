import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Vector;

public class MyWindow extends JFrame {

    protected JFrame f;

    public Color color;

    public ArrayList<JLabel> tempLabel;
    public ArrayList<JTextField> tempText, tempText2, tempText3;
    public ArrayList<JButton> tempButtons;
    public ArrayList<JComboBox> tempMenu, tempMenu2;

    public Compiler compiler;

    public Interpreters interpreterC;

    protected TurtleStatement turtleForward;

    int tempInt, algor;

    LSystems systems;

    public MyWindow(){

        f = new JFrame ();

        color = new Color(240, 240, 240);

        compiler = new Compiler();

        systems = new LSystems();

        tempInt = 0;

        algor = 1;

    }

    public void subFrame () {

        f.setTitle("MacOS is the best OS.");
        f.setSize(1100, 665);
        f.setBackground(color);
        f.setLayout(null);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public void run () {

        this.interpreterC = new Interpreters();

        TextAreas area = new TextAreas();
        Buttons buttons = new Buttons();
        TextFields textFields = new TextFields();
        SubMenu subMenu = new SubMenu();
        Labels newLabel = new Labels();
        JComboBox tempMenu2 = subMenu.iter();

        this.tempMenu = subMenu.menu();
        this.tempButtons = buttons.click();
        this.tempText = textFields.text();
        this.tempText2 = textFields.textfieldC();
        this.tempLabel = newLabel.labels();

        for (int i = 0; i < tempLabel.size(); i++) {

            f.add(tempLabel.get(i));

        }

        for (int i = 0; i < tempText.size(); i++) {

            f.add(tempText.get(i));

        }

        for (int i = 0; i < tempText2.size(); i++) {

            f.add(tempText2.get(i));

        }

        for (int i = 0; i < tempButtons.size(); i++) {

            f.add(tempButtons.get(i));

        }

        for (int i = 0; i < tempMenu.size(); i++) {

            f.add(tempMenu.get(i));

        }

        f.add(tempMenu2);

        tempMenu.get(0).addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {

                area.ret().setText("");

                switch (tempMenu.get(0).getSelectedIndex()) {



                    case 0:



                        for (int i = 0; i < tempText.size(); i++) {


                            tempText.get(i).setEditable(true);
                            tempText.get(i).setText("");

                        }

                        for (int i = 0; i < tempText2.size(); i++) {

                            tempText2.get(i).setText("");
                            tempText2.get(i).setEditable(true);

                        }

                        tempButtons.get(3).setEnabled(true);
                        tempButtons.get(4).setEnabled(true);;
                        tempMenu.get(1).setEnabled(true);


                        break;

                    case 1:


                        for (int i = 0; i < tempText.size(); i++) {

                            tempText.get(i).setText("");
                            tempText.get(i).setEditable(false);

                        }

                        for (int i = 0; i < tempText2.size(); i++) {

                            tempText2.get(i).setText("");
                            tempText2.get(i).setEditable(false);

                        }

                        area.ret().append("Regras de interpretador:\n\n");
                        area.ret().append("F => F+F-F-F+F\n\n");

                        area.ret().append("Regras de Compilador:\n\n");
                        area.ret().append("F => FORWARD 10\n");
                        area.ret().append("+ => TURN 90º\n");
                        area.ret().append("- => TURN -90º\n\n");
                        area.ret().append("Fim das regras de interpretador\n\n");


                        tempButtons.get(5).setEnabled(false);
                        tempButtons.get(3).setEnabled(false);
                        tempButtons.get(4).setEnabled(false);
                        tempMenu.get(1).setEnabled(false);
                        tempMenu.get(1).setEditable(false);

                        break;

                    case 2:


                        for (int i = 0; i < tempText.size(); i++) {

                            tempText.get(i).setText("");
                            tempText.get(i).setEditable(false);

                        }

                        for (int i = 0; i < tempText2.size(); i++) {

                            tempText2.get(i).setText("");
                            tempText2.get(i).setEditable(false);

                        }

                        tempButtons.get(3).setEnabled(false);
                        tempButtons.get(4).setEnabled(false);
                        tempMenu.get(1).setEnabled(false);
                        tempMenu.get(1).setEditable(false);
                        tempButtons.get(5).setEnabled(false);

                        area.ret().append("Regras de interpretador:\n\n");
                        area.ret().append("F => F+F--F+F\n\n");

                        area.ret().append("Regras de Compilador:\n\n");
                        area.ret().append("F => FORWARD 10\n");
                        area.ret().append("+ => TURN 60º\n");
                        area.ret().append("- => TURN -60º\n\n");
                        area.ret().append("Fim das regras de interpretador\n\n");

                        break;

                    case 3:


                        for (int i = 0; i < tempText.size(); i++) {

                            tempText.get(i).setText("");
                            tempText.get(i).setEditable(false);

                        }

                        for (int i = 0; i < tempText2.size(); i++) {

                            tempText2.get(i).setText("");
                            tempText2.get(i).setEditable(false);

                        }

                        tempButtons.get(5).setEnabled(false);
                        tempButtons.get(3).setEnabled(false);
                        tempButtons.get(4).setEnabled(false);
                        tempMenu.get(1).setEnabled(false);
                        tempMenu.get(1).setEditable(false);

                        area.ret().append("Regras de interpretador:\n\n");
                        area.ret().append("F => F-G+F+G-F\n");
                        area.ret().append("G => GG\n\n");


                        area.ret().append("Regras de Compilador:\n\n");
                        area.ret().append("F => FORWARD 10\n");
                        area.ret().append("G => FORWARD 10\n");
                        area.ret().append("+ => TURN 120º\n");
                        area.ret().append("- => TURN -120º\n\n");
                        area.ret().append("Fim das regras de interpretador\n\n");

                        break;

                    case 4:

                        for (int i = 0; i < tempText.size(); i++) {

                            tempText.get(i).setText("");
                            tempText.get(i).setEditable(false);

                        }

                        for (int i = 0; i < tempText2.size(); i++) {

                            tempText2.get(i).setText("");
                            tempText2.get(i).setEditable(false);

                        }

                        tempButtons.get(5).setEnabled(false);
                        tempButtons.get(3).setEnabled(false);
                        tempButtons.get(4).setEnabled(false);
                        tempMenu.get(1).setEnabled(false);
                        tempMenu.get(1).setEditable(false);

                        area.ret().append("Regras de interpretador:\n\n");
                        area.ret().append("A => B-A-B\n");
                        area.ret().append("B => A+B+A\n\n");


                        area.ret().append("Regras de Compilador:\n\n");
                        area.ret().append("A => FORWARD 10\n");
                        area.ret().append("B => FORWARD 10\n");
                        area.ret().append("+ => TURN 60º\n");
                        area.ret().append("- => TURN -60º\n\n");
                        area.ret().append("Fim das regras de interpretador\n\n");

                        break;

                    case 5:

                        for (int i = 0; i < tempText.size(); i++) {

                            tempText.get(i).setText("");
                            tempText.get(i).setEditable(false);

                        }

                        for (int i = 0; i < tempText2.size(); i++) {

                            tempText2.get(i).setText("");
                            tempText2.get(i).setEditable(false);

                        }

                        tempButtons.get(5).setEnabled(false);
                        tempButtons.get(3).setEnabled(false);
                        tempButtons.get(4).setEnabled(false);
                        tempMenu.get(1).setEnabled(false);
                        tempMenu.get(1).setEditable(false);

                        area.ret().append("Regras de interpretador:\n\n");
                        area.ret().append("F => F+H\n");
                        area.ret().append("H => F-H\n\n");


                        area.ret().append("Regras de Compilador:\n\n");
                        area.ret().append("F => FORWARD 10\n");
                        area.ret().append("H => FORWARD 10\n");
                        area.ret().append("+ => TURN 90º\n");
                        area.ret().append("- => TURN -90º\n\n");
                        area.ret().append("Fim das regras de interpretador\n\n");

                        break;

                    case 6:


                        for (int i = 0; i < tempText.size(); i++) {

                            tempText.get(i).setText("");
                            tempText.get(i).setEditable(false);

                        }

                        for (int i = 0; i < tempText2.size(); i++) {

                            tempText2.get(i).setText("");
                            tempText2.get(i).setEditable(false);

                        }

                        tempButtons.get(5).setEnabled(false);
                        tempButtons.get(3).setEnabled(false);
                        tempButtons.get(4).setEnabled(false);
                        tempMenu.get(1).setEnabled(false);
                        tempMenu.get(1).setEditable(false);

                        area.ret().append("Regras de interpretador:\n\n");
                        area.ret().append("F => FLF\n");
                        area.ret().append("L => LLL\n\n");


                        area.ret().append("Regras de Compilador:\n\n");
                        area.ret().append("F => FORWARD 10\n");
                        area.ret().append("L => LEAP 10\n\n");
                        area.ret().append("Fim das regras de interpretador\n\n");

                        break;

                }

            }

        });

        tempButtons.get(3).setEnabled(false);

        JLabel label = new JLabel("Escolha uma opção da caixa de escolhas!");
        f.add(label);

        label.setBounds(740, 420, 315, 40);

        tempMenu.get(1).addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {

                switch (tempMenu.get(1).getSelectedIndex()) {

                    default:

                        tempButtons.get(3).setEnabled(true);
                        tempText2.get(1).setEnabled(true);
                        tempText2.get(1).setEditable(true);
                        label.setVisible(false);
                        tempButtons.get(5).setEnabled(true);

                        break;
                    case 0:

                        tempButtons.get(3).setEnabled(false);
                        label.setVisible(true);

                        break;

                    case 4:

                        tempText2.get(1).setText("");
                        tempText2.get(1).setEnabled(false);
                        tempText2.get(1).setEditable(false);
                        tempButtons.get(3).setEnabled(true);
                        label.setVisible(false);

                        break;

                    case 5:

                        tempText2.get(1).setText("");
                        tempText2.get(1).setEnabled(false);
                        tempText2.get(1).setEditable(false);
                        tempButtons.get(3).setEnabled(true);
                        label.setVisible(false);

                        break;



                }
            }
        });

        tempButtons.get(4).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {

                    for ( int i = 0; i < 3; i++) {

                        if ((tempText.get(i).getText().length() != 0 && tempText.get(i + 3).getText().length() == 0) || (tempText.get(i).getText().length() == 0 && tempText.get(i + 3).getText().length() != 0)) {

                            throw new Exception();

                        }

                    }

                    for ( int i = 0; i < 3; i++) {

                        if (tempText.get(i).getText().length() == 0 && tempText.get(i+3).getText().length() == 0) {

                            area.ret().append("");

                        } else {


                            if (tempText.get(i).getText() == "") {

                                area.ret().append("");


                            } else {

                                String upperCase = tempText.get(i).getText();
                                area.ret().append(upperCase.toUpperCase());
                                systems.addRule(tempText.get(i).getText().toUpperCase().charAt(0), tempText.get(i+3).getText().toUpperCase());

                            }

                            if (tempText.get(i + 3).getText() == "") {

                                area.ret().append(" \n");

                            } else {

                                String upperCase = " => " + tempText.get(i + 3).getText() + "\n";

                                area.ret().append(upperCase.toUpperCase());

                            }

                        }

                    }

                } catch (Exception h) {

                    area.ret().append("\nERRO\n\n");

                }

            }

        });

        ArrayList<Compiler> compileArray = new ArrayList<>();

        tempButtons.get(3).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                    if (tempText2.get(0).getText() == "" || tempText2.get(1).getText() == "") {

                        area.ret().append("");

                    } else {

                        switch (tempMenu.get(1).getSelectedIndex()) {

                            case 0:

                                break;

                            case 1:

                                int s = Integer.parseInt(tempText2.get(1).getText());
                                turtleForward = new Forward(s);
                                compiler.addRule(tempText2.get(0).getText().toUpperCase().charAt(0), turtleForward);

                                break;

                            case 2:

                                int q = Integer.parseInt(tempText2.get(1).getText());
                                turtleForward = new Turn(q);
                                compiler.addRule(tempText2.get(0).getText().toUpperCase().charAt(0), turtleForward);

                                break;

                            case 3:

                                int d = Integer.parseInt(tempText2.get(1).getText());
                                turtleForward = new Leap(d);
                                compiler.addRule(tempText2.get(0).getText().toUpperCase().charAt(0), turtleForward);

                                break;


                            case 4:

                                turtleForward = new PenUp();
                                compiler.addRule(tempText2.get(0).getText().toUpperCase().charAt(0), turtleForward);

                                break;

                            case 5:

                                turtleForward = new PenDown();
                                compiler.addRule(tempText2.get(0).getText().toUpperCase().charAt(0), turtleForward);

                                break;

                        }

                    }

                    if (tempText2.get(1).getText() == "") {

                        area.ret().append(" \n");

                    } else {

                        String upperCase = tempText2.get(0).getText() + " " + tempMenu.get(1).getSelectedItem() + " " + tempText2.get(1).getText() + "\n\n";
                        area.ret().append(upperCase.toUpperCase());

                    }

                }

        });

        //botão Start
        tempButtons.get(2).addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                interpreterC.playground();

                switch (tempMenu.get(0).getSelectedIndex()) {
                    case 0:

                        String wordC = systems.iter(tempMenu2.getSelectedIndex());
                        Vector<TurtleStatement> compiling = compiler.compile(wordC);

                        interpreterC.run(compiling);

                        break;

                    case 1:

                        systems.setStart("F");
                        systems.addRule('F', "F+F-F-F+F");

                        compiler.addRule('F', new Forward(10));
                        compiler.addRule('+', new Turn(90));
                        compiler.addRule('-', new Turn(-90));

                                switch (tempMenu2.getSelectedIndex()) {

                                    case 2:

                                        interpreterC.getPlayground().setUnit(1.4);
                                        interpreterC.getPlayground().setOrigin(50,0);

                                        break;

                                    case 3:

                                        interpreterC.getPlayground().setUnit(0.9);
                                        interpreterC.getPlayground().setOrigin(130,50);

                                        break;

                                    case 4:

                                        interpreterC.getPlayground().setUnit(0.7);
                                        interpreterC.getPlayground().setOrigin(400,100);

                                        break;

                                    case 5:

                                        interpreterC.getPlayground().setUnit(0.28);
                                        interpreterC.getPlayground().setOrigin(1200,600);


                                        break;

                                    case 6:

                                        interpreterC.getPlayground().setUnit(0.093);
                                        interpreterC.getPlayground().setOrigin(3600,2000);

                                        break;

                                    case 7:

                                        interpreterC.getPlayground().setUnit(0.03);
                                        interpreterC.getPlayground().setOrigin(11000,6000);

                                        break;

                                    case 8:

                                        interpreterC.getPlayground().setUnit(0.01);
                                        interpreterC.getPlayground().setOrigin(33000,12000);

                                        break;

                                    default:

                                        interpreterC.getPlayground().setUnit(2.2);

                                        break;


                                }

                        break;

                    case 2:


                        systems.setStart("+F--F--F");
                        systems.addRule('F', "F+F--F+F");

                        compiler.addRule('F', new Forward(10));
                        compiler.addRule('+', new Turn(60));
                        compiler.addRule('-', new Turn(-60));

                        switch (tempMenu2.getSelectedIndex()) {

                            case 2:

                                interpreterC.getPlayground().setUnit(1.4);
                                interpreterC.getPlayground().setOrigin(50, 0);

                                break;

                            case 3:

                                interpreterC.getPlayground().setUnit(0.9);
                                interpreterC.getPlayground().setOrigin(100, 50);

                                break;

                            case 4:

                                interpreterC.getPlayground().setUnit(0.4);
                                interpreterC.getPlayground().setOrigin(400, 200);

                                break;

                            case 5:

                                interpreterC.getPlayground().setUnit(0.15);
                                interpreterC.getPlayground().setOrigin(1200, 600);


                                break;

                            case 6:

                                interpreterC.getPlayground().setUnit(0.05);
                                interpreterC.getPlayground().setOrigin(3600, 1800);

                                break;

                            case 7:

                                interpreterC.getPlayground().setUnit(0.017);
                                interpreterC.getPlayground().setOrigin(10800, 5400);

                                break;

                            case 8:

                                interpreterC.getPlayground().setUnit(0.005);
                                interpreterC.getPlayground().setOrigin(32400, 16200);

                                break;

                            default:

                                interpreterC.getPlayground().setUnit(2.2);

                                break;

                        }

                        break;

                    case 3:

                        systems.setStart("F-G-G");
                        systems.addRule('F', "F-G+F+G-F");
                        systems.addRule('G', "GG");

                        compiler.addRule('F', new Forward(10));
                        compiler.addRule('G', new Forward(10));
                        compiler.addRule('+', new Turn(120));
                        compiler.addRule('-', new Turn(-120));

                        switch (tempMenu2.getSelectedIndex()) {

                            case 1:

                                interpreterC.getPlayground().setUnit(5);
                                interpreterC.getPlayground().setOrigin(0, 0);

                                break;

                            case 2:

                                interpreterC.getPlayground().setUnit(4);
                                interpreterC.getPlayground().setOrigin(20, 0);

                                break;

                            case 3:

                                interpreterC.getPlayground().setUnit(3);
                                interpreterC.getPlayground().setOrigin(40, -20);

                                break;

                            case 4:

                                interpreterC.getPlayground().setUnit(2);
                                interpreterC.getPlayground().setOrigin(80, -60);

                                break;

                            case 5:

                                interpreterC.getPlayground().setUnit(1.05);
                                interpreterC.getPlayground().setOrigin(150, -130);


                                break;

                            case 6:

                                interpreterC.getPlayground().setUnit(0.75);
                                interpreterC.getPlayground().setOrigin(300, -270);

                                break;

                            case 7:

                                interpreterC.getPlayground().setUnit(0.35);
                                interpreterC.getPlayground().setOrigin(600, -550);

                                break;

                            case 8:

                                interpreterC.getPlayground().setUnit(0.175);
                                interpreterC.getPlayground().setOrigin(1200, -1110);

                                break;

                            case 9:

                                interpreterC.getPlayground().setUnit(0.0875);
                                interpreterC.getPlayground().setOrigin(2400, -2230);

                                break;

                            case 10:

                                interpreterC.getPlayground().setUnit(0.0435);
                                interpreterC.getPlayground().setOrigin(4800, -4470);

                                break;

                            case 11:

                                interpreterC.getPlayground().setUnit(0.021875);
                                interpreterC.getPlayground().setOrigin(9600, -8950);

                                break;

                            case 12:

                                interpreterC.getPlayground().setUnit(0.0109375);
                                interpreterC.getPlayground().setOrigin(19200, -15900);

                                break;


                            default:

                                interpreterC.getPlayground().setUnit(6);

                                break;

                        }


                        break;

                    case 4:

                        systems.setStart("A");
                        systems.addRule('A', "B-A-B");
                        systems.addRule('B', "A+B+A");

                        compiler.addRule('A', new Forward(10));
                        compiler.addRule('B', new Forward(10));
                        compiler.addRule('+', new Turn(60));
                        compiler.addRule('-', new Turn(-60));

                        switch (tempMenu2.getSelectedIndex()) {

                            case 1:

                                interpreterC.getPlayground().setUnit(5);
                                interpreterC.getPlayground().setOrigin(0, 0);

                                break;

                            case 2:

                                interpreterC.getPlayground().setUnit(4);
                                interpreterC.getPlayground().setOrigin(20, 0);

                                break;

                            case 3:

                                interpreterC.getPlayground().setUnit(3);
                                interpreterC.getPlayground().setOrigin(40, -30);

                                break;

                            case 4:

                                interpreterC.getPlayground().setUnit(2);
                                interpreterC.getPlayground().setOrigin(80, 60);

                                break;

                            case 5:

                                interpreterC.getPlayground().setUnit(1.05);
                                interpreterC.getPlayground().setOrigin(160, -140);


                                break;

                            case 6:

                                interpreterC.getPlayground().setUnit(0.75);
                                interpreterC.getPlayground().setOrigin(320, 280);

                                break;

                            case 7:

                                interpreterC.getPlayground().setUnit(0.35);
                                interpreterC.getPlayground().setOrigin(640, -560);

                                break;

                            case 8:

                                interpreterC.getPlayground().setUnit(0.175);
                                interpreterC.getPlayground().setOrigin(1280, 1120);

                                break;

                            case 9:

                                interpreterC.getPlayground().setUnit(0.0875);
                                interpreterC.getPlayground().setOrigin(2560, -2240);

                                break;

                            case 10:

                                interpreterC.getPlayground().setUnit(0.0435);
                                interpreterC.getPlayground().setOrigin(5120, 4480);

                                break;

                            case 11:

                                interpreterC.getPlayground().setUnit(0.021875);
                                interpreterC.getPlayground().setOrigin(9600, -8950);

                                break;

                            case 12:

                                interpreterC.getPlayground().setUnit(0.0109375);
                                interpreterC.getPlayground().setOrigin(19200, -15900);

                                break;


                            default:

                                interpreterC.getPlayground().setUnit(6);

                                break;

                        }

                        break;

                    case 5:

                        systems.setStart("F");
                        systems.addRule('F', "F+G");
                        systems.addRule('G', "F-G");

                        compiler.addRule('F', new Forward(10));
                        compiler.addRule('G', new Forward(10));
                        compiler.addRule('+', new Turn(90));
                        compiler.addRule('-', new Turn(-90));

                        switch (tempMenu2.getSelectedIndex()) {

                            case 1:

                                interpreterC.getPlayground().setUnit(5);
                                interpreterC.getPlayground().setOrigin(0, 0);

                                break;

                            case 2:

                                interpreterC.getPlayground().setUnit(5);
                                interpreterC.getPlayground().setOrigin(0, 0);

                                break;

                            case 3:

                                interpreterC.getPlayground().setUnit(4);
                                interpreterC.getPlayground().setOrigin(0, 0);

                                break;

                            case 4:

                                interpreterC.getPlayground().setUnit(4);
                                interpreterC.getPlayground().setOrigin(0, 0);

                                break;

                            case 5:

                                interpreterC.getPlayground().setUnit(3.5);
                                interpreterC.getPlayground().setOrigin(0, 0);


                                break;

                            case 6:

                                interpreterC.getPlayground().setUnit(3);
                                interpreterC.getPlayground().setOrigin(0, -30);

                                break;

                            case 7:

                                interpreterC.getPlayground().setUnit(3);
                                interpreterC.getPlayground().setOrigin(0, -30);

                                break;

                            case 8:

                                interpreterC.getPlayground().setUnit(2);
                                interpreterC.getPlayground().setOrigin(40, -40);

                                break;

                            case 9:

                                interpreterC.getPlayground().setUnit(1.3);
                                interpreterC.getPlayground().setOrigin(60, 20);

                                break;

                            case 10:

                                interpreterC.getPlayground().setUnit(0.9);
                                interpreterC.getPlayground().setOrigin(60, 120);

                                break;

                            case 11:

                                interpreterC.getPlayground().setUnit(0.75);
                                interpreterC.getPlayground().setOrigin(0, 140);

                                break;

                            case 12:

                                interpreterC.getPlayground().setUnit(0.6);
                                interpreterC.getPlayground().setOrigin(-200, 140);

                                break;

                            case 13:

                                interpreterC.getPlayground().setUnit(0.35);
                                interpreterC.getPlayground().setOrigin(-350, -200);

                                break;

                            case 14:

                                interpreterC.getPlayground().setUnit(0.2);
                                interpreterC.getPlayground().setOrigin(-350, -500);

                                break;

                            case 15:

                                interpreterC.getPlayground().setUnit(0.2);
                                interpreterC.getPlayground().setOrigin(20, -650);

                                break;

                            case 16:

                                interpreterC.getPlayground().setUnit(0.15);
                                interpreterC.getPlayground().setOrigin(1000, -550);

                                break;

                            case 17:

                                interpreterC.getPlayground().setUnit(0.08);
                                interpreterC.getPlayground().setOrigin(1500, 800);

                                break;

                            case 18:

                                interpreterC.getPlayground().setUnit(0.055);
                                interpreterC.getPlayground().setOrigin(1500, 2000);

                                break;

                            case 19:

                                interpreterC.getPlayground().setUnit(0.04);
                                interpreterC.getPlayground().setOrigin(0, 2600);

                                break;

                            default:

                                interpreterC.getPlayground().setUnit(6);

                                break;

                        }

                        break;

                    case 6:

                        systems.setStart("F");
                        systems.addRule('F', "FLF");
                        systems.addRule('L', "LLL");

                        compiler.addRule('F', new Forward(10));
                        compiler.addRule('L', new Leap(10));
                        compiler.addRule('+', new Turn(90));
                        compiler.addRule('-', new Turn(-90));

                        break;


                }


                String word = systems.iter(tempMenu2.getSelectedIndex());
                Vector<TurtleStatement> program = compiler.compile(word);

                interpreterC.run(program);
                interpreterC.getPlayground().setVisible(true);



            }
        });

        tempButtons.get(5).addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                if (tempText.get(6).getText() == "") {

                    area.ret().append("\nERRO\n\n");

                } else {

                    systems.setStart(tempText.get(6).getText().toUpperCase());
                    area.ret().append("\nLetra inicial: " + tempText.get(6).getText().toUpperCase() + "\n");

                }

            }

        });

        tempButtons.get(6).addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                area.ret().setText("");

            }
        });


        f.add(area.ret());

        subFrame();

    }

}




