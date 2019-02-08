package frc.robot.utilities.StateControl.ArmStates;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.Arm_Subsys;
import frc.robot.subsystems.Arm_Subsys.ArmSetpoints;
import frc.robot.utilities.StateControl.IArmState;
public class HighGoalState implements IArmState
{
    private Arm_Subsys arm;
    public HighGoalState(Arm_Subsys arm)
    {
        this.arm = arm;
    }

    @Override
    public void moveArmToPos()
    {
        arm.setMagicSetpoint(ArmSetpoints.kHighGoal.getSetpoint());
    }

    @Override 
    public void updateSmartDashboard()
    {
        SmartDashboard.putString("Arm Position", "High Goal");
    }
}