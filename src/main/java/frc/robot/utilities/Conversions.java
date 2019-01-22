package frc.robot.utilities;

public class Conversions
{  
    public static final double HIGH_GEAR_RATIO = 9.74;
    //in inches
    public static final double WHEEL_RADIUS = 3;
    public static final double WHEEL_CIRCUMFERENCE = Math.PI*(2*WHEEL_RADIUS);
    public static final double INCHES_PER_REV = WHEEL_CIRCUMFERENCE/HIGH_GEAR_RATIO;

    public static double inchesToRev(double inches)
    {
        return inches/INCHES_PER_REV;
    }
}