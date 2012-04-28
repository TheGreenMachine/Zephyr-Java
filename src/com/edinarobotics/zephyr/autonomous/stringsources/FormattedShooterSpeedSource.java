package com.edinarobotics.zephyr.autonomous.stringsources;

import com.edinarobotics.utils.autonomous.StringSource;
import com.edinarobotics.zephyr.Components;

/**
 * A StringSource that returns a string expressing the current speed of the
 * shooter.
 */
public class FormattedShooterSpeedSource implements StringSource{
    
    /**
     * Constructs a new FormattedShooterSpeedSource.
     */
    public FormattedShooterSpeedSource(){
        
    }
    
    public String getString(){
        return "Shooter speed: "+Components.getInstance().shooter.getEncoderValue();
    }
}
