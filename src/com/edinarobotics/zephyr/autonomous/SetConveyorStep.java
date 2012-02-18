package com.edinarobotics.zephyr.autonomous;

import com.edinarobotics.utils.autonomous.AutonomousStep;
import com.edinarobotics.zephyr.Zephyr;
import com.edinarobotics.zephyr.parts.CollectorComponents;

/**
 * An autonomous step that can be used to control the internal conveyor belt
 * on Zephyr. This step can be used to load balls into the shooter for firing.
 */
public class SetConveyorStep extends AutonomousStep{
    private boolean conveyorOn;
    private Zephyr robot;
    
    /**
     * Constructs a new SetConveyorStep that sets the internal conveyor to the
     * given state, {@code conveyorOn}.
     * @param conveyorOn The state to which the conveyor will be set.
     * {@code true} activates the conveyor {@code false} stops it.
     * @param robot The {@link Zephyr} object that is used to control the
     * conveyor.
     */
    public SetConveyorStep(boolean conveyorOn, Zephyr robot){
        this.conveyorOn = conveyorOn;
        this.robot = robot;
    }
    
    /**
     * Sets the conveyor to the requested state, {@code conveyorOn}.
     */
    public void start(){
        robot.convMove = (conveyorOn?CollectorComponents.CONVEYOR_UP:CollectorComponents.CONVEYOR_STOP);
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
