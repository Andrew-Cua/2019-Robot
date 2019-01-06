package frc.robot.subsystems;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Superstructure_Subsys extends Subsystem
{
    private static Superstructure_Subsys instance = new Superstructure_Subsys();
    private Superstructure_Subsys()
    {

    }


    public static Superstructure_Subsys getInstance()
    {
        return instance;
    }
    public void initDefaultCommand()
    {

    }
}