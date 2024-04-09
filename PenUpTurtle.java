/*Team Members:
Mamta Pednekar                  Student ID: 026909816
Vidyadhari Raghunadha Naidu     Student ID: 027957174
 */
public class PenUpTurtle implements InterpreterPattern<Void> {
    private InterpreterPattern<Void> next;
    @Override
    public Void interpret(Context value) {
        Turtle turt = value.getTurtle();
        turt.penUp();
        if (next != null)
            next.interpret(value);
        return null;
    }
    public PenUpTurtle() {
        next = null;
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
        var.visitPenUpExpression(this);
    }
}
