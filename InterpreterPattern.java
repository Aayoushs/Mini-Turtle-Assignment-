/*Team Members:
Mamta Pednekar                  Student ID: 026909816
Vidyadhari Raghunadha Naidu     Student ID: 027957174
 */
//Interface for interpreter pattern
public interface InterpreterPattern<E> {
    public E interpret(Context value);
    public void setNext(InterpreterPattern<E> exp);
    public InterpreterPattern<E> getNext();
    public void accept(VisitorPattern<E> v);
}