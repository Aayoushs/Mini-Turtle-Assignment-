/*Team Members:
Mamta Pednekar                  Student ID: 026909816
Vidyadhari Raghunadha Naidu     Student ID: 027957174
 */
public class Variable implements InterpreterPattern<Integer> {
    private String key;
    private int RepeatCount;
    public Variable(String name, int RepeatCount) {
        this.key = name;
        this.RepeatCount = RepeatCount;
    }
    @Override
    public Integer interpret(Context values) {
        for (int i = RepeatCount; i > 0; i--) {
            if (values.getValue(i + key) != Integer.MIN_VALUE) {
                return values.getValue(i + key);
            }
        }
        if (values.getValue(key) != Integer.MIN_VALUE)
            return values.getValue(key);
        else
            throw new RuntimeException("Variable Uninitialized");
    }
    @Override
    public void setNext(InterpreterPattern<Integer> exp) {
        throw new UnsupportedOperationException("Terminal expressions Unsupported");
    }
    @Override
    public InterpreterPattern<Integer> getNext() {
        throw new UnsupportedOperationException("Terminal expressions Unsupported");
    }
    @Override
    public void accept(VisitorPattern v) {
        throw new UnsupportedOperationException("Terminal expressions Unsupported");
    }
}

