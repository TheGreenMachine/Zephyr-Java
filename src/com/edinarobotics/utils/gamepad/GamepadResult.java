package com.edinarobotics.utils.gamepad;

/**
 * Represents the state of both of a Gamepad's joysticks.
 * Can be used to filter these joysticks (deadzoning, scaling, etc.).
 */
public class GamepadResult {
    private double lx;
    private double ly;
    private double rx;
    private double ry;
    
    /**
     * Creates a GamepadResult from the cartesian coordinates of two joysticks.
     * @param lx the left x-value.
     * @param ly the left y-value.
     * @param rx the right x-value.
     * @param ry  the right y-value.
     */
    public GamepadResult(double lx,double ly,double rx,double ry){
        this.lx = lx;
        this.ly = ly;
        this.rx = rx;
        this.ry = ry;
        
    }
    
    public double getLeftX(){
        return lx;
    }
    
    public double getLeftY(){
        return ly;
    }
    
    public double getRightX(){
        return rx;
    }
    
    public double getRightY(){
        return ry;
    }
}
