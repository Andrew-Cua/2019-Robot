/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.Drivetrain_Subsys;
import frc.robot.subsystems.EvoShifters_Subsystem;
import frc.robot.subsystems.Arm_Subsys;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.HatchIntake;
import frc.robot.subsystems.Intake_Subsys;
import frc.robot.subsystems.Superstructure_Subsys;
import frc.robot.subsystems.Wrist_Subsys;
import frc.robot.utilities.*;
import frc.robot.utilities.Vision.PipelineMode;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  public static ExampleSubsystem m_subsystem = new ExampleSubsystem();
  public static OI m_oi;
  public static Drivetrain_Subsys drivetrain_Subsys;
  public static Arm_Subsys arm_Subsys;
  public static Intake_Subsys intake_Subsys;
  public static Superstructure_Subsys superstructure_Subsys;
  public static Vision limeLight;
  public static EvoShifters_Subsystem shifters_Subsystem;
  public static Wrist_Subsys wrist_Subsys;
  public static HatchIntake hatchIntake;
  NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");


  Command m_autonomousCommand;
  SendableChooser<Command> m_chooser = new SendableChooser<>();

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    shifters_Subsystem = EvoShifters_Subsystem.getInstance();
    drivetrain_Subsys = Drivetrain_Subsys.getInstance();
    arm_Subsys = Arm_Subsys.getInstance();
    intake_Subsys = Intake_Subsys.getInstance();
    superstructure_Subsys = Superstructure_Subsys.getInstance();
    wrist_Subsys = Wrist_Subsys.getInstance();
    hatchIntake = HatchIntake.getInstance();
    limeLight = new Vision();
    m_oi = new OI();
    // chooser.addOption("My Auto", new MyAutoCommand());
    SmartDashboard.putData("Auto mode", m_chooser);
    arm_Subsys.zeroSensors();
    wrist_Subsys.resetEncoder();
    CameraServer.getInstance().startAutomaticCapture();
  }

  @Override
  public void robotPeriodic() 
  {
    superstructure_Subsys.updateDistance();
    superstructure_Subsys.updateSmartDashboard();
    drivetrain_Subsys.updateSmartDashboard();
    wrist_Subsys.printSensorPos(0);
    wrist_Subsys.sliderPos();
    arm_Subsys.getVelocity();
    shifters_Subsystem.update();
  }

  @Override
  public void disabledInit() {

  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }
  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_chooser.getSelected();

    if (m_autonomousCommand != null) {
      m_autonomousCommand.start();
    }
  }

  @Override
  public void autonomousPeriodic() {
  }

  @Override
  public void teleopInit() {
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
    limeLight.setTrackTarget(PipelineMode.NormalMode);
    
    }
    

  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
    limeLight.updateVision();
  }
  @Override
  public void testPeriodic() {}
}
