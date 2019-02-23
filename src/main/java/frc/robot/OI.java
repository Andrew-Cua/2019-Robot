/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.*;
import frc.robot.commands.ArmStateCommands.*;
import frc.robot.subsystems.Arm_Subsys.ArmSetpoints;

public class OI {
  private Joystick intakeStick = new Joystick(0);
  private XboxController driveController = new XboxController(1);
  private JoystickButton ballSeekerButton;
  private JoystickButton drivePipelineButton;
  private JoystickButton seekTapeButton;
  private JoystickButton shootBallButton;
  private JoystickButton reverseBallButton;
  private JoystickButton toggleHatchIntake;
  private JoystickButton setLowGoalHatchButton;
  private JoystickButton setMidGoalHatchButton;
  private JoystickButton setHighGoalHatchButton;
  private JoystickButton setLowGoalBallButton;
  private JoystickButton setMidGoalBallButton;
  private JoystickButton setHighGoalBallButton;
  private JoystickButton setNeutralArmButton;
  private JoystickButton shiftGear;
  private JoystickButton setNeutralTwo;
  private JoystickButton setHatchTwo;
  public OI()
  {
    toggleHatchIntake  = new JoystickButton(intakeStick, 3);
    reverseBallButton  = new JoystickButton(intakeStick, 1);
    shootBallButton    = new JoystickButton(intakeStick, 2);
    seekTapeButton     = new JoystickButton(driveController, 3);
    ballSeekerButton   = new JoystickButton(driveController, 2);
    drivePipelineButton = new JoystickButton(driveController, 4);
    setLowGoalHatchButton = new JoystickButton(intakeStick, 11);
    setLowGoalBallButton  = new JoystickButton(intakeStick, 12);
    setMidGoalHatchButton = new JoystickButton(intakeStick, 9);
    setMidGoalBallButton = new JoystickButton(intakeStick, 10);
    setHighGoalHatchButton = new JoystickButton(intakeStick, 7);
    setHighGoalBallButton  = new JoystickButton(intakeStick, 8);
    setNeutralArmButton    = new JoystickButton(intakeStick, 6);
    setNeutralTwo = new JoystickButton(intakeStick, 5);
    setHatchTwo  = new JoystickButton(intakeStick, 4);
    
    shiftGear = new JoystickButton(driveController, 6);
    toggleHatchIntake.whenPressed(new toggleHatchIntakeCommand());
    shootBallButton.whileHeld(new RunIntakeCommand());
    reverseBallButton.whileHeld(new ReverseIntakeMotorsCommand());
    ballSeekerButton.whileHeld(new SeekBallCommand());
    seekTapeButton.whileHeld(new SeekTapeCommand());
    setLowGoalHatchButton.whenPressed(new HatchGoalState(ArmSetpoints.kLowGoal));
    setLowGoalBallButton.whenPressed(new BallStateCommand(ArmSetpoints.kLowGoal));
    setMidGoalHatchButton.whenPressed(new HatchGoalState(ArmSetpoints.kMidGoal));
    setMidGoalBallButton.whenPressed(new BallStateCommand(ArmSetpoints.kMidGoal));
    setHighGoalHatchButton.whenPressed(new HatchGoalState(ArmSetpoints.kHighGoal));
    setHighGoalBallButton.whenPressed(new BallStateCommand(ArmSetpoints.kHighGoal));
    setNeutralArmButton.whenPressed(new HatchGoalState(ArmSetpoints.kNeutral));
    shiftGear.whenPressed(new shiftGear());
    setNeutralTwo.whenPressed(new HatchGoalState(ArmSetpoints.kNeutral));
    setHatchTwo.whenPressed(new toggleHatchIntakeCommand());
  }  

  public XboxController getDriveController()
  {return driveController;}
  /*public Joystick getControlStick()
  {return controlStick;}*/

  public Joystick getIntakeController()
  {return intakeStick;}
  //haha i am the code prankster and i have pranked your code
}
