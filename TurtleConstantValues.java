/*Team Members:
Mamta Pednekar                  Student ID: 026909816
Vidyadhari Raghunadha Naidu     Student ID: 027957174
 */
public class TurtleConstantValues implements InterpreterPattern<Integer> {
    private int value;
    public TurtleConstantValues(int value) {
        this.value = value;
    }
    @Override
    public Integer interpret(Context values) {
        return value;
    }
    @Override
    public void setNext(InterpreterPattern<Integer> exp) {
        throw new UnsupportedOperationException("Unsupported Operation Detected");
    }
    @Override
    public InterpreterPattern<Integer> getNext() {
        throw new UnsupportedOperationException("Unsupported Operation Detected");
    }
    @Override
    public void accept(VisitorPattern v) {
        throw new UnsupportedOperationException("Unsupported Operation Detected");
    }
}

