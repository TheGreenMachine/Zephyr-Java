package com.edinarobotics.zephyr.parts;

import com.edinarobotics.utils.sensors.FilterDouble;
import edu.wpi.first.wpilibj.AnalogChannel;

/**
 *Handles the sonars filtering and conversion to shooter powers.
 */
public class SonarComponents {
    private FilterDouble filter;
    private AnalogChannel sonar;
    private final double SONAR_CONSTANT = 5;
    private final double LAUNCH_ANGLE = 52.0; //in DEGREES
    
    public SonarComponents(int channel, FilterDouble filter){
        this.filter = filter;
        sonar = new AnalogChannel(channel);
    }
    /**
     * Returns the sonars filtered value in inches.
     */
    public double getFilteredValue(){
        return filter.filter(sonar.getValue()/2 + SONAR_CONSTANT);
    }
    /*
     * Returns the raw value from the sonar in inches.
     */
    public double getRawValue(){
        return sonar.getValue()/2 + SONAR_CONSTANT;
    }
    /**
     * Returns the power based on the distance.
     */
    public double getPower(){
        return 0.0;
    };
}
