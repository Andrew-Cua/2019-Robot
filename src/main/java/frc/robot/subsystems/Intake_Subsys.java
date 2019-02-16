package frc.robot.subsystems;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.InvertType;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import frc.robot.utilities.*;
import frc.robot.utilities.StateControl.IIntakeState;
import frc.robot.utilities.StateControl.IntakeStates.TeleopControlIntakeState;
import frc.robot.subsystems.Arm_Subsys.ArmSetpoints;
import frc.robot.commands.*;
public class Intake_Subsys extends Subsystem
{

    private static Intake_Subsys instance = new Intake_Subsys();

    //private Solenoid shootPiston;

    private TalonSRX leftIntakeMotor;
    private TalonSRX rightIntakeMotor;
    private E3Talon intakeEffector;

    private IIntakeState teleopControl;
    private IIntakeState state;
    private Intake_Subsys()
    {
        //this.shootPiston = new Solenoid(RobotMap.extendChannel);

        this.rightIntakeMotor = new TalonSRX(12);
        //this.intakeEffector   = new E3Talon(13, FeedbackDevice.CTRE_MagEncoder_Relative, false);
        rightIntakeMotor.setInverted(true);
        this.teleopControl = new TeleopControlIntakeState(this);
        this.state = teleopControl;
    }

    public void controlLoop()
    {
        state.setIntakeToAngle();
        state.updateSmartDashboard();
    }



    public void runIntakeMotors()
    {
       // leftIntakeMotor.set(ControlMode.PercentOutput, 1);
        rightIntakeMotor.set(ControlMode.PercentOutput,0.2);
    }

    public void stopIntakeMotors()
    {
        //IntakeMotor.set(ControlMode.PercentOutput, 0);
        rightIntakeMotor.set(ControlMode.PercentOutput,0);
    }
    public void reverseIntakeMotors()
    {
        rightIntakeMotor.set(ControlMode.PercentOutput, -0.75);
    }

    public static Intake_Subsys getInstance()
    {
        return instance;
    }

    public void initDefaultCommand()
    {
        setDefaultCommand(new StopIntakeMotors());
    }

}