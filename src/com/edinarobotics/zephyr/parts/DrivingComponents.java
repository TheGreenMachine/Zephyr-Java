package com.edinarobotics.zephyr.parts;

import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Relay;

/**
 *Contains functions to modify the left and right driving motors along with the
 * super shifters
 */
public class DrivingComponents {
    private Jaguar leftMotors;
    private Jaguar rightMotors;
    private Relay shifters;
    /*
     * Intializes leftMotors, rightMotors and shifters with left, right, and shifters
     */
    public DrivingComponents(int left, int right, int shifters){
        leftMotors = new Jaguar(left);
        rightMotors = new Jaguar(right);
        this.shifters = new Relay(shifters);
    }
    /*
     * Set the left jaguars to left and the right jaguars to right
     */
    public void setDrivingSpeed(double left, double right){
        leftMotors.set(left);
        rightMotors.set(right);
    }
    /*
     * shift the shifters based on boolean high
     */
    public void shift(boolean high){
        shifters.set((high?Relay.Value.kReverse:Relay.Value.kOff));
    }
    
}
