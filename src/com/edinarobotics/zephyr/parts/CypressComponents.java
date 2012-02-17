package com.edinarobotics.zephyr.parts;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStationEnhancedIO;

/**
 * Wraps the Cypress access into this class. Handles getting input from the 
 * Driver Station Enhanced IO
 */
public class CypressComponents {
    private DriverStationEnhancedIO cypress;
    /**
     * Creates an instance of the DriverStationEnhancedIO for the cypress.
     */
    public CypressComponents(){
        cypress = DriverStation.getInstance().getEnhancedIO();
    }
    
    /**
     * Returns the state of a digital input on the DriverStationEnhancedIO.
     * Defaults to {@code false}.
     * @param port The digital input number on the DriverStationEnhancedIO to
     * get.
     * @return The value of the digital input on the DriverStationEnhancedIO
     * input set. Or {@code false} if any exception occurs when fetching.
     */
    public boolean getDigital(int port){
        try{
            return cypress.getDigital(port);
        }
        catch(Exception e){
            return false;
        }
    }
}
