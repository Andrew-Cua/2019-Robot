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
    private NetworkTable limelight = NetworkTableInstance.getDefault().getTable("limelight");
    private NetworkTableEntry tx = limelight.getEntry("tx");
    //Horizontal Offset From Crosshair To Target (-27 degrees to 27 degrees)
    private NetworkTableEntry ty = limelight.getEntry("ty");
    //Vertical Offset From Crosshair To Target (-20.5 degrees to 20.5 degrees)
    private NetworkTableEntry tv = limelight.getEntry("tv");
    //Whether the limelight has any valid targets (0 or 1)
    private NetworkTableEntry ta = limelight.getEntry("ta");
    //Target Area (0% of image to 100% of image)
    private NetworkTableEntry ta1 = limelight.getEntry("ta1");
    //Area (0% of image to 100% of image) (Dont Use)
    private NetworkTableEntry tp = limelight.getEntry("pipeline");
    private double x, y, area, v, currentPipeline, tempPipeline ;
    
    public Vision()
    {
        
    }

    public void updateVision()
    {
        //changes pipelines if there is a difference between ints 
        if(tempPipeline != currentPipeline){NetworkTableInstance.getDefault().getTable("limelight").getEntry("pipeline").setNumber(this.tempPipeline);}
        this.x = tx.getDouble(0.0);
        this.y = ty.getDouble(0.0);
        this.area = ta.getDouble(0.0);
        this.v = tv.getDouble(0);
        this.currentPipeline = tp.getDouble(0);
        SmartDashboard.putNumber("LimelightX", x);
        SmartDashboard.putNumber("LimelightY", y);
        SmartDashboard.putNumber("LimelightArea", area);
        SmartDashboard.putNumber("Limelightv", v);
        SmartDashboard.putNumber("Current Pipeline", currentPipeline);

        
    }
    public void changeCamMode(int mode)
    {
        NetworkTableInstance.getDefault().getTable("limelight").getEntry("camMode").setNumber(mode);
    }
    public void changePipeline(int pipeline){
        this.tempPipeline = pipeline;
    }
    public double getCurrentPipeline()
    {
        return currentPipeline;
    }
    public void BallFollower() {
        if(getCurrentPipeline() != 3){changePipeline(3);}

        double leftPower = 0, rightPower = 0;
        if(v != 1)//checks if a target is in view
        {
            System.out.println("nothing is being seen");
            System.out.println("Current Value V: " + v);

            return;//exits method if nothing is seen and gives current value of "v"
        }
        //allows for only one method call and increased readability by making left and right power exist
        if (v != 0 && x > -5 && x < 5)
        {
            leftPower  = 0.5;
            rightPower = 0.5;
            
        } else if (v == 1 && x > 5)
        {
            leftPower  =  0.25;
            rightPower = -0.25;

        } else if (v == 1 && x < -5) 
        {
            leftPower  = -0.25;
            rightPower = 0.25;
        } 

        Robot.drivetrain_Subsys.set(leftPower, rightPower);

    }

}