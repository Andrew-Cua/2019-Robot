package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
enum ArmDirection
{
    ROTATEUP(),
    ROTATEDOWN();
}

public class Arm_Subsys extends Subsystem
{
    private TalonSRX masterArm, slaveArm;

    private static Arm_Subsys instance = new Arm_Subsys();
    private Arm_Subsys()
    {

    }

    public void rotateArm(ArmDirection direction)
    {
        switch(direction)
        {
            case ROTATEUP:
                masterArm.set(ControlMode.PercentOutput,1);
                break;
            case ROTATEDOWN:
                masterArm.set(ControlMode.PercentOutput, -1);
                break;
        }
    }

    


    public static Arm_Subsys getInstance()
    {
        return instance; 
    }
    public void initDefaultCommand()
    {

    }
}