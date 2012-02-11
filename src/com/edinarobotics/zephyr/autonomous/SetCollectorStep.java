package com.edinarobotics.zephyr.autonomous;

import com.edinarobotics.utils.autonomous.AutonomousStep;
import com.edinarobotics.zephyr.Zephyr;

/**
 * An autonomous step that turns the collector's collecting motor on and off.
 */
public class SetCollectorStep extends AutonomousStep{
    private boolean collectorOn;
    private Zephyr robot;
    
    /**
     * Constructs a SetCollectorStep that will set the collector's motor
     * to the given state.
     * @param collectorOn whether or not the collector motor should be running.
     * {@code true} starts the motor, {@code false} stops it.
     * @param robot the {@link Zephyr} object to use to set the collector's
     * state.
     */
    public SetCollectorStep(boolean collectorOn, Zephyr robot){
        this.collectorOn = collectorOn;
        this.robot = robot;
    }
    
    /**
     * Sets the collector's motor to the given state, {@code collectorOn}.
     */
    public void start(){
        robot.collectorSpin = collectorOn;
        robot.mechanismSet();
    }
    
    /**
     * Always returns {@code true}. This step is always finished.
     * @return {@code true}
     */
    public boolean isFinished(){
        return true;
    }    
}
