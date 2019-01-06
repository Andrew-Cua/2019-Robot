
package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Drivetrain_Subsys extends Subsystem {

  private static Drivetrain_Subsys instance = new Drivetrain_Subsys();
  private Drivetrain_Subsys()
  {

  }



  //all methods above this point
  public static Drivetrain_Subsys getInstance()
  {
    return instance;
  }
  @Override
  public void initDefaultCommand() {}
}
