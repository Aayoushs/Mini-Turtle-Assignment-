/*Team Members:
Mamta Pednekar                  Student ID: 026909816
Vidyadhari Raghunadha Naidu     Student ID: 027957174
 */
//class to turn the turtle by specific angle in degrees
public class TurnTurtle implements InterpreterPattern<Void> {
    private InterpreterPattern<Integer> argument;
    private InterpreterPattern<Void> next;
    public TurnTurtle(InterpreterPattern<Integer> argument) {
        this.argument = argument;
        this.next = null;
    }
    @Override
    public Void interpret(Context value) {
        Turtle t = value.getTurtle();
        t.turn(argument.interpret(value));
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
    public InterpreterPattern<Integer> getArgument() {
        return this.argument;
    }
    @Override
    public void accept(VisitorPattern var) {
        var.visitTurnExpression(this);
    }
}

