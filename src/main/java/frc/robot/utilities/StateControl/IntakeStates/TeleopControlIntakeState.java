package frc.robot.utilities.StateControl.IntakeStates;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.subsystems.Intake_Subsys;
import frc.robot.utilities.StateControl.IIntakeState;

public class TeleopControlIntakeState implements IIntakeState
{
    private Intake_Subsys intake;
    public TeleopControlIntakeState (Intake_Subsys intake)
    {
        this.intake = intake;
    }

    @Override
    public void setIntakeToAngle()
    {
        intake.actuateIntake(Robot.m_oi.getControlStick().getY());
    }

    @Override
    public void updateSmartDashboard()
    {
        SmartDashboard.putString("Intake Position", "N/a, under manual control");
    }
}