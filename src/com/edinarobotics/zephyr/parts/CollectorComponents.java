package com.edinarobotics.zephyr.parts;

import edu.wpi.first.wpilibj.Relay;

/**
 *Wraps the conveyor belt, collector lifter and spinner into a class.
 */
public class CollectorComponents {
    private Relay collectorLift;
    private Relay collectorSpin;
    private Relay conveyor;
    public CollectorComponents(int lift, int spin, int conveyor){
        collectorLift = new Relay(lift);
        collectorSpin = new Relay(spin);
        this.conveyor = new Relay(conveyor);
    }
    /*
     * Lower the conveyor if liftDirection is 1, lower it if it's -1 otherwise
     * stop it.
     */
    public void lift(int liftDirection){
        switch(liftDirection){
                case 1:collectorLift.set(Relay.Value.kForward); break;
                case -1: collectorLift.set(Relay.Value.kReverse); break;
                default: collectorLift.set(Relay.Value.kOff); break;
        }
    }
    /*
     * Spin the collector if collecotState is true, otherwise stop it.
     */
    public void collect(boolean collectState){
        collectorSpin.set((collectState?Relay.Value.kReverse:Relay.Value.kOff));
    }
    /*
     * Spin the conveyor if collectState is true, otherwise stop it.
     */
    public void conveyorMove(boolean collectState){
        conveyor.set((collectState?Relay.Value.kReverse:Relay.Value.kOff));
    }
    
}