package frc.robot.utilities.StateControl.WristStates;

import frc.robot.subsystems.Wrist_Subsys;
import frc.robot.utilities.StateControl.IWristState;
import frc.robot.*;

public class TeleopControlState implements IWristState
{
    private Wrist_Subsys wrist;
    public TeleopControlState(Wrist_Subsys wrist)
    {
        this.wrist = wrist;
    }

    @Override
    public void ballSetpoint()
    {
        wrist.setPos((-(Robot.m_oi.getIntakeController().getRawAxis(3)-1)/2)*6800);
    }

    @Override
    public void hatchSetpoint()
    {
        wrist.setPos((-(Robot.m_oi.getIntakeController().getRawAxis(3)-1)/2)*6800);
    }

    public void updateSD()
    {
        return;
    }
}