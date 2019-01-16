package frc.robot.utilities;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

public class Vision
{
    NetworkTable limelight = NetworkTableInstance.getDefault().getTable("limelight");
    NetworkTableEntry tx = limelight.getEntry("tx");
    NetworkTableEntry ty = limelight.getEntry("ty");
    NetworkTableEntry ta = limelight.getEntry("ta");

    private double x,y,area;
    public Vision()
    {
        
    }

    public void readValues()
    {
        this.x = tx.getDouble(0.0);
        this.y = ty.getDouble(0.0);
        this.area = ta.getDouble(0.0);
    }
}