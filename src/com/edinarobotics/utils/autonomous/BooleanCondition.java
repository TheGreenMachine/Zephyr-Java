package com.edinarobotics.utils.autonomous;

/**
 * Represents a function that can be called to get the value of a boolean
 * condition.
 */
public interface BooleanCondition {
    /**
     * Returns the value of the boolean condition. This value can be the
     * result of any other function calls.
     */
    public boolean get();
}
