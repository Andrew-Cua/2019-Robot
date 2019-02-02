package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import frc.robot.utilities.Conversions;
import frc.robot.utilities.E3Talon;

public class Arm_Subsys extends Subsystem
{
    public enum ArmSetpoints
    {
        kNeutral(100),
        kLowGoal(200),
        kMidGoal(300),
        kHighGoal(400);
        private double setpoint;
        private ArmSetpoints(double tickPos)
        {
            this.setpoint = tickPos;
        }
        public double getSetpoint()
        {
            return setpoint;
        }
    }
    private E3Talon masterArm, slaveArm;
    private ArmSetpoints currentSetpoint = ArmSetpoints.kNeutral;
    private ArmSetpoints seekSetpoint;
    private static Arm_Subsys instance = new Arm_Subsys();
    private Arm_Subsys()
    {
        masterArm = new E3Talon(10, FeedbackDevice.CTRE_MagEncoder_Relative, true);
        slaveArm  = new E3Talon(10, FeedbackDevice.CTRE_MagEncoder_Relative, false);
        slaveArm.follow(masterArm);
        slaveArm.setInverted(InvertType.OpposeMaster);

        masterArm.configureMotionMagic();
        masterArm.configurePIDF(Conversions.motionkP, Conversions.motionkI, Conversions.motionkD, Conversions.motionkF);
    }
    

    public void rotateArm(double power)
    {
        double maxTick = 1000;
        double setpoint = power * maxTick;
        masterArm.setMagicSetpoint(setpoint); 
    }

    public void goToSetpoint(ArmSetpoints setpoint)
    {
        masterArm.setMagicSetpoint(setpoint.getSetpoint());
        seekSetpoint = setpoint;
    }

    public boolean onTarget()
    {
        double distance = seekSetpoint.getSetpoint() - masterArm.getSelectedSensorPosition();
        if(distance < 50)
        {
            currentSetpoint = seekSetpoint;
            return true;
        }
        return false;
    }


    public static Arm_Subsys getInstance()
    {
        return instance; 
    }
    public void initDefaultCommand()
    {

    }
}