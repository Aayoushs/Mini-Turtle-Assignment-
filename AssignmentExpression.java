/*Team Members:
Mamta Pednekar                  Student ID: 026909816
Vidyadhari Raghunadha Naidu     Student ID: 027957174
 */
public class AssignmentExpression implements InterpreterPattern<Void> {
    private InterpreterPattern<Void> next;
    private String key;
    private int value;
    //Parameterized constructor
    public AssignmentExpression(String key, int value) {
        this.key = key;
        this.value = value;
    }
    public String getKey() {
        return this.key;
    }
    public int getValue() {
        return this.value;
    }
    @Override
    public Void interpret(Context values) {
        values.setValue(key, value);
        if (next != null)
            next.interpret(values);
        return null;
    }
    //Method to assign next expression
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
        var.visitAssignmentExpression(this);
    }
}

