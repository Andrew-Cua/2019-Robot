package frc.robot.utilities.StateControl.IntakeStates;
import frc.robot.utilities.StateControl.*;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
public class NeutralIntakeState implements IIntakeState
{
    private Intake_Subsys intake;
    public NeutralIntakeState(Intake_Subsys intake)
    {
        this.intake = intake;
    }

    @Override
    public void setIntakeToAngle()
    {
    }
    @Override
    public void updateSmartDashboard()
    {
        SmartDashboard.putString("Intake Position", "Stashed");
    }
}