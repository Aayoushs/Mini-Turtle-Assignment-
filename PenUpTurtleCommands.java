/*Team Members:
Mamta Pednekar                  Student ID: 026909816
Vidyadhari Raghunadha Naidu     Student ID: 027957174
 */
public class PenUpTurtleCommands implements TurtleCommands {
    private Turtle turt;
    private boolean isExecuted = false;
    public PenUpTurtleCommands(Turtle turt) {
        this.turt = turt;
    }
    @Override
    public Turtle execute() {
        turt.penUp();
        isExecuted = true;
        return turt;
    }
    @Override
    public Turtle undo() {
        if (isExecuted) {
            turt.penDown();
            return turt;
        } else
            throw new RuntimeException("Unable to undo");
    }
}
