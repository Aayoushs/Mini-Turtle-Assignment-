/*Team Members:
Mamta Pednekar                  Student ID: 026909816
Vidyadhari Raghunadha Naidu     Student ID: 027957174
 */
//interface for visitor pattern
public interface VisitorPattern<E> {
    public void visitMoveExpression(MoveTurtle moveExp);
    public void visitTurnExpression(TurnTurtle turnExp);
    public void visitPenUpExpression(PenUpTurtle penupExp);
    public void visitPenDownExpression(PenDownTurtle pendownExp);
    public void visitAssignmentExpression(AssignmentExpression assignExpression);
    public void visitRepeatExpression(RepeatTurtle repeatTurtle);
    public void visitEndExpression(EndTurtle endTurtle);
    public E getResult();
}

