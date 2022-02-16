public class Turn extends TurtleStatement {

    double degree;

    public Turn(double degree) {

        this.degree = degree;

    }

    public double getDegree() {

        return degree;

    }

    public void run(Interpreter interpreter) {

        interpreter.runTurn(this);

    }

}