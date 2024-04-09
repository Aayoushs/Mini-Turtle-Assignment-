/*Team Members:
Mamta Pednekar                  Student ID: 026909816
Vidyadhari Raghunadha Naidu     Student ID: 027957174
 */
public class EndTurtle implements InterpreterPattern<Void> {
    private InterpreterPattern<Void> repeat, next;
    private InterpreterPattern<Integer> repeatCount;
    private int count = 0;
    public EndTurtle(InterpreterPattern<Void> exp, InterpreterPattern<Integer> repeatCountExp) {
        repeat = exp;
        next = null;
        this.repeatCount = repeatCountExp;
    }
    @Override
    public Void interpret(Context value) {
        count++;
        if (count < repeatCount.interpret(value)) {
            if (repeat.getNext() != null)
                repeat.getNext().interpret(value);
        } else if (next != null) {
            count = 0;
            next.interpret(value);
        }
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
    public InterpreterPattern<Void> correspondingRepeatExp() {
        return repeat;
    }
    public InterpreterPattern<Integer> getArgumentExp() {
        return repeatCount;
    }
    @Override
    public void accept(VisitorPattern var) {
        var.visitEndExpression(this);
    }
}
