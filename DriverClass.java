/*Team Members:
Mamta Pednekar                  Student ID: 026909816
Vidyadhari Raghunadha Naidu     Student ID: 027957174
 */
import java.io.FileNotFoundException;
import java.util.List;
public class DriverClass {
    private static MoveTurtle moveExp;
    public static void main(String[] args) throws FileNotFoundException {
        Evaluator exp = new Evaluator("turtle.txt");
        exp.makeSyntaxTree();
        Context context = exp.getContext();
        context.setTurtle(new Turtle());
        VisitorPattern<List<TurtleCommands>> var = new VisitorPatternExecution(context);
        exp.accept(var);
        List<TurtleCommands> turtleCommandsList = var.getResult();
        Turtle turt = new Turtle();
        for(int i = 0; i< turtleCommandsList.size(); i++){
            turt = turtleCommandsList.get(i).execute();
        }
        //check the location and distance values for development purpose
        System.out.println("X coordinate: " + turt.location().getX()+"\nY coordinate: "+ turt.location().getY());
        VisitorPattern<Integer> distanceVisitor = new DistCalcVisitorPattern(context);
        exp.accept(distanceVisitor);
        System.out.println("Total Distance: "+ distanceVisitor.getResult());
        System.out.println("Angle in degrees: "+ turt.bearingTo());
    }
}

