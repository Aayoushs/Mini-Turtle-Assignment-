/*Team Members:
Mamta Pednekar                  Student ID: 026909816
Vidyadhari Raghunadha Naidu     Student ID: 027957174
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;
public class Evaluator {
    String file;
    String expression;
    InterpreterPattern<Void> root = null;
    InterpreterPattern<Void> leaf = null;
    Stack<InterpreterPattern<Void>> repeatStack = new Stack<InterpreterPattern<Void>>();
    Stack<InterpreterPattern<Integer>> repeatCountStack = new Stack<InterpreterPattern<Integer>>();
    int lineCount = 0, repeatCount = 0;
    String[] SplitArray;
    Context context = new Context();
    Evaluator(String file) throws FileNotFoundException {
        this.file = file;
    }
    //forming a syntax tree from a file by splitting the expression
    public void makeSyntaxTree() throws FileNotFoundException {
        context.removeValues();
        Scanner sc = new Scanner(new File(file));
        while (sc.hasNextLine()) {
            lineCount++;
            expression = sc.nextLine().trim();
            SplitArray = expression.split(" ");
            if (SplitArray.length == 2) {
                String argument = SplitArray[1].trim();
                String turtleOperation = SplitArray[0].trim();
                checkForSyntaxError(turtleOperation, argument);
                if (argument.startsWith("$"))
                    VariableExpression(turtleOperation);
                else
                    ConstantExpression(turtleOperation);
            } else if (SplitArray.length == 1 || SplitArray.length == 3) {
                checkForSyntaxError(expression, SplitArray.length);
                if (root == null)
                    RootExpression(expression, null);
                else
                    LeafExpression(expression, null);
            } else {
                sc.close();
                throw new RuntimeException("Unidentified expression Detected");
            }
        }
        sc.close();
        if (repeatCount != 0)
            throw new RuntimeException("End command Missing");
    }
    public void VariableExpression(String turtleOperation) {
        String name = SplitArray[1].trim();
        InterpreterPattern<Integer> variable = new Variable(name, repeatCount);
        if (turtleOperation.equalsIgnoreCase("REPEAT"))
            repeatCountStack.push(variable);
        if (root == null)
            RootExpression(turtleOperation, variable);
        else
            LeafExpression(turtleOperation, variable);
    }
    public void ConstantExpression(String turtleOperation) {
        String value = SplitArray[1].trim();
        int IntegerValue = 0;
        try {
            IntegerValue = Integer.parseInt(value);
        } catch (Exception e) {
        }
        InterpreterPattern<Integer> constant = new TurtleConstantValues(IntegerValue);
        if (turtleOperation.equalsIgnoreCase("REPEAT"))
            repeatCountStack.push(constant);
        if (root == null)
            RootExpression(turtleOperation, constant);
        else
            LeafExpression(turtleOperation, constant);
    }
    // method for Operation selection
    public void RootExpression(String turtleOperation, InterpreterPattern<Integer> argument) {
        if (turtleOperation.equalsIgnoreCase("MOVE")) {
            root = new MoveTurtle(argument);
            leaf = root;
        } else if (turtleOperation.equalsIgnoreCase("TURN")) {
            root = new TurnTurtle(argument);
            leaf = root;
        } else if (turtleOperation.equalsIgnoreCase("REPEAT")) {
            repeatCount++;
            root = new RepeatTurtle(argument);
            leaf = root;
            repeatStack.push(leaf);
        } else if (turtleOperation.equalsIgnoreCase("PENUP")) {
            root = new PenUpTurtle();
            leaf = root;
        } else if (turtleOperation.equalsIgnoreCase("PENDOWN")) {
            root = new PenDownTurtle();
            leaf = root;
        } else if (turtleOperation.startsWith("$")) {
            root = parseAssignmentExpression(turtleOperation);
            leaf = root;
        } else if (!expression.isEmpty())
            throw new RuntimeException("Syntax error Detected");
    }
    //handle leaf expression with turtle operations
    public void LeafExpression(String turtleOperation, InterpreterPattern<Integer> argument) {
        InterpreterPattern<Void> temporaryExpression = null;
        if (turtleOperation.equalsIgnoreCase("MOVE")) {
            temporaryExpression = new MoveTurtle(argument);
            leaf.setNext(temporaryExpression);
            leaf = temporaryExpression;
        } else if (turtleOperation.equalsIgnoreCase("TURN")) {
            temporaryExpression = new TurnTurtle(argument);
            leaf.setNext(temporaryExpression);
            leaf = temporaryExpression;
        } else if (turtleOperation.equalsIgnoreCase("REPEAT")) {
            temporaryExpression = new RepeatTurtle(argument);
            leaf.setNext(temporaryExpression);
            leaf = temporaryExpression;
            repeatCount++;
            repeatStack.push(leaf);
        } else if (turtleOperation.equalsIgnoreCase("PENUP")) {
            temporaryExpression = new PenUpTurtle();
            leaf.setNext(temporaryExpression);
            leaf = temporaryExpression;
        } else if (turtleOperation.equalsIgnoreCase("PENDOWN")) {
            temporaryExpression = new PenDownTurtle();
            leaf.setNext(temporaryExpression);
            leaf = temporaryExpression;
        } else if (turtleOperation.equalsIgnoreCase("END")) {
            temporaryExpression = new EndTurtle(repeatStack.pop(), repeatCountStack.pop());
            leaf.setNext(temporaryExpression);
            leaf = temporaryExpression;
            repeatCount--;
        } else if (turtleOperation.startsWith("$")) {
            if (repeatCount > 0)
                turtleOperation = repeatCount + turtleOperation;
            temporaryExpression = parseAssignmentExpression(turtleOperation);
            leaf.setNext(temporaryExpression);
            leaf = temporaryExpression;
        } else if (!expression.isEmpty())
            throw new RuntimeException("Syntax error Detected");
    }
    //parsing expression based on key values
    public InterpreterPattern<Void> parseAssignmentExpression(String expression) {
        String[] keyValues = expression.split("=");
        String key = keyValues[0].trim();
        int value = 0;
        try {
            value = Integer.parseInt(keyValues[1].trim());
        } catch (Exception e) {
        }
        InterpreterPattern<Void> exp = new AssignmentExpression(key, value);
        return exp;
    }
    //Check if the given value throws syntax error
    public void checkForSyntaxError(String turtleOperation, String argument) {
        if (turtleOperation.equalsIgnoreCase("MOVE")
                || turtleOperation.equalsIgnoreCase("TURN")
                || turtleOperation.equalsIgnoreCase("REPEAT")) {
            if (!argument.startsWith("$")) {
                try {
                    Integer.parseInt(argument);
                } catch (Exception e) {
                    throw new RuntimeException("Integer Value Expected");
                }
            }
        } else
            throw new RuntimeException("Unidentified expression Detected");
    }
    //polymorphic method for checking syntax error
    public void checkForSyntaxError(String expression, int length) {
        if (length == 1) {
            if (expression.startsWith("$")) {
                String[] expArray = expression.split("=");
                try {
                    Integer.parseInt(expArray[1]);
                } catch (Exception e) {
                    throw new RuntimeException("Integer Value Expected");
                }
            } else if (expression.equalsIgnoreCase("PENUP")
                    || expression.equalsIgnoreCase("PENDOWN")
                    || expression.equalsIgnoreCase("END")
                    || expression.isEmpty()) {
            } else
                throw new RuntimeException("Unidentified expression Detected");
        } else {
            if (expression.startsWith("$")) {
                String[] expArray = expression.split(" ");
                try {
                    Integer.parseInt(expArray[2]);
                } catch (Exception e) {
                    throw new RuntimeException("Integer Value Expected");
                }
            } else
                throw new RuntimeException("Unidentified expression Detected");
        }
    }
    public Turtle interpret(Context context) {
        root.interpret(context);
        return context.getTurtle();
    }
    public void accept(VisitorPattern var) {
        InterpreterPattern<Void> exp = root;
        while (exp != null) {
            exp.accept(var);
            exp = exp.getNext();
        }
    }
    public Context getContext() {
        return context;
    }
}