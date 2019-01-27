package frc.robot.subsystems;
import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.utilities.E3DistanceSensor;


public class Superstructure_Subsys extends Subsystem
{
    private static Superstructure_Subsys instance = new Superstructure_Subsys();
    private E3DistanceSensor distanceSensor;
    private double distance;
    private Superstructure_Subsys()
    {
        this.distanceSensor = new E3DistanceSensor(Port.kOnboard,0x54);

    }

    public void updateDistance()
    {
        this.distance = distanceSensor.getDistance();
    }

    public void updateSmartDashboard()
    {
        distanceSensor.updateSmartDashboard();
    }

    public static Superstructure_Subsys getInstance()
    {
        return instance;
    }
    public void initDefaultCommand()
    {

    }
}