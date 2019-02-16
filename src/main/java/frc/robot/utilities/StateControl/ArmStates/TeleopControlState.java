package frc.robot.utilities.StateControl.ArmStates;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.subsystems.Arm_Subsys;
import frc.robot.utilities.StateControl.IArmState;

public class TeleopControlState implements IArmState
{
    private Arm_Subsys arm;
    public TeleopControlState(Arm_Subsys arm)
    {
        this.arm = arm;
    }

    @Override
    public void moveArmToPos()
    {
        arm.set(((int)(Robot.m_oi.getWhatevs().getY(Hand.kLeft))));
    }
    @Override
    public void updateSmartDashboard()
    {
        SmartDashboard.putString("Arm Position", "High Goal");
    }
}