package com.edinarobotics.zephyr.autonomous;

import com.edinarobotics.utils.autonomous.AutonomousStep;
import com.edinarobotics.zephyr.Zephyr;

/**
 * An autonomous step that sets the deploy direction of the shooter.
 * It can either raise the shooter into the robot, lower the shooter to the
 * ground, or stop the collector deploy motor.
 */
public class SetCollectorDeployStep extends AutonomousStep{
    /**
     * Raises the shooter off the ground back into the robot.
     */
    public static final int DEPLOY_UP = -1;
    
    /**
     * Lowers the shooter down onto the field so that it can be used.
     */
    public static final int DEPLOY_DOWN = 1;
    
    /**
     * Stops the shooter deploy motor. The collector will stop deploying
     * at its current position.
     */
    public static final int DEPLOY_STOP = 0;
    
    private int deployDirection;
    private Zephyr robot;
    
    /**
     * Constructs a SetCollectorDeployStep that will set the collector deploy
     * motor's state.
     * @param deployDirection the direction to which the collector deploy motor
     * should be set. Should be set with the constants {@link #DEPLOY_DOWN},
     * {@link #DEPLOY_UP}, and {@link #DEPLOY_STOP}.
     * @param robot the {@link Zephyr} object to use to set the collector
     * deploy state.
     */
    public SetCollectorDeployStep(int deployDirection, Zephyr robot){
        this.deployDirection = deployDirection;
        this.robot = robot;
    }
    
    /**
     * Sets the collector's deployment state.
     */
    public void start(){
        robot.collectorLift = deployDirection;
        robot.mechanismSet();
    }
    
    /**
     * Always returns {@code true}. This step is always finished.
     * @return {@code true}.
     */
    public boolean isFinished(){
        return true;
    }
}
