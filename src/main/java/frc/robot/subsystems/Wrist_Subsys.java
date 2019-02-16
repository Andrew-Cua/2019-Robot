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
import com.ctre.phoenix.motorcontrol.ControlMode;
import frc.robot.utilities.E3Talon;
import frc.robot.commands.*;

/**
 * Add your docs here.
 */
public class Wrist_Subsys extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private E3Talon wrist;
  private static Wrist_Subsys wrist_Subsys = new Wrist_Subsys();
  private Wrist_Subsys()
  {
    wrist = new E3Talon(13, FeedbackDevice.CTRE_MagEncoder_Absolute, true);
    wrist.setInverted(InvertType.InvertMotorOutput);
  }

  public void actuateIntake(double pow)
  {
      wrist.set(ControlMode.PercentOutput, pow*0.75);
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
