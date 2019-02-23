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

public class Climber_Subsys extends Subsystem {

  private DoubleSolenoid frontClimber;
  private DoubleSolenoid backClimber;
  private boolean frontIsExtended = false;
  private boolean backIsExtended  = false;
  private static Climber_Subsys climber = new Climber_Subsys();
  private Climber_Subsys()
  {
    frontClimber = new DoubleSolenoid(RobotMap.frontClimberOut, RobotMap.frontClimberIn);
    backClimber = new DoubleSolenoid(RobotMap.backClimberOut, RobotMap.backClimberIn);
  }

  public void extendFront()
  {
    frontClimber.set(Value.kForward);
  }

  public void extendBack()
  {
    frontClimber.set(Value.kForward);
  }

  public void retractFront()
  {
    frontClimber.set(Value.kReverse);
  }

  public void retractBack()
  {
    backClimber.set(Value.kReverse);
  }

  public void toggleFront()
  {
    if(frontIsExtended)
    {
      retractFront();
      frontIsExtended = false;
    }else if(!frontIsExtended)
    {
      extendFront();
      frontIsExtended = true;
    }
  }

  public void toggleBack()
  {
    if(backIsExtended)
    {
      retractBack();
      backIsExtended = false;
    }else if(!backIsExtended)
    {
      extendBack();
      backIsExtended = true;
    }
  }

  public void toggleClimber()
  {
    toggleFront();
    toggleBack();
  }



  public static Climber_Subsys getInstance()
  {
    return climber;
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
