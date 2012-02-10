package com.edinarobotics.zephyr.autonomous;

import com.edinarobotics.utils.autonomous.AutonomousStep;
import com.edinarobotics.zephyr.Zephyr;

/**
 * An autonomous step that sets the speed of the motor that rotates the shooter.
 */
public class SetShooterRotateStep extends AutonomousStep{
    private double shooterRotate;
    private Zephyr robot;
    
    /**
     * Constructs a new SetShooterRotateStep that sets the speed of the
     * shooter's rotation motor to the given speed.
     * @param shooterRotation The speed of the shooter's rotation motor.
     * @param robot The {@link Zephyr} object to use to set the speed state.
     */
    public SetShooterRotateStep(double shooterRotation, Zephyr robot){
        shooterRotate = shooterRotation;
        this.robot = robot;
    }
    
    /**
     * Sets the state of the shooter's rotation motor.
     */
    public void start(){
        robot.shooterRotateSpeed = shooterRotate;
        robot.mechanismSet();
    }
    
    /**
     * Always returns {@code true}. This step is always done.
     * @return {@code true}
     */
    public boolean isFinished(){
        return true;
    }
}
