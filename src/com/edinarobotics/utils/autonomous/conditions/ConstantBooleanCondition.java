package com.edinarobotics.utils.autonomous.conditions;

import com.edinarobotics.utils.autonomous.BooleanCondition;

/**
 * A {@link BooleanCondition} that returns a constant, given value.
 */
public class ConstantBooleanCondition implements BooleanCondition{
    private boolean value;
    
    /**
     * Constructs a new BooleanCondition that always returns the given value.
     * @param value the {@code boolean} value to be returned.
     */
    public ConstantBooleanCondition(boolean value){
        this.value = value;
    }
    
    /**
     * Always returns the given {@code boolean} value.
     * @return {@code value} given in the constructor,
     * {@link #ConstantBooleanCondition(boolean)}.
     */
    public boolean get(){
        return value;
    }
}
