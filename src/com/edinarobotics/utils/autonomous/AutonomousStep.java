package com.edinarobotics.utils.autonomous;

/**
 * This class is used to define a step of an autonomous program.
 */
public abstract class AutonomousStep{
    /**
     * Called once when this autonomous step starts.
     * This method can be used to initialize any values at the start of this
     * step.
     */
    public void start(){
        
    }
    
    /**
     * Called repeatedly while this step is the active step.
     * This method should be used for any actions that occur throughout this
     * step.
     */
    public void run(){
        
    }
    
    /**
     * Used to indicate whether or not this step is finished.
     * Once this method returns {@code true} {@link #stop()} will be called
     * 
     * @return {@code true} if the step is complete, {@code false} otherwise.
     */
    public abstract boolean isFinished();
    
    /**
     * Called once when this step is no longer the active step.
     * This method is called when this step is finished or if this step
     * is interrupted.
     */
    public void stop(){
        
    }
}
