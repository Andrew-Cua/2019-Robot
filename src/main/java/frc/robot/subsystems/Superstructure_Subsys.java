package frc.robot.subsystems;
import edu.wpi.first.wpilibj.command.Subsystem;

enum IntakeState
{
    ARMS_OPEN(),
    ARMS_CLOSED(),
    INTAKE_RAISED,
    INTAKE_LOWERD;
}


public class Superstructure_Subsys extends Subsystem
{
    private static Superstructure_Subsys instance = new Superstructure_Subsys();

    private IntakeState mArmState;
    private IntakeState mIntakeState;
    private Superstructure_Subsys()
    {

    }

    public void setArmState(IntakeState state)
    {
        mArmState = state;
    }

    public void setIntakeState(IntakeState state)
    {
        mIntakeState = state;
    }

    public IntakeState getArmState()
    {
        return mArmState;
    }
    public IntakeState getIntakeState()
    {
        return mIntakeState;
    }

    public static Superstructure_Subsys getInstance()
    {
        return instance;
    }
    public void initDefaultCommand()
    {

    }
}