package com.edinarobotics.zephyr.autonomous;

import com.edinarobotics.utils.autonomous.AutonomousStep;
import com.edinarobotics.zephyr.Zephyr;
import com.edinarobotics.zephyr.parts.CollectorComponents;

/**
 * An autonomous step that can be used to control the internal conveyor belt
 * on Zephyr. This step can be used to load balls into the shooter for firing.
 */
public class SetConveyorStep extends AutonomousStep{
    private int conveyorState;
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
        this((conveyorOn?CollectorComponents.CONVEYOR_UP:CollectorComponents.CONVEYOR_STOP), robot);
    }
    
    /**
     * Constructs a new SetConveyorStep that sets the internal conveyor to the
     * given state, {@code conveyorState}.
     * @param conveyorState The state to which the conveyor will be set.
     * One of: {@link CollectorComponents#CONVEYOR_DOWN},
     * {@link CollectorComponents#CONVEYOR_UP} or
     * {@link CollectorComponents#CONVEYOR_STOP}
     * @param robot 
     */
    public SetConveyorStep(int conveyorState, Zephyr robot){
        this.conveyorState = conveyorState;
        this.robot = robot;
    }
    
    /**
     * Sets the conveyor to the requested state, {@code conveyorOn}.
     */
    public void start(){
        robot.convMove = conveyorState;
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
