package frc.robot.utilities;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
public class E3DistanceSensor
{
    private I2C distanceSensor;
    private final int MAX_BYTES = 32;
    private byte[] data;
    private int address;
    private double currentDistance;

    public E3DistanceSensor(Port port, int address)
    {
        this.distanceSensor = new I2C(port, address);
        this.data = new byte[MAX_BYTES];
        this.address = address;
    }

    private byte readData()
    {
        byte[] data = new byte[MAX_BYTES];
        distanceSensor.read(address, MAX_BYTES, data);
        return data[MAX_BYTES -1];
    }
    public double getDistance()
    {
        this.currentDistance = (double)readData();
        return this.currentDistance;
    }

    public void updateSmartDashboard()
    {
        SmartDashboard.putNumber("Distance: ", (double)readData());
        SmartDashboard.putBoolean("Is Connected", isConnected());
    }

    public boolean isConnected()
    {
        return distanceSensor.addressOnly();
    }

}