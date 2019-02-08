package frc.robot.utilities;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
public class E3DistanceSensor
{
    private I2C distanceSensor;
    private final int MAX_BYTES = 4;
    private byte[] data;
    private int address;
    private double currentDistance;

    public E3DistanceSensor(Port port, int address)
    {
        this.distanceSensor = new I2C(port, address);
        this.data = new byte[MAX_BYTES];
        this.address = address;
    }

    private String readData()
    {
        byte[] data = new byte[10];
        distanceSensor.read(address, 10, data);
        return new String(data);
    }
    public double getDistance()
    {
        try {
            this.currentDistance = (double)Double.parseDouble(readData());    
        } catch (Exception e) {
            //TODO: handle exception
            this.currentDistance = -30;

        }
        
        return this.currentDistance;
    }

    public void updateSmartDashboard()
    {
        SmartDashboard.putNumber("Distance: ", getDistance());
        SmartDashboard.putBoolean("Is Connected", isConnected());
    }

    public boolean isConnected()
    {
        return distanceSensor.addressOnly();
    }

}