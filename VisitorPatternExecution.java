/*Team Members:
Mamta Pednekar                  Student ID: 026909816
Vidyadhari Raghunadha Naidu     Student ID: 027957174
 */
import java.util.ArrayList;
import java.util.List;
public class VisitorPatternExecution implements VisitorPattern<List<TurtleCommands>> {
    private List<TurtleCommands> turtleCommandsList = new ArrayList<TurtleCommands>();
    private Context context;
    private int RepeatCount = 0;
    public VisitorPatternExecution(Context context) {
        this.context = context;
    }
    //Visitor Pattern for move operation
    @Override
    public void visitMoveExpression(MoveTurtle exp) {
        if (RepeatCount == 0) {
            TurtleCommands move = new MoveTurtleCommands(context.getTurtle(), exp.getArgument().interpret(context));
            turtleCommandsList.add(move);
        }
    }
    //Visitor Pattern for turn operation
    @Override
    public void visitTurnExpression(TurnTurtle exp) {
        if (RepeatCount == 0) {
            TurtleCommands turn = new TurnTurtleCommands(context.getTurtle(), exp.getArgument().interpret(context));
            turtleCommandsList.add(turn);
        }
    }
    //Visitor Pattern for pen up operation
    @Override
    public void visitPenUpExpression(PenUpTurtle penupExp) {
        if (RepeatCount == 0) {
            TurtleCommands penUp = new PenUpTurtleCommands(context.getTurtle());
            turtleCommandsList.add(penUp);
        }
    }
    //Visitor Pattern for pen down operation
    @Override
    public void visitPenDownExpression(PenDownTurtle pendownExp) {
        if (RepeatCount == 0) {
            TurtleCommands penDown = new PenDownTurtleCommands(context.getTurtle());
            turtleCommandsList.add(penDown);
        }
    }
    //Visitor Pattern for assignment operation
    @Override
    public void visitAssignmentExpression(AssignmentExpression assignExpression) {
        if (RepeatCount == 0) {
            if (RepeatCount == 0) {
                context.setValue(assignExpression.getKey(), assignExpression.getValue());
            }		}
    }
    @Override
    public List<TurtleCommands> getResult() {
        return this.turtleCommandsList;
    }
    //Visitor Pattern for repeat operation
    @Override
    public void visitRepeatExpression(RepeatTurtle repeatTurtle) {
        RepeatCount++;
    }
    //Visitor Pattern for end operation
    @Override
    public void visitEndExpression(EndTurtle endTurtle) {
        RepeatCount--;
        InterpreterPattern<Void> exp = endTurtle.correspondingRepeatExp().getNext();
        InterpreterPattern<Void> nextExpression = endTurtle.getNext();
        InterpreterPattern<Integer> argumentExp = endTurtle.getArgumentExp();
        int repeatCount = argumentExp.interpret(context);
        for (int i = 0; i < repeatCount; i++) {
            while (exp != endTurtle) {
                exp.accept((VisitorPattern) this);
                exp = exp.getNext();
            }
            exp = endTurtle.correspondingRepeatExp().getNext();

        }
    }
}