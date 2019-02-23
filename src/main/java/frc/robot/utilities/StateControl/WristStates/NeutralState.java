package frc.robot.utilities.StateControl.WristStates;

import frc.robot.utilities.StateControl.IWristState;
import frc.robot.subsystems.Wrist_Subsys;
import frc.robot.subsystems.Wrist_Subsys.WristSepoint;
public class NeutralState implements IWristState
{
    private Wrist_Subsys wrist;
    public NeutralState(Wrist_Subsys wrist)
    {
        this.wrist = wrist;
    }
    @Override
    public void ballSetpoint()
    {
        wrist.setPos(WristSepoint.kNeutral.getBall());
    }

    @Override
    public void hatchSetpoint()
    {
        wrist.setPos(WristSepoint.kNeutral.getHatch());
    }
    @Override
    public void updateSD()
    {
        
    }
}