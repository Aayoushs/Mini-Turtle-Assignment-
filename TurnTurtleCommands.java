/*Team Members:
Mamta Pednekar                  Student ID: 026909816
Vidyadhari Raghunadha Naidu     Student ID: 027957174
 */
public class TurnTurtleCommands implements TurtleCommands {
    private int angle;
    private Turtle turt;
    private boolean isExecuted = false;
    public TurnTurtleCommands(Turtle turt, int angle) {
        this.angle = angle;
        this.turt = turt;
    }
    @Override
    public Turtle execute() {
        turt.turn(angle);
        isExecuted = true;
        return turt;
    }
    @Override
    public Turtle undo() {
        if (isExecuted) {
            turt.turn(-(angle));
            return turt;
        } else
            throw new RuntimeException("Can't undo");
    }

}

