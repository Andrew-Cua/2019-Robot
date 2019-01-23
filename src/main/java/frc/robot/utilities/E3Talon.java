package frc.robot.utilities;

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

    public void configureMotionMagic()
    {
        
    }
}