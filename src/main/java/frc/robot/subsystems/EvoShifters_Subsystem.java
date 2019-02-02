/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class EvoShifters_Subsystem extends Subsystem {
  public enum driveGear
  {
    kLowGear,
    kHighGear;
  }

  private driveGear currentDriveGear = driveGear.kLowGear;
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private DoubleSolenoid leftShifter;
  private DoubleSolenoid rightShifter;

  private static EvoShifters_Subsystem shifters = new EvoShifters_Subsystem();
  private EvoShifters_Subsystem()
  {
    this.leftShifter = new DoubleSolenoid(RobotMap.leftSolenoidOne, RobotMap.leftSolenoidTwo);
    this.rightShifter = new DoubleSolenoid(RobotMap.rightSolenoidOne, RobotMap.rightSolenoidTwo);
  }



  public void highGear()
  {
    leftShifter.set(Value.kForward);
    rightShifter.set(Value.kForward);
  }
  public void lowGear()
  {
    leftShifter.set(Value.kReverse);
    rightShifter.set(Value.kReverse);
  }

  public void toggleGear()
  {
    if(currentDriveGear == driveGear.kLowGear)
    {
      highGear();
      currentDriveGear = driveGear.kHighGear;
    }else if(currentDriveGear == driveGear.kHighGear)
    {
      lowGear();
      currentDriveGear = driveGear.kLowGear;
    }
  }


  public static EvoShifters_Subsystem getInstance()
  {
    return shifters;
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
