package com.edinarobotics.utils.autonomous;

/**
 * Represents a {@link BooleanCondition} that can be used as the condition of
 * a loop. This condition can be reset when re-entering the loop.
 */
public interface LoopCondition extends BooleanCondition{
    /**
     * Resets the loop condition. This method may be called before or after
     * the loop has run.
     * This method should reset the condition to its initial state if this
     * is at all possible.
     */
    public void reset();
    
    /**
     * Increments the loop condition. Called once per loop iteration.
     */
    public void increment();
}
