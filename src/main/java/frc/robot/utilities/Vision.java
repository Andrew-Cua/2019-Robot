package frc.robot.utilities;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
//http://10.63.41.11:5801/
//Red Balance 1648
//Blue balance 1975
public class Vision
{
    NetworkTable limelight = NetworkTableInstance.getDefault().getTable("limelight");
    NetworkTableEntry tx = limelight.getEntry("tx");
    //Horizontal Offset From Crosshair To Target (-27 degrees to 27 degrees)
    NetworkTableEntry ty = limelight.getEntry("ty");
    //Vertical Offset From Crosshair To Target (-20.5 degrees to 20.5 degrees)
    NetworkTableEntry tv = limelight.getEntry("tv");
    //Whether the limelight has any valid targets (0 or 1)
    NetworkTableEntry ta = limelight.getEntry("ta");
    //Target Area (0% of image to 100% of image)
    NetworkTableEntry ta1 = limelight.getEntry("ta1");
    //Area (0% of image to 100% of image) (Dont Use)
    private double x,y,area,v;
    
    public Vision()
    {
        
    }

    public void readValues()
    {
        this.x = tx.getDouble(0.0);
        this.y = ty.getDouble(0.0);
        this.area = ta.getDouble(0.0);
        this.v = tv.getDouble(0);
        SmartDashboard.putNumber("LimelightX", x);
        SmartDashboard.putNumber("LimelightY", y);
        SmartDashboard.putNumber("LimelightArea", area);
        SmartDashboard.putNumber("Limelightv", v);
    }
    public void changeCamMode(int mode)
    {
        NetworkTableInstance.getDefault().getTable("limelight").getEntry("camMode").setNumber(mode);
    }
    public void changepipeline(int Pipeline){
        NetworkTableInstance.getDefault().getTable("limelight").getEntry("pipeline").setNumber(Pipeline);
    }
    public void BallFollower() {
        changepipeline(3);
        if (v == 1 && x > -5 && x < 5) {
            Robot.drivetrain_Subsys.set(.5, .5);
        } else if (v == 1 && x > 5) {
            Robot.drivetrain_Subsys.set(.25, -.25);
        } else if (v == 1 && x < -5) {
            Robot.drivetrain_Subsys.set(-.25, .25);
        } else {
            System.out.println("You messed up Prick!!!");
        }
    }

}