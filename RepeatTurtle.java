/*Team Members:
Mamta Pednekar                  Student ID: 026909816
Vidyadhari Raghunadha Naidu     Student ID: 027957174
 */
//repeats the expression of turtle till end
public class RepeatTurtle implements InterpreterPattern<Void> {
    private InterpreterPattern<Void> next;
    private InterpreterPattern<Integer> argument;
    public RepeatTurtle(InterpreterPattern<Integer> argument) {
        this.next = null;
        this.argument = argument;
    }
    @Override
    public Void interpret(Context value) {
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
    public InterpreterPattern<Integer> getRepeatCount() {
        return argument;
    }
    @Override
    public void accept(VisitorPattern var) {
        var.visitRepeatExpression(this);
    }
}

