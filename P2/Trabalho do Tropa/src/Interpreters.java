import java.awt.*;
import java.util.List;

import galapagos.*;

import static javax.swing.WindowConstants.HIDE_ON_CLOSE;

public class Interpreters implements Interpreter {

    protected Turtle squirtle;//criar uma turtle nova

    protected TurtleDrawingWindow playground;

    public void playground () {

        squirtle = new Turtle(Turtle.NO_DEFAULT_WINDOW);

        this.playground = new TurtleDrawingWindow();
        this.playground.setUnit(0.7);
        this.playground.add(squirtle);
        this.playground.setName("");
        this.playground.setGrid(false);
        this.playground.setVisible(true);
        this.playground.setDefaultCloseOperation(HIDE_ON_CLOSE);

        squirtle.bodyColor(Color.ORANGE);
        squirtle.penColor(Color.pink);
        squirtle.hide();
        squirtle.speed(100000000);//para alterar o speed para ficar logo desenhado

    }

    public TurtleDrawingWindow getPlayground() {

        return this.playground;

    }

    public void run(List<TurtleStatement> program) {

        for (int i = 0; i < program.size(); i++){//for para percorrer o TurtleStatement

            TurtleStatement letter = program.get(i);//igualdade para a letra ser igual aos statements
            letter.run(this);//para desenhar a tartaruga

        }

    }

    public void runLeap(Leap statement){

        squirtle.penUp();
        squirtle.move(statement.getDistance());
        squirtle.penDown();

    }

    public void runForward(Forward statement) {

        squirtle.move(statement.getDistance());

    }

    public void runTurn(Turn statement) {

        squirtle.turn(statement.getDegree());

    }

    public void runPenUp(PenUp statement) {

        squirtle.penUp();

    }

    public void runPenDown(PenDown statement) {

        squirtle.penDown();

    }

}
