package com.edinarobotics.zephyr.parts;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStationEnhancedIO;

/**
 *
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
     * Returns the state of a digital input on the DriverStationEnhancedIO
     * @param port gives the input to get.
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
