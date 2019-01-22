package frc.robot.utilities;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;

public class E3SparkMax extends CANSparkMax
{
    private CANEncoder sparkEncoder;
    private CANPIDController pidController;
    public E3SparkMax(int port)
    {
        super(port, MotorType.kBrushless);
        this.sparkEncoder = this.getEncoder();
        this.pidController = this.getPIDController();
    }


    public double getPosition()
    {
        return Conversions.inchesToRev(sparkEncoder.getPosition());
    }

    public double getVelocity()
    {
        return sparkEncoder.getVelocity();
    }

    public void setPIDConsts(int kSlot,  double kP, double kI, double kD)
    {
        pidController.setP(kP, kSlot);
        pidController.setI(kI, kSlot);
        pidController.setD(kI, kSlot);
    }

    public void setReference(double rotations)
    {
        //if(this.getInverted()) {rotations = -rotations;}
        pidController.setReference(rotations, ControlType.kPosition);
    }

    public void setOutputRange(double min, double max)
    {
        pidController.setOutputRange(min, max);
    }
}