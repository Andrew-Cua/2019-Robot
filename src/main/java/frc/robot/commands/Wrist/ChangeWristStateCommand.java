/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Wrist;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;
import frc.robot.subsystems.Wrist_Subsys;
import frc.robot.subsystems.Wrist_Subsys.WristSepoint;
/**
 * Add your docs here.
 */
public class ChangeWristStateCommand extends InstantCommand {
  /**
   * Add your docs here.
   */
  private WristSepoint setpoint;
  public ChangeWristStateCommand(WristSepoint setpoint) {
    
    super();
    this.setpoint = setpoint;
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called once when the command executes
  @Override
  protected void initialize() {
    Robot.wrist_Subsys.setState(setpoint);
  }

}
