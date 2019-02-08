package frc.robot.utilities.StateControl.ArmStates;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.Arm_Subsys;
import frc.robot.subsystems.Arm_Subsys.ArmSetpoints;
import frc.robot.utilities.StateControl.IArmState;

public class MidGoalState implements IArmState
{
    private Arm_Subsys arm;
    public MidGoalState(Arm_Subsys arm)
    {
        this.arm = arm;
    }

    @Override
    public void moveArmToPos()
    {
        arm.setMagicSetpoint(ArmSetpoints.kMidGoal.getSetpoint());
    }

    @Override
    public void updateSmartDashboard()
    {
        SmartDashboard.putString("Arm Position", "Mid Goal");
    }
}