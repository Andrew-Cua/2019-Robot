package frc.robot.subsystems;
import edu.wpi.first.wpilibj.command.Subsystem;


import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.Solenoid;
public class Intake_Subsys extends Subsystem
{

    private static Intake_Subsys instance = new Intake_Subsys();

    private Solenoid extendArms;
    private Solenoid retractArms;

    private VictorSPX leftIntakeMotor;
    private VictorSPX rightIntakeMotor;
    private Intake_Subsys()
    {
        this.extendArms  = new Solenoid(0);
        this.retractArms = new Solenoid(1);

        this.leftIntakeMotor  = new VictorSPX(0);
        this.rightIntakeMotor = new VictorSPX(1);

        rightIntakeMotor.setInverted(true);
    }


    public void openArms()
    {
        extendArms.set(true);
        retractArms.set(false);
    }

    public void closeArms()
    {
        extendArms.set(false);
        retractArms.set(true);
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