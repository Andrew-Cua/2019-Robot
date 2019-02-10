package frc.robot.subsystems;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import frc.robot.utilities.*;
import frc.robot.utilities.StateControl.IIntakeState;
import frc.robot.utilities.StateControl.IntakeStates.TeleopControlIntakeState;
import frc.robot.subsystems.Arm_Subsys.ArmSetpoints;
public class Intake_Subsys extends Subsystem
{

    private static Intake_Subsys instance = new Intake_Subsys();

    private Solenoid shootPiston;

    private TalonSRX leftIntakeMotor;
    private TalonSRX rightIntakeMotor;
    private E3Talon intakeEffector;
    private Value solenoidState = Value.kOff;

    private IIntakeState teleopControl;
    private IIntakeState state;
    private Intake_Subsys()
    {
        this.shootPiston = new Solenoid(RobotMap.extendChannel);

        this.leftIntakeMotor  = new TalonSRX(0);
        this.rightIntakeMotor = new TalonSRX(1);
        this.intakeEffector   = new E3Talon(3, FeedbackDevice.CTRE_MagEncoder_Relative, false);

        this.intakeEffector.configurePIDF(0, 0, 0, 0.02);
        rightIntakeMotor.setInverted(true);

        this.teleopControl = new TeleopControlIntakeState(this);
        this.state = teleopControl;
    }

    public void controlLoop()
    {
        state.setIntakeToAngle();
        state.updateSmartDashboard();
    }


    public void extendPiston()
    {
        shootPiston.set(true);
    }

    public void retractPiston()
    {
        shootPiston.set(false);
    }

    public boolean hasShotPiston()
    {
        return solenoidState == Value.kForward;
    }

    public void actuateIntake(double pow)
    {
        intakeEffector.set(ControlMode.PercentOutput, pow);
    }


    public void runIntakeMotors()
    {
        leftIntakeMotor.set(ControlMode.PercentOutput, 1);
        rightIntakeMotor.set(ControlMode.PercentOutput,1);
    }

    public void stopIntakeMotors()
    {
        leftIntakeMotor.set(ControlMode.PercentOutput, 0);
        leftIntakeMotor.set(ControlMode.PercentOutput,0);
    }

    public static Intake_Subsys getInstance()
    {
        return instance;
    }

    public void initDefaultCommand()
    {

    }

}