package com.edinarobotics.zephyr.parts;

import edu.wpi.first.wpilibj.Relay;

/**
 *Wraps the conveyor belt, collector lifter and spinner into a class.
 */
public class CollectorComponents {
    private Relay collectorLift;
    private Relay collectorSpin;
    private Relay conveyor;
    
    /**
     * Constructs a CollectorComponents using the given channels for its
     * sub-components.
     * @param lift The channel of the lifting spike relay of the collector.
     * @param spin The channel of the collecting motor spike relay of the
     * collector
     * @param conveyor the channel of the spike relay that powers the conveyor
     * belt within the collector.
     */
    public CollectorComponents(int lift, int spin, int conveyor){
        collectorLift = new Relay(lift);
        collectorSpin = new Relay(spin);
        this.conveyor = new Relay(conveyor);
    }
    
    /**
     * Sets the current deployment direction of the collector.
     * @param liftDirection The direction that the collector should be deployed.
     * {@code 1} will lower the collector to the ground, {@code -1} will
     * lift the collector into the robot, and {@code 0} will stop the
     * collector's deployment.
     */
    public void lift(int liftDirection){
        switch(liftDirection){
                case 1:collectorLift.set(Relay.Value.kForward); break;
                case -1: collectorLift.set(Relay.Value.kReverse); break;
                default: collectorLift.set(Relay.Value.kOff); break;
        }
    }
    
    /**
     * Sets whether or not the collector's collection motor should be powered.
     * @param collectState Turns the collector on or off. {@code true} starts
     * the collector and will collect balls, {@code false} stops the collector.
     */
    public void collect(boolean collectState){
        collectorSpin.set((collectState?Relay.Value.kReverse:Relay.Value.kOff));
    }
    
    /**
     * Sets whether or not the internal conveyor belt should move collected
     * balls up to the shooter.
     * @param conveyorState Turns the internal conveyor belt on and off.
     * {@code true} turns the conveyor belt on, {@code false} turns it off.
     */
    public void conveyorMove(boolean conveyorState){
        conveyor.set((conveyorState?Relay.Value.kReverse:Relay.Value.kOff));
    }
    
}