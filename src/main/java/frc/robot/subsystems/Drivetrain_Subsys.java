
package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import frc.robot.commands.*;
public class Drivetrain_Subsys extends PIDSubsystem 
{

  private static Drivetrain_Subsys instance = new Drivetrain_Subsys();
  private CANSparkMax frontLeft,
                      backLeft,
                      frontRight,
                      backRight;
  private AHRS Navx = new AHRS(I2C.Port.kMXP);

  private double kP = 0,
                 kI = 0,
                 kD = 0;
  private Drivetrain_Subsys()
  {
    super("Drivetrain", 0, 0, 0);

    frontLeft = new CANSparkMax(RobotMap.frontLeft, MotorType.kBrushless);
    backLeft  = new CANSparkMax(RobotMap.backLeft, MotorType.kBrushless);
    frontRight= new CANSparkMax(RobotMap.frontRight, MotorType.kBrushless);
    backRight = new CANSparkMax(RobotMap.backRight, MotorType.kBrushless);

    frontRight.setInverted(true);
    backRight.setInverted(true);

    backLeft.follow(frontLeft);
    backRight.follow(frontRight);

  }

  public void drive(double x, double y)
  {
    double leftPower  = y - x;
    double rightPower = y + x;

    set(leftPower, rightPower);
  }


  private void set(double left, double right)
  {
    frontLeft.set(left);
    frontRight.set(right);
  }

  public double returnPIDInput()
  {
    return Navx.getAngle();
  }

  public void usePIDOutput(double input)
  {

  }

  public void logSpeed()
  {
    System.out.print(frontLeft.get());
    System.out.println(frontRight.get());
  } 

  //all methods above this point
  public static Drivetrain_Subsys getInstance()
  {
    return instance;
  }
  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new Drive_Command());
  }
}
