package frc.robot.subsystems;

import edu.wpi.first.wpilibj.GenericHID.Hand;
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
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.commands.ArmCommands.*;
public class Arm_Subsys extends Subsystem
{
    public enum ArmSetpoints
    {
        

        kNeutral(0,0),
        kLowGoal(1980,850),
        kMidGoal(4200,2730),
        kHighGoal(6160,4700);

 
        private int setpoint;
        private int hatchSetpoint;
        private ArmSetpoints(int tickPos, int hatchSetpoint)
        {
            this.setpoint = tickPos;
            this.hatchSetpoint = hatchSetpoint;
        }
        public int getSetpoint()
        {
            return setpoint;
        }

        public int getHatchSetpoint()
        {
            return hatchSetpoint;
        }
    }
    private boolean isHatch = false;
    private TalonSRX masterArm, slaveArm;
    //states the arm can be in
    private IArmState NeutralState;
    private IArmState LowGoalState;
    private IArmState MidGoalState;
    private IArmState HighGoalState;
    private IArmState state;
    private int max = 0;
    private static Arm_Subsys instance = new Arm_Subsys();
    private Arm_Subsys()
    {
        NeutralState = new NeutralState(this);
        LowGoalState = new LowGoalState(this);
        MidGoalState = new MidGoalState(this);
        HighGoalState = new HighGoalState(this);
        setState(ArmSetpoints.kNeutral);
        masterArm = new TalonSRX(RobotMap.armMaster);
        slaveArm  = new TalonSRX(RobotMap.armSlave);
        masterArm.setInverted(InvertType.InvertMotorOutput);
        slaveArm.follow(masterArm);
        slaveArm.setInverted(InvertType.OpposeMaster);
        masterArm.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
        masterArm.setSensorPhase(true);
        masterArm.configMotionCruiseVelocity(700);
        masterArm.configMotionAcceleration(500);
        masterArm.config_kF(0, 5.05);
        masterArm.config_kP(0, 5.1);
        masterArm.config_kD(0, 0.00037);
    }

    public void controlLoop()
    {
        if(isHatch)
        {
            moveToHatchPos();
        }else if(!isHatch)
        {
            moveToBallPos();
        }
        updateSmartDashboard();
    }

    public void setState(ArmSetpoints setpoint)
    {
        switch(setpoint)
        {
            case kHighGoal:
                state = HighGoalState;
                SmartDashboard.putString("Current State", "HighGoal");
                break;
            case kMidGoal:
                state = MidGoalState;
                SmartDashboard.putString("Current State", "MidGoal");
                break;
            case kLowGoal:
                state = LowGoalState;
                SmartDashboard.putString("Current State", "LowGoal");
                break;
            case kNeutral:
                state = NeutralState;
                SmartDashboard.putString("Current State", "Neutral");
        }
    }

    public void setMagicSetpoint(int ticks)
    {
        masterArm.set(ControlMode.MotionMagic, ticks);
    }
    
    public void updateState(IArmState nextState)
    {
        this.state = nextState;
    }

    public void moveToBallPos()
    {
        state.moveArmToBallPos();
    }

    public void moveToHatchPos()
    {
        state.moveArmToHatchPos();
    }

    public void updateSmartDashboard()
    {
        state.updateSmartDashboard();
    }

    public void zeroSensors()
    {
        masterArm.setSelectedSensorPosition(0);
    }
    public void getVelocity()
    {
        if(masterArm.getSelectedSensorVelocity() > max)
            max = masterArm.getSelectedSensorVelocity();
        SmartDashboard.putNumber("Velocity", max);
        SmartDashboard.putNumber("ArmPos", masterArm.getSelectedSensorPosition());
    }

    public void setBall()
    {
        isHatch = false;
    }

    public void setHatch()
    {
        isHatch = true;
    }

    public static Arm_Subsys getInstance()
    {
        return instance; 
    }

    public void set(double pow)
    {
        masterArm.set(ControlMode.PercentOutput, -pow);
    }
    public void initDefaultCommand()
    {
       setDefaultCommand(new DefaultArmCommand());
    }
}