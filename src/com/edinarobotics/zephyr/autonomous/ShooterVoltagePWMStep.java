package com.edinarobotics.zephyr.autonomous;

import com.edinarobotics.zephyr.Zephyr;
import com.edinarobotics.zephyr.parts.ShooterComponents;

/**
 * Attempts to set the shooter to the desired velocity using the current battery
 * voltage to adjust the set PWM value.
 */
public class ShooterVoltagePWMStep extends SetShooterSpeedStep{
    
    /**
     * Creates a new ShooterVoltagePWMStep that attempts to set the shooter to
     * the desired velocity.
     * @param desiredVelocity The velocity in rotations per second to target.
     * @param robot The {@link Zephyr} object to use to set motor powers.
     */
    public ShooterVoltagePWMStep(double desiredVelocity, Zephyr robot){
        super(ShooterComponents.getVoltagePWM(desiredVelocity), robot);
    }
}
