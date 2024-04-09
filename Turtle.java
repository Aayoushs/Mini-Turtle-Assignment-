/*Team Members:
Mamta Pednekar                  Student ID: 026909816
Vidyadhari Raghunadha Naidu     Student ID: 027957174
 */

import java.awt.geom.Point2D;

public class Turtle {
    private Point2D point;
    private boolean isPenUp;
    private int angle;
    public double X_coordinate=0.0;
    public double Y_coordinate=0.0;
    private Context context;
    public Turtle() {
        //Defines a point representing a location in (x,y) coordinate space
        point = new Point2D() {
            //method to fetch X-coordinate points
            @Override
            public double getX() {
                return X_coordinate;
            }
            //method to fetch Y-coordinate points
            @Override
            public double getY() {
                return Y_coordinate;
            }
            //Set the location based on X and Y coordinates
            @Override
            public void setLocation(double x, double y) {
                X_coordinate = x;
                Y_coordinate = y;
            }
        };
        isPenUp = false;
        angle = 0;
    }
    public void penDown() {
        isPenUp = false;
    }
    public void penUp() {
        isPenUp = true;
    }
    //Returns the distance to the given point
    public float distanceTo(MoveTurtle moveExp)
    {
        int distance = moveExp.getArgument().interpret(context);
        return distance;
    }
    //Returns the angle in degrees to the given point
    public float bearingTo()
    {
        return angle;
    }
    public boolean isPenUp() {
        return isPenUp;
    }
    //Add “degrees” to the current heading of the turtle.
    public void turn(int angle) {
        this.angle = this.angle+angle;
    }
    //Returns the current direction of the turtle.
    public int direction() {
        return angle;
    }
    //Returns the current location of the turtle
    public Point2D location() {
        return point;
    }
    //Move the turtle distance units in the current direction and draw on the screen if the pen is down.
    public void move(int distance) {
        double radian = Math.PI * ((double) angle / 180);
        double dX = Math.cos(radian) * distance;
        double dY = Math.sin(radian) * distance;
        point.setLocation((point.getX() + dX), (point.getY() + dY));
    }

}

