/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.ArmCommands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;
import frc.robot.subsystems.Arm_Subsys.ArmSetpoints;

/**
 * Add your docs here.
 */
public class HatchGoalState extends InstantCommand {
  /**
   * Add your docs here.
   */
  private ArmSetpoints setpoint;
  public HatchGoalState(ArmSetpoints setpoint) {
    super();
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    this.setpoint = setpoint;
  }

  // Called once when the command executes
  @Override
  protected void initialize() {
    Robot.arm_Subsys.setState(setpoint);
    Robot.arm_Subsys.setHatch();
  }

}
