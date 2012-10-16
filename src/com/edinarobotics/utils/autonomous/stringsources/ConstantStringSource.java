package com.edinarobotics.utils.autonomous.stringsources;

import com.edinarobotics.utils.autonomous.StringSource;

/**
 * A {@link StringSource} that returns a constant, given String.
 */
public class ConstantStringSource implements StringSource{
    private String value;
    
    /**
     * Constructs a ConstantStringSource that returns the given String.
     * @param value 
     */
    public ConstantStringSource(String value){
        this.value = value;
    }
    
    public String getString(){
        return value;
    }
}
