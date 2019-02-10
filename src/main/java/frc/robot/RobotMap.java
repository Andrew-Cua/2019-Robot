/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
  // For example to map the left and right motors, you could define the
  // following variables to use with your drivetrain subsystem.
  // public static int leftMotor = 1;
  // public static int rightMotor = 2;

  // If you are using multiple modules, make sure to define both the port
  // number and the module. For example you with a rangefinder:
  // public static int rangefinderPort = 1;
  // public static int rangefinderModule = 1;

  //motors
  public static int frontLeft = 10,
                    backLeft  = 11,
                    frontRight= 12,
                    backRight = 13,
                    leftSlave = 14,
                    rightSlave = 15;

  //shifting solenoids
  public static int leftSolenoidOne  = 0,
                    leftSolenoidTwo  = 1,
                    rightSolenoidOne = 2,
                    rightSolenoidTwo = 3;

  //intake solenoid
  public static int extendChannel = 4,
                    retractChannel = 5;
}
