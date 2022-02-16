import galapagos.Turtle;
import galapagos.TurtleDrawingWindow;
import java.awt.*;
import java.util.List;

import static javax.swing.WindowConstants.HIDE_ON_CLOSE;

public class InterpreterClass implements Interpreter{

    Turtle turtle;
    TurtleDrawingWindow drawing;

    public void runTurtle(){

        turtle = new Turtle(Turtle.NO_DEFAULT_WINDOW);
        drawing = new TurtleDrawingWindow();

        turtle.hide();
        turtle.speed(9000);
        turtle.penColor(Color.BLACK);

        drawing.add(turtle);
        drawing.setGrid(false);
        drawing.setBackground(Color.WHITE);
        drawing.setAlwaysOnTop(true);
        drawing.setDefaultCloseOperation(HIDE_ON_CLOSE);
        drawing.setBounds(10,10,650,650);
        drawing.setUnit(0.3);
        drawing.setVisible(true);

    }


    public void run(List<TurtleStatement> program){

        for(int i=0; i<program.size(); i++){

            TurtleStatement f = program.get(i);
            f.run(this);

        }

    }
    public void runForward(Forward statement){

        turtle.move(statement.getDistance());

    }
    public void runTurn(Turn statement){

        turtle.turn((statement.getAngle()));
    }
    public void runPenUp(PenUp statement){

        turtle.penUp();
    }
    public void runPenDown(PenDown statement){

        turtle.penDown();
    }
    public void runLeap(Leap statement){
        turtle.penUp();
        turtle.move(statement.getDistance());
        turtle.penDown();
    }
}


