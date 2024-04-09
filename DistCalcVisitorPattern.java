/*Team Members:
Mamta Pednekar                  Student ID: 026909816
Vidyadhari Raghunadha Naidu     Student ID: 027957174
 */
public class DistCalcVisitorPattern implements VisitorPattern<Integer> {
    private Context context;
    private int allDistance;
    private int repeatCount = 0;
    public DistCalcVisitorPattern(Context context) {
        this.context = context;
        allDistance = 0;
    }
    //Method to calculate the distance from move expression
    @Override
    public void visitMoveExpression(MoveTurtle moveExp) {
        if(repeatCount == 0) {
            int distance = moveExp.getArgument().interpret(context);
            context.getTurtle().move(distance);
            allDistance = allDistance + distance;
        }
    }
    //Method to calculate the turn of turtle
    @Override
    public void visitTurnExpression(TurnTurtle turnExp) {
        if(repeatCount == 0)
            context.getTurtle().turn(turnExp.getArgument().interpret(context));
    }
    //Method to calculate the penup to draw the turtle line
    @Override
    public void visitPenUpExpression(PenUpTurtle penupExp) {
        if (repeatCount == 0)
            context.getTurtle().penUp();
    }
    //Method to calculate the pendown to draw the turtle line
    @Override
    public void visitPenDownExpression(PenDownTurtle pendownExp) {
        if (repeatCount == 0)
            context.getTurtle().penDown();
    }
    @Override
    public void visitAssignmentExpression(AssignmentExpression assignExpression) {
        if (repeatCount == 0)
            context.setValue(assignExpression.getKey(), assignExpression.getValue());
    }
    //Method to calculate the count of repeat expression
    @Override
    public void visitRepeatExpression(RepeatTurtle repeatTurtle) {
        repeatCount++;
    }
    //get overall distance
    @Override
    public Integer getResult() {
        return allDistance;
    }
    //Method to repeat the visit till end is encountered
    @Override
    public void visitEndExpression(EndTurtle endTurtle) {
        repeatCount--;
        InterpreterPattern<Void> exp = endTurtle.correspondingRepeatExp().getNext();
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
