/*Team Members:
Mamta Pednekar                  Student ID: 026909816
Vidyadhari Raghunadha Naidu     Student ID: 027957174
 */

public class MoveTurtleCommands implements TurtleCommands {
    private int distance;
    private Turtle turtle;
    private boolean isExecuted = false;
    //calculates the distance turtle moved
    public MoveTurtleCommands(Turtle turt, int distance) {
        this.distance = distance;
        this.turtle = turt;
    }
    //executes the move operation
    @Override
    public Turtle execute() {
        turtle.move(distance);
        isExecuted = true;
        return turtle;
    }
    //reverts the move by stepping back the distance
    @Override
    public Turtle undo() {
        if (isExecuted) {
            turtle.move(-(distance));
            return turtle;
        } else
            throw new RuntimeException("Unable to undo");
    }
}

