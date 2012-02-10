package com.edinarobotics.zephyr.autonomous;

import com.edinarobotics.utils.autonomous.AutonomousStep;
import com.edinarobotics.zephyr.Zephyr;

/**
 * An autonomous step that sets the position of the shooter's piston.
 */
public class ShooterPistonControlStep extends AutonomousStep{
    private boolean ballLoaderUp;
    private Zephyr robot;
    
    /**
     * Constructs a new SetDriveSpeedStep that sets the position of the piston
     * to the given position.
     * @param ballLoaderUp Whether or not the piston should be moved to the 'up'
     * position. {@code true} moves the piston up, {@code false} moves down.
     * @param robot The {@link Zephyr} object to use to set the piston state.
     */
    public ShooterPistonControlStep(boolean ballLoaderUp, Zephyr robot){
        this.ballLoaderUp = ballLoaderUp;
        this.robot = robot;
    } 
    
    /**
     * Sets the piston state.
     */
    public void start(){
        robot.ballLoaderUp = ballLoaderUp;
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
