package frc.robot.utilities.StateControl.WristStates;

import frc.robot.subsystems.Wrist_Subsys;
import frc.robot.subsystems.Wrist_Subsys.WristSepoint;
import frc.robot.utilities.StateControl.IWristState;
public class LowGoalState implements IWristState
{
    private Wrist_Subsys wrist;
    public LowGoalState(Wrist_Subsys wrist)
    {
        this.wrist = wrist;
    }
    @Override
    public void ballSetpoint()
    {
        wrist.setPos(WristSepoint.kLow.getBall());
    }

    @Override
    public void hatchSetpoint()
    {
        wrist.setPos(WristSepoint.kLow.getHatch());
    }

    @Override
    public void updateSD()
    {

    }
}