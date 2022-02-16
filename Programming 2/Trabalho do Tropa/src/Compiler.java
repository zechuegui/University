import java.util.*;
import java.io.*;

public class Compiler {

    Map<Character, TurtleStatement> rules;//dicionario para rules

    public Compiler () {
        this.rules = new HashMap<Character, TurtleStatement>();//criar um dicionario para utilizar e criar novas regras
    }

    public void addRule(Character letter, TurtleStatement statement) {
        rules.put(letter, statement);
    }

    protected TurtleStatement compile(Character c) {
        return rules.get(c);
    }



    protected Vector<TurtleStatement> compile(String word) {

        Vector<TurtleStatement> result = new Vector<>();

        for (int i = 0; i < word.length(); i++) {
            result.add(compile(word.charAt(i)));
        }

        return result;
    }

}