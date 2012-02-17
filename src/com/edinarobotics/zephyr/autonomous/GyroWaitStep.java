package com.edinarobotics.zephyr.autonomous;

import com.edinarobotics.utils.autonomous.AutonomousStep;
import com.edinarobotics.zephyr.Zephyr;
import edu.wpi.first.wpilibj.Gyro;

/**
 * A step that waits for the Gyro to report that we have completed a precise
 * turn of a certain number of degrees.
 */
public class GyroWaitStep extends AutonomousStep{
    private double angle;
    private Gyro gyro;
    private Zephyr robot;
    
    /**
     * Constructs a new GyroWaitStep that will wait for the robot to have
     * turned a certain number of degrees given by {@code angle}.
     * @param angle The angle in degrees to wait for (can be negative).
     * @param gyro The {@link Gyro} sensor to use to measure turned degrees.
     * @param robot The robot that is turning, used to call
     * {@link Zephyr#mechanismSet()} to keep components alive.
     */
    public void GyroWaitStep(double angle, Gyro gyro, Zephyr robot){
        this.angle = angle;
        this.gyro = gyro;
        this.robot = robot;
    }
    
    public void start(){
        gyro.reset();
    }
    
    public void run(){
        robot.mechanismSet();
    }
    
    /**
     * Returns {@code true} when the given number of degrees have gone by in
     * a turn.
     * @return {@code true} if we have turned the given number of degrees,
     * {@code false} otherwise.
     */
    public boolean isFinished(){
        if(angle <= 0){
            return gyro.getAngle() <= angle;
        }
        else{
            return gyro.getAngle() >= angle;
        }
    }
}
