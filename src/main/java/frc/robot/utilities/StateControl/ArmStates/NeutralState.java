package frc.robot.utilities.StateControl.ArmStates;

import frc.robot.utilities.StateControl.IArmState;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.Arm_Subsys;
import frc.robot.subsystems.Arm_Subsys.ArmSetpoints;
public class NeutralState implements IArmState
{
    private Arm_Subsys arm;
    public NeutralState(Arm_Subsys arm)
    {
        this.arm = arm;
    }

    @Override
    public void moveArmToPos()
    {
        arm.setMagicSetpoint(ArmSetpoints.kNeutral.getSetpoint());
    }

    @Override
    public void updateSmartDashboard()
    {
        SmartDashboard.putString("Arm Position", "neutral");
    }
}