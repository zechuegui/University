import javax.swing.*;
import java.util.ArrayList;

public class Labels extends MyWindow {

    ArrayList<JLabel> labelArray;

    protected JLabel s1, s2, s3, s4, s5, l6, l7, l8, l9, l10, l11, l12, l13;

    public Labels () {

        labelArray = new ArrayList<>();

        s1 = new JLabel("=>");
        s2 = new JLabel("=>");
        s3 = new JLabel("=>");
        s4 = new JLabel("=>");
        s5 = new JLabel("=>");
        l6 = new JLabel("Letra");
        l7 = new JLabel("Regras");
        l8 = new JLabel("A criação de regras é feita da seguinte maneira:");
        l9 = new JLabel("Atribuição de uma letra inicial (ex: F)");
        l10 = new JLabel("atribuição de uma regra para 'F' e em seguida ");
        l11 = new JLabel("identificar o que é que F irá fazer (ex: Turn).");
        l12 = new JLabel("Nº de Iterações");
        l13 = new JLabel("Letra Inicial");




    }

    public ArrayList<JLabel> labels () {

        s1.setBounds(822,88,20, 20);
        s1.setVisible(true);

        s2.setBounds(822,118,20, 20);
        s2.setVisible(true);

        s3.setBounds(822,148,20, 20);
        s3.setVisible(true);

        l6.setBounds(780, 60, 50,20);
        l6.setVisible(true);

        l7.setBounds(900, 60, 50,20);
        l7.setVisible(true);

        l8.setBounds(710, 250, 405, 20);
        l8.setVisible(true);

        l9.setBounds(745, 270, 375, 20);
        l9.setVisible(true);

        l10.setBounds(715, 290,  405, 20);
        l10.setVisible(true);

        l11.setBounds(725,  310, 405, 20);
        l11.setVisible(true);

        l12.setBounds(705,490, 120,20);

        l13.setBounds(835,490,100,20);


        labelArray.add(s1);
        labelArray.add(s2);
        labelArray.add(s3);
        labelArray.add(s4);
        labelArray.add(s5);
        labelArray.add(l6);
        labelArray.add(l7);
        labelArray.add(l8);
        labelArray.add(l9);
        labelArray.add(l10);
        labelArray.add(l11);
        labelArray.add(l12);
        labelArray.add(l13);

        return labelArray;
    }

}
