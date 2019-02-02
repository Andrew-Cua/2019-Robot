package frc.robot.subsystems;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
public class Intake_Subsys extends Subsystem
{

    private static Intake_Subsys instance = new Intake_Subsys();

    private DoubleSolenoid shootPiston;

    private VictorSPX leftIntakeMotor;
    private VictorSPX rightIntakeMotor;
    private Value solenoidState = Value.kOff;
    private Intake_Subsys()
    {
        this.shootPiston = new DoubleSolenoid(RobotMap.extendChannel, RobotMap.retractChannel);

        this.leftIntakeMotor  = new VictorSPX(0);
        this.rightIntakeMotor = new VictorSPX(1);

        rightIntakeMotor.setInverted(true);
    }


    public void extendPiston()
    {
        shootPiston.set(Value.kForward);
        solenoidState = Value.kForward;
    }

    public void retractPiston()
    {
        shootPiston.set(Value.kReverse);
        solenoidState = Value.kReverse;
    }

    public boolean hasShotPiston()
    {
        return solenoidState == Value.kForward;
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