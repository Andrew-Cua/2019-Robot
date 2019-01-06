package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
public class Elevator_Subsys extends Subsystem
{

    private static Elevator_Subsys instance = new Elevator_Subsys();
    private Elevator_Subsys()
    {

    }

    


    public static Elevator_Subsys getInstance()
    {
        return instance; 
    }
    public void initDefaultCommand()
    {

    }
}