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

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

  private JoystickButton ballFollowerButton;
  private JoystickButton ballSeekerButton;
  private JoystickButton drivePipelineButton;
  private JoystickButton turnToAngleButton;
  private JoystickButton resetNavxButton;
  public OI()
  {
    resetNavxButton    = new JoystickButton(driveStick, 5);
    ballFollowerButton = new JoystickButton(driveStick, 7);
    ballSeekerButton   = new JoystickButton(driveStick, 12);
    drivePipelineButton = new JoystickButton(driveStick, 2);
    turnToAngleButton   = new JoystickButton(driveStick, 3);
    resetNavxButton.whenPressed(new ResetNavxCommand());
    drivePipelineButton.whenPressed(new ChangeToDrivePipelineCommand());
    ballFollowerButton.whileHeld(new BallFollower());
    ballSeekerButton.whileHeld(new SeekBallCommand());
    turnToAngleButton.whenPressed(new TurnToAngleCommand(0));
  }
  //// CREATING BUTTONS
  // One type of button is a joystick button which is any button on a
  //// joystick.
  // You create one by telling it which joystick it's on and which button
  // number it is.
  // Joystick stick = new Joystick(port);
  // Button button = new JoystickButton(stick, buttonNumber);

  // There are a few additional built in buttons you can use. Additionally,
  // by subclassing Button you can create custom triggers and bind those to
  // commands the same as any other Button.
  //// TRIGGERING COMMANDS WITH BUTTONS
  // Once you have a button, it's trivial to bind it to a button in one of
  // three ways:

  // Start the command when the button is pressed and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenPressed(new ExampleCommand());

  // Run the command while the button is being held down and interrupt it once
  // the button is released.
  // button.whileHeld(new ExampleCommand());

  // Start the command when the button is released and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenReleased(new ExampleCommand());

  //private XboxController driveController = new XboxController(0);
  private Joystick driveStick = new Joystick(0);

  public Joystick getDriveController()
  {return driveStick;}

  
}
