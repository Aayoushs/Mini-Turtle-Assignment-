/*Team Members:
Mamta Pednekar                  Student ID: 026909816
Vidyadhari Raghunadha Naidu     Student ID: 027957174
 */
//Moveturtle implements the interface InterpreterPattern
public class MoveTurtle implements InterpreterPattern<Void> {
    private InterpreterPattern<Integer> argument;
    private InterpreterPattern<Void> next;
    public MoveTurtle(InterpreterPattern<Integer> argument) {
        this.argument = argument;
        this.next = null;
    }
    //interpret through next expression
    @Override
    public Void interpret(Context value) {
        value.getTurtle().move(argument.interpret(value));

        if (next != null) {
            next.interpret(value);
        }
        return null;
    }
    //set to next expression
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
        var.visitMoveExpression(this);
    }
}