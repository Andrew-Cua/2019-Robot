package frc.robot.subsystems;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Intake_Subsys extends Subsystem
{

    private static Intake_Subsys instance = new Intake_Subsys();
    private Intake_Subsys()
    {

    }


    public static Intake_Subsys getInstance()
    {
        return instance;
    }

    public void initDefaultCommand()
    {

    }

}