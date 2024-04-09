/*Team Members:
Mamta Pednekar                  Student ID: 026909816
Vidyadhari Raghunadha Naidu     Student ID: 027957174
 */
//Class to draw a line as the turtle moves
public class PenDownTurtle implements InterpreterPattern<Void> {
    private InterpreterPattern<Void> next;
    public PenDownTurtle() {
        next = null;
    }
    @Override
    public Void interpret(Context value) {
        Turtle turt = value.getTurtle();
        turt.penDown();
        if (next != null)
            next.interpret(value);
        return null;
    }
    @Override
    public void setNext(InterpreterPattern<Void> exp) {
        this.next = exp;
    }
    @Override
    public InterpreterPattern<Void> getNext() {
        return next;
    }
    @Override
    public void accept(VisitorPattern var) {
        var.visitPenDownExpression(this);
    }
}