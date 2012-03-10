package com.edinarobotics.utils.gamepad.filters;

import com.edinarobotics.utils.gamepad.GamepadFilter;
import com.edinarobotics.utils.gamepad.GamepadResult;
import com.sun.squawk.util.MathUtils;

/**
 *
 */
public class QuarticScalingFilter implements GamepadFilter{
    public QuarticScalingFilter(){
        
    }
    
    public GamepadResult filter(GamepadResult toFilter){
        double lx = inRange(scale(toFilter.getLeftX()));
        double ly = inRange(scale(toFilter.getLeftY()));
        double rx = inRange(scale(toFilter.getRightX()));
        double ry = inRange(scale(toFilter.getRightY()));
        
        return new GamepadResult(lx, ly, rx, ry);
    }
    
    private double scale(double toScale){
        return sgn(toScale)*Math.abs(MathUtils.pow(toScale,4));
    }
    
    /**
     * Returns the sign of the value passed in. Represented by a +1, 0 or -1
     * @param val the number to evaluate the sign on
     * @return either 1, 0 or -1
     */
    private int sgn(double val)
    {
        if(val==0){
            return 0;
        }
        if(val<0){
            return -1;
        }
            return 1;
    }
    
    /**
     * Converts the value passed in to a value within -1 to 1 if it it is not within that range
     * @param a the number to be converted
     * @return a value between -1 and 1
     */
    private double inRange(double a)
    {
        if(a>1)
        {
            return 1;
        }
        if(a<-1)
        {
            return -1;
        }
        return a;
    }
}
