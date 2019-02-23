/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.InvertType;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.ctre.phoenix.motorcontrol.ControlMode;
import frc.robot.utilities.E3Talon;
import frc.robot.Robot;
import frc.robot.commands.*;
import frc.robot.utilities.StateControl.IWristState;
import frc.robot.utilities.StateControl.WristStates.*;
import frc.robot.commands.Wrist.*;

/**
 * Add your docs here.
 */


public class Wrist_Subsys extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  public enum WristSepoint
  {
    kNeutral(6788,6788),
    kLow(6772,1925),
    kMid(6789,1923),
    kHigh(6733,1609);

    private double ballSetpoint;
    private double hatchSetpoint;
    private WristSepoint(double ballSetpoint, double hatchSetpoint)
    {
      this.ballSetpoint = ballSetpoint;
      this.hatchSetpoint = hatchSetpoint;
    }

    public double getBall()
    {
      return ballSetpoint;
    }

    public double getHatch()
    {
      return hatchSetpoint;
    }
  }

  private E3Talon wrist;
  private static Wrist_Subsys wrist_Subsys = new Wrist_Subsys();
  private boolean isHatch = false;
  private IWristState neutralState;
  private IWristState lowGoalState;
  private IWristState midGoalState;
  private IWristState highGoalState;
  private IWristState teleopState;
  private IWristState state;
  private Wrist_Subsys()
  {
    wrist = new E3Talon(13, FeedbackDevice.CTRE_MagEncoder_Relative, true);
    //wrist.setInverted(InvertType.InvertMotorOutput);
    wrist.config_kF(0, 1.1443);
    wrist.config_kP(0,1.705);
    wrist.configMotionCruiseVelocity(500);
    wrist.configMotionAcceleration(886);

    neutralState = new NeutralState(this);
    lowGoalState = new LowGoalState(this);
    midGoalState = new MidGoalState(this);
    highGoalState = new HighGoalState(this);
    teleopState = new TeleopControlState(this);
    state = neutralState;
  }

  public void setState(WristSepoint setpoint)
    {
        switch(setpoint)
        {
            case kHigh:
                state = highGoalState;
                break;
            case kMid:
                state = midGoalState;
                break;
            case kLow:
                state = lowGoalState;
                break;
            case kNeutral:
                state = neutralState;
        }
    }



  public void printSensorPos(double set)
  {
    SmartDashboard.putNumber("Sensor", wrist.getSelectedSensorPosition());
    SmartDashboard.putNumber("Error", set - wrist.getSelectedSensorPosition());
  }

  public void setPos(double set)
  {
    wrist.set(ControlMode.MotionMagic, set);
  }
  public void resetEncoder()
  {
    wrist.setSelectedSensorPosition(0);
  }

  public void sliderPos()
    {
    SmartDashboard.putNumber("Slider", ((Robot.m_oi.getIntakeController().getRawAxis(3)+1)/2)*6800);
  }
  public static Wrist_Subsys getInstance()
  {return wrist_Subsys;}


  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new ActuateIntakeCommand());
  }
}
