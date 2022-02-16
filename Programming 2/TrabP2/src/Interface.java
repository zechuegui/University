import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;
import java.lang.*;

public class Interface {

    Compiler compiler = new Compiler();
    Vector<TurtleStatement> program;
    Interpreter jesus = new InterpreterClass();

    String[] lsysChoiceString = {"Novo","Koch Curve", "Sierpinski Triangle", "Koch Snowflake", "Sierpinski Arrowhead", "Dragon Curve", "Cantor Set"};
    String[] iterChoiceString = {"0","1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15"};
    String[] compChoiceString = {"","Forward","Turn","PenUp","PenDown","Leap"};
    String[] sinalS = {"+","-"};
    String word;
    String d = "distancia";
    String a = "angulo";
    String arrow = "------->";

    JTextField addRuleSymbol1 = new JTextField();
    JTextField addRuleSymbol2 = new JTextField();
    JTextField addRuleSymbol3 = new JTextField();
    JTextField addRuleSymbol4 = new JTextField();

    JTextField startText = new JTextField();

    JTextField addRuleWord1 = new JTextField();
    JTextField addRuleWord2 = new JTextField();
    JTextField addRuleWord3 = new JTextField();
    JTextField addRuleWord4 = new JTextField();

    JComboBox compChoice1 = new JComboBox(compChoiceString);
    JComboBox compChoice2 = new JComboBox(compChoiceString);
    JComboBox compChoice3 = new JComboBox(compChoiceString);
    JComboBox compChoice4 = new JComboBox(compChoiceString);
    JComboBox compChoice5 = new JComboBox(compChoiceString);
    JComboBox compChoice6 = new JComboBox(compChoiceString);
    JComboBox compChoice7 = new JComboBox(compChoiceString);
    JComboBox compChoice8 = new JComboBox(compChoiceString);

    JTextField compSymb1 = new JTextField();
    JTextField compSymb2 = new JTextField();
    JTextField compSymb3 = new JTextField();
    JTextField compSymb4 = new JTextField();
    JTextField compSymb5 = new JTextField();
    JTextField compSymb6 = new JTextField();
    JTextField compSymb7 = new JTextField();
    JTextField compSymb8 = new JTextField();

    JComboBox iterChoice = new JComboBox(iterChoiceString);

    JTextField insereDouble1 = new JTextField();
    JTextField insereDouble2 = new JTextField();
    JTextField insereDouble3 = new JTextField();
    JTextField insereDouble4 = new JTextField();
    JTextField insereDouble5 = new JTextField();
    JTextField insereDouble6 = new JTextField();
    JTextField insereDouble7 = new JTextField();
    JTextField insereDouble8 = new JTextField();

    JTextField insereDegree1 = new JTextField();
    JTextField insereDegree2 = new JTextField();
    JTextField insereDegree3 = new JTextField();
    JTextField insereDegree4 = new JTextField();
    JTextField insereDegree5 = new JTextField();
    JTextField insereDegree6 = new JTextField();
    JTextField insereDegree7 = new JTextField();
    JTextField insereDegree8 = new JTextField();

    JLabel doubleLabel1 = new JLabel(d);
    JLabel doubleLabel2 = new JLabel(d);
    JLabel doubleLabel3 = new JLabel(d);
    JLabel doubleLabel4 = new JLabel(d);
    JLabel doubleLabel5 = new JLabel(d);
    JLabel doubleLabel6 = new JLabel(d);
    JLabel doubleLabel7 = new JLabel(d);
    JLabel doubleLabel8 = new JLabel(d);

    JLabel degree1 = new JLabel(a);
    JLabel degree2 = new JLabel(a);
    JLabel degree3 = new JLabel(a);
    JLabel degree4 = new JLabel(a);
    JLabel degree5 = new JLabel(a);
    JLabel degree6 = new JLabel(a);
    JLabel degree7 = new JLabel(a);
    JLabel degree8 = new JLabel(a);

    JComboBox sinal1 = new JComboBox(sinalS);
    JComboBox sinal2 = new JComboBox(sinalS);
    JComboBox sinal3 = new JComboBox(sinalS);
    JComboBox sinal4 = new JComboBox(sinalS);
    JComboBox sinal5 = new JComboBox(sinalS);
    JComboBox sinal6 = new JComboBox(sinalS);
    JComboBox sinal7 = new JComboBox(sinalS);
    JComboBox sinal8 = new JComboBox(sinalS);

    JLabel arrowLabel1 = new JLabel(arrow);
    JLabel arrowLabel2 = new JLabel(arrow);
    JLabel arrowLabel3  = new JLabel(arrow);
    JLabel arrowLabel4 = new JLabel(arrow);

    TurtleStatement turtleCommand;
    JFrame error = new JFrame();
    JButton errorButton;

    public void run() {

        ArrayList<JTextField> addRuleSymbList = new ArrayList<>(Arrays.asList(addRuleSymbol1,addRuleSymbol2,addRuleSymbol3,addRuleSymbol4));
        ArrayList<JTextField> compSymbList = new ArrayList<>(Arrays.asList(compSymb1,compSymb2,compSymb3,compSymb4,compSymb5,compSymb6,compSymb7,compSymb8));
        ArrayList<JTextField> insereDoubleList = new ArrayList<>(Arrays.asList(insereDouble1,insereDouble2,insereDouble3,insereDouble4,insereDouble5,insereDouble6,insereDouble7,insereDouble8));
        ArrayList<JTextField> insereDegreeList = new ArrayList<>(Arrays.asList(insereDegree1,insereDegree2,insereDegree3,insereDegree4,insereDegree5,insereDegree6,insereDegree7,insereDegree8));
        ArrayList<JTextField> addRuleWordList = new ArrayList<>(Arrays.asList(addRuleWord1,addRuleWord2,addRuleWord3,addRuleWord4));

        ArrayList<JComboBox> compBoxList = new ArrayList<>(Arrays.asList(compChoice1, compChoice2, compChoice3, compChoice4, compChoice5, compChoice6, compChoice7,compChoice8));
        ArrayList<JComboBox> sinalList = new ArrayList<>(Arrays.asList(sinal1,sinal2,sinal3,sinal4,sinal5,sinal6,sinal7,sinal8));

        ArrayList<JLabel> arrowList = new ArrayList<>(Arrays.asList(arrowLabel1,arrowLabel2,arrowLabel3,arrowLabel4));
        ArrayList<JLabel> doubleList = new ArrayList<>(Arrays.asList(doubleLabel1,doubleLabel2,doubleLabel3,doubleLabel4,doubleLabel5,doubleLabel6,doubleLabel7,doubleLabel8));
        ArrayList<JLabel> degreeList = new ArrayList<>(Arrays.asList(degree1,degree2,degree3,degree4,degree5,degree6,degree7,degree8));


        JFrame frame = new JFrame("Aprendizations");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);       //Main Frame
        frame.setSize(1100, 665);
        frame.setLayout(null);
        frame.setResizable(false);

        error.setSize(300,200);
        error.setBounds(550,330,300,200);           //Error Frame
        error.setLayout(null);

        JLabel errorMessage = new JLabel("ERRO");
        errorMessage.setBounds(20,10,50,50);        //Error Message Label
        errorMessage.setVisible(true);
        error.add(errorMessage);

        errorButton = new JButton("continue");
        errorButton.setBounds(100,100,100,20);      //Error Button
        errorButton.setVisible(true);
        error.add(errorButton);

        JButton startButton = new JButton("START");
        startButton.setVisible(true);                                       //Start Button
        startButton.setBounds(1000, 615, 80, 20);
        frame.add(startButton);

        JLabel lsysLabel = new JLabel("L-System");
        lsysLabel.setVisible(true);                                        //L-System Label
        lsysLabel.setBounds(700, 20, 80, 20);
        frame.add(lsysLabel);

        JComboBox lsysChoice = new JComboBox(lsysChoiceString);
        lsysChoice.setVisible(true);                                        //L-System Combo Box
        lsysChoice.setBounds(850, 20, 190, 20);
        frame.add(lsysChoice);

        JLabel iterLabel = new JLabel("Nº Iteraçoes");
        iterLabel.setVisible(true);
        iterLabel.setBounds(700, 50, 150, 20);          //Iter Label
        frame.add(iterLabel);

        iterChoice = new JComboBox(iterChoiceString);
        iterChoice.setVisible(true);
        iterChoice.setBounds(850, 50, 190, 20);         //Iter CheckBox
        frame.add(iterChoice);

        JLabel startLabel = new JLabel("Palavra Inicial");
        startLabel.setVisible(true);
        startLabel.setBounds(700,80,150,20);            //Start Label
        frame.add(startLabel);

        startText = new JTextField();
        startText.setVisible(true);
        startText.setBounds(850,80,100,20);             //Start Text Field
        frame.add(startText);

        JLabel rulesLabel = new JLabel("Adicionar Regras:");
        rulesLabel.setVisible(true);                                        //Rules Label
        rulesLabel.setBounds(700, 110, 190, 20);
        frame.add(rulesLabel);

        int y=140;
        for(int i=0; i<addRuleSymbList.size(); i++){
            
            addRuleSymbList.get(i).setVisible(true);
            addRuleSymbList.get(i).setBounds(750,y,30,20);           //CRIAR TEXTFILES DO ADD RULE SYMBOL
            frame.add(addRuleSymbList.get(i));

            addRuleWordList.get(i).setVisible(true);
            addRuleWordList.get(i).setBounds(850,y,150,20);         //CRIAR TEXTFILE ADD RULE WORD
            frame.add(addRuleWordList.get(i));

            arrowList.get(i).setVisible(true);
            arrowList.get(i).setBounds(780,y,70,20);                //CRIAR SETAS DO ADD RULE
            frame.add(arrowList.get(i));

            y+=30;
        }

        JLabel legenda = new JLabel("<html><br>Adiconar uma letra (Maiuscula), depois adicionar<br>palavra para substituir.</html>");
        legenda.setVisible(true);
        legenda.setBounds(700, 200, 400, 150);                  //Explanation Label
        frame.add(legenda);

        JLabel exemple = new JLabel("Ex: F --> F+F-F+G");
        exemple.setVisible(true);                                                   //Exemple Label
        exemple.setBounds(790,310,150,20);
        frame.add(exemple);

        JLabel rulesComp = new JLabel("Regras do Compilador:");
        rulesComp.setVisible(true);                                                 //Compiler Rules Label
        rulesComp.setBounds(700,340,150,20);
        frame.add(rulesComp);

        y=370;
        for(int i=0; i<compSymbList.size(); i++){

            compSymbList.get(i).setVisible(true);
            compSymbList.get(i).setBounds(700,y,30,20);     //ADICIONAR AS TEXT FILES COMPILADOR SYMBOL
            frame.add(compSymbList.get(i));

            compBoxList.get(i).setVisible(true);
            compBoxList.get(i).setBounds(740,y,120,20);     //ADICIONA AS COMBO BOXES PARA O COMPILER
            frame.add(compBoxList.get(i));

            compBoxList.get(i).setVisible(true);
            compBoxList.get(i).setBounds(740,y,120,20);     //ADICIONA AS COMBO BOXES PARA O COMPILER
            frame.add(compBoxList.get(i));

            insereDoubleList.get(i).setVisible(false);
            insereDoubleList.get(i).setBounds(860,y,50,20); //Adiciona as Text Files para o valor da distancia
            frame.add(insereDoubleList.get(i));

            doubleList.get(i).setVisible(false);
            doubleList.get(i).setBounds(920,y,100,20);      //Adiciona as Labels distancia
            frame.add(doubleList.get(i));

            sinalList.get(i).setVisible(false);
            sinalList.get(i).setBounds(860,y,60,20);        //Adiciona as ComboBoxes dos sinais
            frame.add(sinalList.get(i));

            insereDegreeList.get(i).setVisible(false);
            insereDegreeList.get(i).setBounds(930,y,50,20); //Adiciona as TextFiles para o valor do angulo
            frame.add(insereDegreeList.get(i));

            degreeList.get(i).setVisible(false);
            degreeList.get(i).setBounds(990,y,100,20);      //Adiciona as Labels angulo
            frame.add(degreeList.get(i));

            y+=30;
        }

        lsysChoice.addItemListener(new ItemListener() {             //Quando o L-System ChoiceBox é alterado
            @Override
            public void itemStateChanged(ItemEvent e) {

                reset(addRuleSymbList,addRuleWordList,startText,compBoxList,compSymbList,insereDoubleList,insereDegreeList,sinalList);
                compSymb1.setText("F");
                compChoice1.setSelectedIndex(1);
                insereDouble1.setText("10");

                switch(lsysChoice.getSelectedIndex()){              //Mudar as TextFiles das Regras E Compiler Consoante o L-System

                    case 0:

                        reset(addRuleSymbList,addRuleWordList,startText,compBoxList,compSymbList,insereDoubleList,insereDegreeList,sinalList);
                        break;

                    case 1://Koch Curve

                        startText.setText("F");

                        addRuleSymbol1.setText("F");
                        addRuleWord1.setText("F+F-F-F+F");

                        compSymb2.setText("+");
                        compChoice2.setSelectedIndex(2);
                        compSymb3.setText("-");
                        compChoice3.setSelectedIndex(2);

                        insereDegree2.setText("90");
                        sinal2.setSelectedIndex(0);
                        insereDegree3.setText("90");
                        sinal3.setSelectedIndex(1);

                        break;
                    case 2://Sierpinski Triangle

                        startText.setText("F-G-G");

                        addRuleSymbol2.setText("F");
                        addRuleWord2.setText("F-G+F+G-F");
                        addRuleSymbol1.setText("G");
                        addRuleWord1.setText("GG");

                        compSymb2.setText("G");
                        compChoice2.setSelectedIndex(1);
                        compSymb3.setText("+");
                        compChoice3.setSelectedIndex(2);
                        compSymb4.setText("-");
                        compChoice4.setSelectedIndex(2);

                        insereDouble2.setText("10");
                        insereDegree3.setText("120");
                        sinal3.setSelectedIndex(0);
                        insereDegree4.setText("120");
                        sinal4.setSelectedIndex(1);

                        break;
                    case 3://Koch Snowflake

                        startText.setText("+F--F--F");

                        addRuleSymbol1.setText("F");
                        addRuleWord1.setText("F+F--F+F");

                        compSymb2.setText("+");
                        compChoice2.setSelectedIndex(2);
                        compSymb3.setText("-");
                        compChoice3.setSelectedIndex(2);

                        insereDegree2.setText("60");
                        sinal2.setSelectedIndex(0);
                        insereDegree3.setText("60");
                        sinal3.setSelectedIndex(1);

                        break;
                    case 4://Sierpinski Arrowhead

                        startText.setText("F");

                        addRuleSymbol1.setText("F");
                        addRuleWord1.setText("G-F-G");
                        addRuleSymbol2.setText("G");
                        addRuleWord2.setText("F+G+F");

                        compSymb2.setText("G");
                        compChoice2.setSelectedIndex(1);
                        compSymb3.setText("+");
                        compChoice3.setSelectedIndex(2);
                        compSymb4.setText("-");
                        compChoice4.setSelectedIndex(2);

                        insereDouble2.setText("10");
                        insereDegree3.setText("60");
                        sinal3.setSelectedIndex(0);
                        insereDegree4.setText("60");
                        sinal4.setSelectedIndex(1);

                        break;
                    case 5://Dragon Curve

                        startText.setText("F");

                        addRuleSymbol1.setText("F");
                        addRuleWord1.setText("F+G");
                        addRuleSymbol2.setText("G");
                        addRuleWord2.setText("F-G");

                        compSymb2.setText("G");
                        compChoice2.setSelectedIndex(1);
                        compSymb3.setText("+");
                        compChoice3.setSelectedIndex(2);
                        compSymb4.setText("-");
                        compChoice4.setSelectedIndex(2);

                        insereDouble2.setText("10");
                        insereDegree3.setText("90");
                        sinal3.setSelectedIndex(0);
                        insereDegree4.setText("90");
                        sinal4.setSelectedIndex(1);

                        break;
                    case 6://Cantor Set

                        startText.setText("F");

                        addRuleSymbol1.setText("F");
                        addRuleWord1.setText("FLF");
                        addRuleSymbol2.setText("L");
                        addRuleWord2.setText("LLL");

                        compSymb2.setText("L");
                        compChoice2.setSelectedIndex(5);

                        insereDouble2.setText("10");

                        break;
                }
            }
        });

        hideOrshow(compBoxList,insereDoubleList,doubleList,sinalList,insereDegreeList,degreeList);  //Esconder e Mostrar as Labels, TextFiles e ComboBoxes do Compiler Rules

        startButton.addActionListener(new ActionListener() {            //Quando o Button Start é pressionado
            @Override
            public void actionPerformed(ActionEvent e) {

                try{
                            LSystem lsys = new LSystemClass();
                            lsys.setStart(startText.getText());

                            int k;
                            for(int x=0; x<addRuleSymbList.size()-1;x++){
                                k=x+1;
                                while (k<addRuleSymbList.size()){
                                    if (addRuleSymbList.get(x).getText().length()>0){
                                        if(addRuleSymbList.get(x).getText().equals(addRuleSymbList.get(k).getText())){          //VER SE DUAS REGRAS TEEM O MESMO SIMBOLO
                                            throw new Exception();

                                        }

                                    }

                                    k++;
                                }
                            }

                            for(int i=0; i<addRuleSymbList.size();i++){

                                if (addRuleSymbList.get(i).getText().length()>0 && addRuleWordList.get(i).getText().length()>0){            //VERIFICAR SE ESTA VAZIO

                                    lsys.addRule(addRuleSymbList.get(i).getText().charAt(0),addRuleWordList.get(i).getText().toLowerCase());
                                }

                            }
                            word = lsys.iter(iterChoice.getSelectedIndex());

                            for (int i =0; i<compBoxList.size(); i++){

                                if(compSymbList.get(i).getText().length()>0){           //Adicionar as Regras ao Compiler

                                    switch(compBoxList.get(i).getSelectedIndex()){
                                        case 0:
                                            break;
                                        case 1:

                                            turtleCommand = new Forward(Double.parseDouble(insereDoubleList.get(i).getText()));
                                            compiler.addRule(compSymbList.get(i).getText().charAt(0), turtleCommand);
                                            break;
                                        case 2:

                                            int x = Integer.parseInt(insereDegreeList.get(i).getText());

                                            if(sinalList.get(i).getSelectedIndex()==1){
                                                x = 0 - x;
                                            }
                                            turtleCommand = new Turn(x);
                                            compiler.addRule(compSymbList.get(i).getText().charAt(0), turtleCommand);
                                            break;
                                        case 3:

                                            turtleCommand = new PenUp();
                                            compiler.addRule(compSymbList.get(i).getText().charAt(0), turtleCommand);
                                            break;
                                        case 4:

                                            turtleCommand = new PenDown();
                                            compiler.addRule(compSymbList.get(i).getText().charAt(0), turtleCommand);
                                            break;
                                        case 5:

                                            turtleCommand = new Leap(Double.parseDouble(insereDoubleList.get(i).getText()));
                                            compiler.addRule(compSymbList.get(i).getText().charAt(0), turtleCommand);
                                            break;
                                    }
                                }
                            }

                    program = compiler.compile(word);
                    jesus.runTurtle();
                    jesus.run(program);
                }
                catch (Exception h){
                    error.setVisible(true);
                }
            }
        });
        frame.setVisible(true);

        errorButton.addActionListener(new ActionListener() {       //Esconder Frame Error Quando o Button é pressionado
            @Override
            public void actionPerformed(ActionEvent e) {
                error.setVisible(false);
            }
        });

    }
     void reset(ArrayList<JTextField> addRuleSymbolList, ArrayList<JTextField> addRuleWordList, JTextField startText, ArrayList<JComboBox>
             compBoxList, ArrayList<JTextField> compSymb, ArrayList<JTextField> insereDouble, ArrayList<JTextField> insereDegree, ArrayList<JComboBox> sinalList){

        startText.setText("");                                                                  //Todos os componentes da JFrame colocados a default

         for(int i=0; i<compBoxList.size();i++){

             if(i<4) {
                 addRuleSymbolList.get(i).setText("");
                 addRuleWordList.get(i).setText("");
             }
                 sinalList.get(i).setSelectedIndex(0);
                 compBoxList.get(i).setSelectedIndex(0);

                 compSymb.get(i).setText("");
                 insereDouble.get(i).setText("");
                 insereDegree.get(i).setText("");

         }
    }
    public void hideOrshow(ArrayList<JComboBox> compBoxList ,ArrayList<JTextField> insereDoubleList, ArrayList<JLabel> doublelist,
                           ArrayList<JComboBox> sinalList, ArrayList<JTextField> insereDegreeList, ArrayList<JLabel> degreeList){

        for(int i=0; i<insereDegreeList.size(); i++){
            int x=i;
            compBoxList.get(x).addActionListener(new ActionListener() {           //Choice Box do Compilador é alterada
                @Override
                public void actionPerformed(ActionEvent e) {

                    switch (compBoxList.get(x).getSelectedIndex()) {

                        case 1:
                            insereDoubleList.get(x).setVisible(true);
                            doublelist.get(x).setVisible(true);
                            sinalList.get(x).setVisible(false);
                            insereDegreeList.get(x).setVisible(false);
                            degreeList.get(x).setVisible(false);

                            break;
                        case 2:
                            insereDoubleList.get(x).setVisible(false);
                            doublelist.get(x).setVisible(false);
                            sinalList.get(x).setVisible(true);
                            insereDegreeList.get(x).setVisible(true);
                            degreeList.get(x).setVisible(true  );

                            break;
                        case 5:
                            insereDoubleList.get(x).setVisible(true);
                            doublelist.get(x).setVisible(true);
                            sinalList.get(x).setVisible(false);
                            insereDegreeList.get(x).setVisible(false);
                            degreeList.get(x).setVisible(false);

                            break;
                        default:
                            insereDoubleList.get(x).setVisible(false);
                            doublelist.get(x).setVisible(false);
                            sinalList.get(x).setVisible(false);
                            insereDegreeList.get(x).setVisible(false);
                            degreeList.get(x).setVisible(false);

                            break;
                    }
                }
            });
        }
    }
}
/**TODO ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
 *
 * //Set Canvas
 *
 * //INTERFACE NAO PASSAR COMO STRIN MAS SIM COMO OBJECTO? (ACHO QUE AS COMBO BOXES NAO RECEBEM STRINGS)
 *
 * //AUTO COMPLETE TURTLE STATEMENTS (UM ACTION LISTENER LE O INPUT QUANDO SÓ HOUVER 1 POSSIVEL SUBSTITUIR AUTOMATICAMENTE ????)
 *
 *
 * //MANEIRA DE FAZER SAVE E RESTORE, GUARDAR OS FORWARDS E TURNS e Inverter (+) --> (-) e (Forward) --> (Backup).
 * // ^
 * // |_ é preciso ver Saves e Restores complementares [F+[F]]
 *
 *
 *
 */