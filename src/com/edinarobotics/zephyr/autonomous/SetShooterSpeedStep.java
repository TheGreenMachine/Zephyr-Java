package com.edinarobotics.zephyr.autonomous;

import com.edinarobotics.utils.autonomous.AutonomousStep;
import com.edinarobotics.zephyr.Zephyr;

/**
 * An autonomous step that sets the shooter firing speed and then exits.
 */
public class SetShooterSpeedStep extends AutonomousStep{
    private double rotateSpeed;
    private Zephyr robot;
    
    /**
     * Constructs a SetShooterSpeedStep that sets the shooter firing
     * speed to the given value.
     * @param rotateSpeed The value that should be passed to the shooter motor
     * controller. The speed to which the shooter should be set.
     * @param robot The {@link Zephyr} object to use to set the motor powers.
     */
    public SetShooterSpeedStep(double rotateSpeed, Zephyr robot){
        this.rotateSpeed = rotateSpeed;
        this.robot = robot;
    }
    
    /**
     * Sets the shooter speed to the given value, {@code rotateSpeed}.
     */
    public void start(){
        robot.shooterSpeed = rotateSpeed;
        robot.mechanismSet();
    }
    
    /**
     * Always returns {@code true}. this step is always finished.
     * @return {@code true}
     */
    public boolean isFinished(){
        return true;
    }
}
