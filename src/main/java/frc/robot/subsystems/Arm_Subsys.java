package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import frc.robot.utilities.Conversions;
import frc.robot.utilities.E3Talon;
import frc.robot.utilities.StateControl.IArmState;
import frc.robot.utilities.StateControl.ArmStates.HighGoalState;
import frc.robot.utilities.StateControl.ArmStates.LowGoalState;
import frc.robot.utilities.StateControl.ArmStates.MidGoalState;
import frc.robot.utilities.StateControl.ArmStates.NeutralState;
import frc.robot.utilities.StateControl.ArmStates.TeleopControlState;
import frc.robot.Robot;
public class Arm_Subsys extends Subsystem
{
    public enum ArmSetpoints
    {
        

        kNeutral(100),
        kLowGoal(200),
        kMidGoal(300),
        kHighGoal(400);


        private int setpoint;
        private ArmSetpoints(int tickPos)
        {
            this.setpoint = tickPos;
        }
        public int getSetpoint()
        {
            return setpoint;
        }
    }
    private E3Talon masterArm, slaveArm;
    //states the arm can be in
    private IArmState NeutralState;
    private IArmState TeleopControlState;
    private IArmState LowGoalState;
    private IArmState MidGoalState;
    private IArmState HighGoalState;
    private IArmState state;
    private static Arm_Subsys instance = new Arm_Subsys();
    private Arm_Subsys()
    {
        NeutralState = new NeutralState(this);
        TeleopControlState = new TeleopControlState(this);
        LowGoalState = new LowGoalState(this);
        MidGoalState = new MidGoalState(this);
        HighGoalState = new HighGoalState(this);
        state = NeutralState;
        masterArm = new E3Talon(10, FeedbackDevice.CTRE_MagEncoder_Relative, true);
        slaveArm  = new E3Talon(10, FeedbackDevice.CTRE_MagEncoder_Relative, false);
        slaveArm.follow(masterArm);
        slaveArm.setInverted(InvertType.OpposeMaster);

        masterArm.configureMotionMagic();
        masterArm.configurePIDF(Conversions.motionkP, Conversions.motionkI, Conversions.motionkD, Conversions.motionkF);
    }

    public void controlLoop()
    {
        if(Robot.m_oi.getControlStick().getThrottle() > 0 + 0.1)
        {
            if(state != TeleopControlState)
            {
                SmartDashboard.putString("Errors", "Cannot change state, put the throttle down");
            }
            state = TeleopControlState;
        }
        state.moveArmToPos();
        state.updateSmartDashboard();
    }

    public void setMagicSetpoint(int ticks)
    {
        masterArm.setMagicSetpoint(ticks);
    }
    
    public void updateState(IArmState nextState)
    {
        this.state = nextState;
    }

    public void moveToPos()
    {
        state.moveArmToPos();
    }

    public void updateSmartDashboard()
    {
        state.updateSmartDashboard();
    }

    public void zeroSensors()
    {
        masterArm.setSelectedSensorPosition(0);
        slaveArm.setSelectedSensorPosition(0);
    }

    public static Arm_Subsys getInstance()
    {
        return instance; 
    }
    public void initDefaultCommand()
    {

    }
}