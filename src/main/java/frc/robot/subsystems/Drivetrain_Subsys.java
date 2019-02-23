
package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import frc.robot.utilities.E3SparkMax;
import frc.robot.utilities.Vision.PipelineMode;
import frc.robot.commands.*;
import frc.robot.*;
public class Drivetrain_Subsys extends PIDSubsystem 
{

  private static Drivetrain_Subsys instance = new Drivetrain_Subsys();
  private E3SparkMax frontLeft,
                      backLeft,
                      frontRight,
                      backRight,
                      leftSlave,
                      rightSlave;
  private AHRS Navx;

  private double kP = 0,
                 kI = 0,
                 kD = 0;
  private Drivetrain_Subsys()
  {
    super("Drivetrain", 0.017,0.00,0.0997);

    getPIDController().setInputRange(-360, 360);  
    getPIDController().setOutputRange(-1,1);
    getPIDController().setAbsoluteTolerance(10);
    getPIDController().setContinuous(true);
    
    //try {
      this.Navx = new AHRS(SPI.Port.kMXP);
    //} catch (Exception e) {
      //TODO: handle exception
    //}

    frontLeft = new E3SparkMax(RobotMap.frontLeft);
    backLeft  = new E3SparkMax(RobotMap.backLeft);
    leftSlave = new E3SparkMax(RobotMap.leftSlave);
    frontRight= new E3SparkMax(RobotMap.frontRight);
    backRight = new E3SparkMax(RobotMap.backRight);
    rightSlave= new E3SparkMax(RobotMap.rightSlave);

    frontRight.setInverted(true);
    backRight.setInverted(true);
    rightSlave.setInverted(true);

    frontLeft.setPIDConsts(0, 0.05, 0, 0);
    frontRight.setPIDConsts(0, 0.05, 0, 0);

    frontLeft.setOutputRange(-1,1);
    frontRight.setOutputRange(-1,1);

    backLeft.follow(frontLeft);
    leftSlave.follow(frontLeft);
    backRight.follow(frontRight);
    rightSlave.follow(frontRight);

  }

  public void drive(double y, double x)
  {
    double sensFactor = 0.75D;

    double yValPrime = Math.pow((sensFactor*y), 3) + ((1-sensFactor)*y);
    double xValPrime = Math.pow((sensFactor*x), 3) + ((1-sensFactor)*x);
    double leftPower  =  yValPrime - xValPrime;
    double rightPower =  yValPrime + xValPrime;

    set(leftPower, rightPower);
  }


  public void set(double left, double right)
  {
    frontLeft.set(left);
    frontRight.set(right);
  }

  @Override
  public double returnPIDInput()
  {
    double Angle = Navx.getAngle();
    if (Angle > 360){
      Navx.reset();
      Angle = Navx.getAngle();
    } else if (Angle < -360){
      Navx.reset();
      Angle = Navx.getAngle();
    } return Angle;
  }

  @Override
  public void usePIDOutput(double output)
  {
    if(getPIDController().getSetpoint() < 0){
      frontLeft.pidWrite(output);
      frontRight.pidWrite(-output);
    } else if (getPIDController().getSetpoint() > 0){
      frontLeft.pidWrite(-output);
      frontRight.pidWrite(output);
    }
    
  }

  public void positionControl(double rotations)
  {
    frontLeft.setReference(rotations);
    frontRight.setReference(rotations);
  }


  public void seekTarget()
  {
    //Robot.limeLight.setTrackTarget(PipelineMode.BallMode);
    double kP = 0.0052;
    double x = Robot.limeLight.getX();
    double y = Robot.limeLight.getY();
    boolean v = Robot.limeLight.getV() != 0;

    double power = -0.20; 
    if(!v){return;}

    if(v)
    {
      double headingAjust = x*kP;

      double leftPower = power - headingAjust;
      double rightPower = power + headingAjust;

      set(leftPower, rightPower);
    }

    
  }
  public void updateSmartDashboard()
  {
    SmartDashboard.putNumber("Gyro", Navx.getAngle());
  }

  public void resetNavx()
  {
    Navx.reset();
  }

  public void stop()
  {
    set(0,0);
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
