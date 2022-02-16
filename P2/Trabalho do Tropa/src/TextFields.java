import javax.swing.*;
import java.util.ArrayList;

public class TextFields extends MyWindow {

    ArrayList<JTextField> textArray, textArray2, textArray3;

    public JTextField tf1, tf2, tf3, tf4, tf5, tf6, tf7, tf8, tf9;

    public TextFields () {

        tf1 = new JTextField();
        tf2 = new JTextField();
        tf3 = new JTextField();
        tf4 = new JTextField();
        tf5 = new JTextField();
        tf6 = new JTextField();
        tf7 = new JTextField();
        tf8 = new JTextField();
        tf9 = new JTextField();

        textArray = new ArrayList<>();
        textArray2 = new ArrayList<>();
        textArray3 = new ArrayList<>();

    }

    public ArrayList<JTextField> textfieldC () {

        tf7.setBounds(720,350,40,20);
        tf7.setLayout(null);
        tf7.setHorizontalAlignment(JTextField.CENTER);

        tf8.setBounds(995,350,40,20);
        tf8.setLayout(null);
        tf8.setHorizontalAlignment(JTextField.CENTER);

        textArray2.add(tf7);
        textArray2.add(tf8);

        return textArray2;

    }

    public ArrayList<JTextField> text () {

        tf1.setBounds(785,90,30, 20);
        tf1.setLayout(null);
        tf1.setHorizontalAlignment(JTextField.CENTER);

        tf2.setBounds(785,120,30, 20);
        tf2.setLayout(null);
        tf2.setHorizontalAlignment(JTextField.CENTER);

        tf3.setBounds(785,150,30, 20);
        tf3.setLayout(null);
        tf3.setHorizontalAlignment(JTextField.CENTER);

        tf4.setBounds(850,90,150, 20);
        tf4.setLayout(null);
        tf4.setHorizontalAlignment(JTextField.CENTER);

        tf5.setBounds(850,120,150, 20);
        tf5.setLayout(null);
        tf5.setHorizontalAlignment(JTextField.CENTER);

        tf6.setBounds(850,150,150, 20);
        tf6.setLayout(null);
        tf6.setHorizontalAlignment(JTextField.CENTER);

        //compiler
        tf9.setBounds(850,520, 50,20);
        tf9.setLayout(null);
        tf9.setHorizontalAlignment(JTextField.CENTER);

        textArray.add(tf1);
        textArray.add(tf2);
        textArray.add(tf3);
        textArray.add(tf4);
        textArray.add(tf5);
        textArray.add(tf6);
        textArray.add(tf9);

        return textArray;

    }

}
