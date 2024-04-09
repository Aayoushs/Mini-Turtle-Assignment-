/*Team Members:
Mamta Pednekar                  Student ID: 026909816
Vidyadhari Raghunadha Naidu     Student ID: 027957174
 */

import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.List;
public class TurtleTest {
    private double epsilonValue = 0.01;
    //test function to validate the turtle operations
    @Test
    public void turtleTest1() throws FileNotFoundException {
        Evaluator evaluator = new Evaluator("turtle.txt");
        evaluator.makeSyntaxTree();
        Context context = evaluator.getContext();
        List<TurtleCommands> turtleCommandsList = getCommandListUsingVisitor(context, evaluator);
        Turtle turtleVisitor = new Turtle();
        for (int i = 0; i < turtleCommandsList.size(); i++) {
            turtleVisitor = turtleCommandsList.get(i).execute();
        }
        int totalDistance = getTotalDistanceUsingVisitor(context, evaluator);
        assertTrue(Math.abs(460 - context.getTurtle().location().getX()) < epsilonValue);
        assertTrue(Math.abs(-40 - context.getTurtle().location().getY()) < epsilonValue);
        assertTrue(Math.abs(context.getTurtle().location().getX()- turtleVisitor.location().getX()) < epsilonValue);
        assertTrue(Math.abs(context.getTurtle().location().getY()- turtleVisitor.location().getY()) < epsilonValue);
        assertEquals(totalDistance, 500);
        assertTrue(context.getTurtle().isPenUp());
        assertTrue(turtleVisitor.isPenUp());
        Turtle turtle1 = turtleCommandsList.get(turtleCommandsList.size() - 1).undo();
        assertTrue(Math.abs(460 - turtle1.location().getX()) < epsilonValue);
        assertEquals(turtle1.direction(), -90);
        Turtle turtle2 = turtleCommandsList.get(turtleCommandsList.size() - 2).undo();
        assertTrue(Math.abs(460 - turtle2.location().getX()) < epsilonValue);
        assertEquals(turtle2.direction(), 0);
        Turtle turtle3 = turtleCommandsList.get(turtleCommandsList.size() - 3).undo();
        assertTrue(Math.abs(450 - turtle3.location().getX()) < epsilonValue);
        assertTrue(turtle3.isPenUp());
        Turtle turtle4 = turtleCommandsList.get(turtleCommandsList.size() - 4).undo();
        assertTrue(turtle4.isPenUp());
    }
    //test method to check the evaluator method
    @Test(expected = RuntimeException.class)
    public void TurtleTest2() throws FileNotFoundException {
        Evaluator evaluator = new Evaluator("turtle.txt");
        evaluator.makeSyntaxTree();
        Context context = evaluator.getContext();
        List<TurtleCommands> turtleCommandsList = getCommandListUsingVisitor(context, evaluator);
        turtleCommandsList.get(0).undo();
    }
    //test method for interpreter pattern
    @Test
    public void TurtleTest3() throws FileNotFoundException {
        Evaluator evaluator = new Evaluator("turtle.txt");
        evaluator.makeSyntaxTree();
        Context context = evaluator.getContext();
        Turtle turtleInterpreter = context.getTurtle(); //getResultUsingInterpreter(context, evaluator);
    }
    //helper method for visitor pattern
    public List<TurtleCommands> getCommandListUsingVisitor(Context context,
                                                           Evaluator evaluator) {
        context.setTurtle(new Turtle());
        VisitorPattern<List<TurtleCommands>> commandVisitor = new VisitorPatternExecution(context);
        evaluator.accept(commandVisitor);
        List<TurtleCommands> turtleCommandsList = commandVisitor.getResult();
        return turtleCommandsList;
    }
    private void assertEquals(int direction, int i) {
        // TODO Auto-generated method stub
    }
    private void assertTrue(boolean b) {
        // TODO Auto-generated method stub
    }
    //helper to get total distance using visitor pattern
    public int getTotalDistanceUsingVisitor(Context context, Evaluator evaluator) {
        context.setTurtle(new Turtle());
        VisitorPattern<Integer> distanceVisitor = new DistCalcVisitorPattern(
                context);
        evaluator.accept(distanceVisitor);
        int totalDistance = distanceVisitor.getResult();
        return totalDistance;
    }
}

