package frc.robot.utilities;

public class Conversions
{  
    public static final double HIGH_GEAR_RATIO = 9.74;
    //in inches
    public static final double WHEEL_RADIUS = 3;
    public static final double WHEEL_CIRCUMFERENCE = Math.PI*(2*WHEEL_RADIUS);
    public static final double INCHES_PER_REV = WHEEL_CIRCUMFERENCE/HIGH_GEAR_RATIO;
    //in ticks (4096)
    public static final int MOTIONACCEL = 300;
    public static final int MOTIONVEL   = 300;

    //PIDF Constants
    public static double motionkP = 0.001;
    public static double motionkI;
    public static double motionkF;
    public static double motionkD;
    public static double inchesToRev(double inches)
    {
        return inches/INCHES_PER_REV;
    }
}