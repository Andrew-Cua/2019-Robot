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
        NetworkTableInstance.getDefault().getTable("limelight").getEntry("pipeline").setNumber(3);
        this.x = tx.getDouble(0.0);
        this.y = ty.getDouble(0.0);
        this.area = ta.getDouble(0.0);
        this.v = tv.getDouble(0);
        this.currentPipeline = tp.getDouble(0);
        SmartDashboard.putNumber("LimelightX", x);
        SmartDashboard.putNumber("LimelightY", y);
        SmartDashboard.putNumber("LimelightArea", area);
        SmartDashboard.putNumber("Limelightv", v);
        SmartDashboard.putNumber("Current Pipeline", NetworkTableInstance.getDefault().getTable("limelight").getEntry("pipeline").getDouble(0));

        
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
    public double getV()
    {
        return v;
    }
    public double getX()
    {
        return x;
    }
    public double getY()
    {
        return y;
    }

    

}