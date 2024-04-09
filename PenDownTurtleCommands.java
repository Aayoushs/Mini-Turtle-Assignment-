/*Team Members:
Mamta Pednekar                  Student ID: 026909816
Vidyadhari Raghunadha Naidu     Student ID: 027957174
 */
public class PenDownTurtleCommands implements TurtleCommands {
    private Turtle turt;
    private boolean isExecuted = false;
    public PenDownTurtleCommands(Turtle turtle) {
        this.turt = turtle;
    }
    //Executes the pen down operation
    @Override
    public Turtle execute() {
        turt.penDown();
        isExecuted = true;
        return turt;
    }
    @Override
    public Turtle undo() {
        if (isExecuted) {
            turt.penUp();
            return turt;
        } else
            throw new RuntimeException("Unable to Undo");
    }

}

