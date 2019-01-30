
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
                      backRight;
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
    frontRight= new E3SparkMax(RobotMap.frontRight);
    backRight = new E3SparkMax(RobotMap.backRight);

    frontRight.setInverted(true);
    backRight.setInverted(true);

    frontLeft.setPIDConsts(0, 0.05, 0, 0);
    frontRight.setPIDConsts(0, 0.05, 0, 0);

    frontLeft.setOutputRange(-1,1);
    frontRight.setOutputRange(-1,1);

    //backLeft.follow(frontLeft);
    //backRight.follow(frontRight);
    backRight.follow(frontLeft);
    backRight.follow(frontRight);

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
    backLeft.set(left);
    frontRight.set(right);
    backRight.set(right);
  }

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

  public void logSpeed()
  {
    //System.out.println(frontLeft.get());
    System.out.println(frontRight.getPosition());
  } 

  public void positionControl(double rotations)
  {
    frontLeft.setReference(rotations);
    frontRight.setReference(rotations);
  }

  public void force()
  {
    set(1,1);
  }

  public void BallFollower() 
  {
    Robot.limeLight.setTrackTarget(PipelineMode.BallMode);

    double leftPower = 0, rightPower = 0;
    if(Robot.limeLight.getV() != 1)//checks if a target is in view
    {
        System.out.println("nothing is being seen");
        System.out.println("Current Value V: " + Robot.limeLight.getV());

        return;//exits method if nothing is seen and gives current value of "v"
    }
    //allows for only one method call and increased readability by making left and right power exist
    if (Robot.limeLight.getV() != 0 && Robot.limeLight.getX() > -7.5 && Robot.limeLight.getX() < 7.5)
    {
        leftPower  = -0.25;
        rightPower = -0.25;
        
    } else if (Robot.limeLight.getV() == 1 && Robot.limeLight.getX() > 7.5)
    {
        leftPower  =  -0.075;
        rightPower =  0.075;

    } else if (Robot.limeLight.getV() == 1 && Robot.limeLight.getX() < -7.5) 
    {
        leftPower  = 0.075;
        rightPower = -0.075;
    } 

    Robot.drivetrain_Subsys.set(leftPower, rightPower);

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

    //Hello this is my secret code. ;s,d,d,d,d,d,d,; xdxdxd sans undertale dab on the haters good b8 m8 i r8 8/8 xdxdxd

  }
  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new Drive_Command());
  }
}
