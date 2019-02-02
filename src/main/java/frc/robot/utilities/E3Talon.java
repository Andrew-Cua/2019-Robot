package frc.robot.utilities;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class E3Talon extends TalonSRX
{
    public E3Talon(int Id, FeedbackDevice encoder, boolean sensorPhase)
    {
        super(Id);
        configFactoryDefault();
        this.configSelectedFeedbackSensor(encoder, 0, 30);
        setSensorPhase(sensorPhase);
    }

    public void configurePIDF(double kP, double kI, double kD, double kF)
    {
        this.config_kP(0, kP);
        this.config_kI(0, kI);
        this.config_kD(0, kD);
        this.config_kF(0, kF);
    }

    public void configureMotionMagic()
    {
        this.configMotionAcceleration(Conversions.MOTIONACCEL);
        this.configMotionCruiseVelocity(Conversions.MOTIONVEL);
    }

    public void setMagicSetpoint(double setpoint)
    {
        this.set(ControlMode.MotionMagic, setpoint);
    }
}