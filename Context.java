/*Team Members:
Mamta Pednekar                  Student ID: 026909816
Vidyadhari Raghunadha Naidu     Student ID: 027957174
 */
import java.util.Hashtable;
public class Context {
    private Hashtable<String, Integer> values;
    private Turtle turt;
    public Context() {
        values = new Hashtable<String, Integer>();
        turt = new Turtle();
    }
    //Method for assigning object to this pointer
    public void setTurtle(Turtle t) {
        this.turt = turt;
    }
    //Method to return the object turt
    public Turtle getTurtle() {
        return turt;
    }
    public int getValue(String key) {
        if (values.get(key) != null)
            return values.get(key);
        else {
            return Integer.MIN_VALUE;
        }
    }
    public void setValue(String key, int value) {
        values.put(key, value);
    }

    public void removeValues() {
        values.clear();
    }
}

