package com.edinarobotics.zephyr.parts;

import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Relay;

/**
 *Wraps the conveyor belt, collector lifter and spinner into a class.
 */
public class CollectorComponents {
    private Jaguar collectorLift;
    private Relay collectorSpin;
    private Relay conveyor;
    
    public static final int COLLECTOR_LIFT_UP = 1;
    public static final int COLLECTOR_LIFT_DOWN = -1;
    public static final int COLLECTOR_LIFT_STOP = 0;
    public static final int DEPLOY_UP_SIGN = 1;
    public static final int DEPLOY_DOWN_SIGN = -1;
    
    private static final double DEFAULT_DEPLOY_MULTIPLIER = 0.5;
    
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
        collectorLift = new Jaguar(lift);
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
                case 1: lift(DEFAULT_DEPLOY_MULTIPLIER); break;
                case -1: lift(DEFAULT_DEPLOY_MULTIPLIER); break;
                default: lift(DEFAULT_DEPLOY_MULTIPLIER); break;
        }
    }
    
    /**
     * Sets the collector deploy deploy motor to the given power [-1,1].
     * @param power The power to which the deployment motor should be set.
     */
    public void lift(double power){
        collectorLift.set(power);
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