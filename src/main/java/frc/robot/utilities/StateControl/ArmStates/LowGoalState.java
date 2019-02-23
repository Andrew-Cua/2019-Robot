package frc.robot.utilities.StateControl.ArmStates;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.Arm_Subsys;
import frc.robot.utilities.StateControl.IArmState;
import frc.robot.subsystems.Arm_Subsys.ArmSetpoints;
public class LowGoalState implements IArmState
{
    private Arm_Subsys arm;
    public LowGoalState(Arm_Subsys arm)
    {
        this.arm = arm;
    }

    @Override
    public void moveArmToBallPos()
    {
        arm.setMagicSetpoint(ArmSetpoints.kLowGoal.getSetpoint());
    }

    @Override
    public void moveArmToHatchPos() {
        arm.setMagicSetpoint(ArmSetpoints.kLowGoal.getHatchSetpoint());
    }

    @Override
    public void updateSmartDashboard()
    {
        SmartDashboard.putString("Arm Position", "Low Goal");
    }
}