
package com.edinarobotics.utils.gamepad.filters;

import com.edinarobotics.utils.gamepad.*;
import com.sun.squawk.util.MathUtils;


public class ScalingFilter implements GamepadFilter {
    private final double SLOWDOWN = 2;

    public GamepadResult filter(GamepadResult toFilter)
    {
        
        double lx = inRange(scale(toFilter.getLeftX()));
        double ly = inRange(scale(toFilter.getLeftY()));
        double rx = inRange(scale(toFilter.getRightX()));
        double ry = inRange(scale(toFilter.getRightY()));
        
        return new GamepadResult(lx, ly, rx, ry);
    }
    
    public GamepadResult filter(GamepadResult toFilter, boolean slowdown)
    {
        GamepadResult toReturn = filter(toFilter);
        if(slowdown){
        return new GamepadResult(toReturn.getLeftX()/SLOWDOWN, 
                                 toReturn.getLeftY()/SLOWDOWN, 
                                 toReturn.getRightX()/SLOWDOWN, 
                                 toReturn.getRightY()/SLOWDOWN);
        }
        return toReturn;
    }
    
    private double scale(double toScale)
    {
        return sgn(toScale)*MathUtils.pow(Math.abs(toScale), 2);
    }
    
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