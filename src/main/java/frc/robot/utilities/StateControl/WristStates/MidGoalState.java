package frc.robot.utilities.StateControl.WristStates;
import frc.robot.subsystems.*;
import frc.robot.subsystems.Wrist_Subsys.WristSepoint;
import frc.robot.utilities.StateControl.*;
public class MidGoalState implements IWristState
{
    private Wrist_Subsys wrist;
    public MidGoalState(Wrist_Subsys wrist)
    {
        this.wrist = wrist;
    }

    @Override
    public void ballSetpoint()
    {
        wrist.setPos(WristSepoint.kMid.getBall());
    }

    @Override
    public void hatchSetpoint()
    {
        wrist.setPos(WristSepoint.kMid.getHatch());
    }
    @Override
    public void updateSD()
    {

    }
}