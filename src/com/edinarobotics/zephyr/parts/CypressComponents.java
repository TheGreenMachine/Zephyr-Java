package com.edinarobotics.zephyr.parts;

import com.edinarobotics.zephyr.Zephyr;
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
            e.printStackTrace();
            Zephyr.exceptionProblem = true;
            return false;
        }
    }
    
    /**
     * Returns the state of all digital inputs on the DriverStationEnhancedIO
     * set. Defaults to {@code 0}.
     * @return The state of all inputs on the DriverStationEnhancedIO board
     * or {@code 0} if any exception occurs while fetching.
     */
    public short getDigitals(){
        try{
            return cypress.getDigitals();
        }catch(Exception e){
            Zephyr.exceptionProblem = true;
            return 0;
        }
    }
}
