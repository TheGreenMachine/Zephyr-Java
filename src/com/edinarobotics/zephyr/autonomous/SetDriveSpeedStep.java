package com.edinarobotics.zephyr.autonomous;

import com.edinarobotics.utils.autonomous.AutonomousStep;
import com.edinarobotics.zephyr.Zephyr;

/**
 * An autonomous step that sets the drive train speed and then exits.
 */
public class SetDriveSpeedStep extends AutonomousStep{
    private double leftSpeed;
    private double rightSpeed;
    private Zephyr robot;
    
    /**
     * Constructs a new SetDriveSpeedStep that sets the motor speeds
     * to the given speeds.
     * @param leftDriveSpeed the speed to which the left drive motors should
     * be set.
     * @param rightDriveSpeed the speed to which the right drive motors should
     * be set.
     * @param robot the {@link Zephyr} object to use to set the motor powers.
     */
    public SetDriveSpeedStep(double leftDriveSpeed, double rightDriveSpeed, Zephyr robot){
        leftSpeed = leftDriveSpeed;
        rightSpeed = rightDriveSpeed;
        this.robot = robot;
    }
    
    /**
     * Sets the motor speeds.
     */
    public void start(){
        robot.leftDrive = leftSpeed;
        robot.rightDrive = rightSpeed;
        robot.mechanismSet();
    }
    
    /**
     * Always returns {@code true}. This step is always done.
     * @return {@code true}.
     */
    public boolean isFinished(){
        return true;
    }
}
