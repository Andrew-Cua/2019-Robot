package frc.robot.utilities.StateControl.WristStates;

import frc.robot.utilities.StateControl.IWristState;
import frc.robot.subsystems.Wrist_Subsys;
import frc.robot.subsystems.Wrist_Subsys.WristSepoint;
public class HighGoalState implements IWristState
{
    private Wrist_Subsys wrist;
    public HighGoalState(Wrist_Subsys wrist)
    {
        this.wrist = wrist;
    }

    @Override
    public void ballSetpoint()
    {
        wrist.setPos(WristSepoint.kHigh.getBall());
    }

    @Override
    public void hatchSetpoint()
    {
        wrist.setPos(WristSepoint.kHigh.getHatch());
    }
    @Override
    public void updateSD()
    {

    }
}