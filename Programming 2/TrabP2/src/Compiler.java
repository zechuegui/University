import galapagos.*;

import java.util.*;

public class Compiler {

    TreeMap<Character, TurtleStatement> tmap = new TreeMap<Character, TurtleStatement>();

    public void addRule(Character letter, TurtleStatement statement) {

        tmap.put(letter,statement);
    }
    protected TurtleStatement compile(Character c) {


        return tmap.get(c);

    }
    protected Vector<TurtleStatement> compile(String word) {

        Vector<TurtleStatement> result = new Vector<>();

        for (int i = 0; i < word.length(); i++) {

                result.add(compile(word.charAt(i)));
        }

        return result;
    }
}

