package com.edinarobotics.utils.autonomous;

import edu.wpi.first.wpilibj.Timer;

/**
 * An {@link AutonomousStep} that can be used to add a timeout to another
 * {@link AutonomousStep}. This step will be finished when either the given
 * time has elapsed or the contained step has finished, whichever comes first.
 */
public class TimeoutStep extends AutonomousStep{
    private AutonomousStep step;
    private double timeout;
    private Timer timer;
    
    /**
     * Constructs a new TimeoutStep that ends the given {@link AutonomousStep}
     * when it is finished or if the given time has elapsed.
     * @param step The {@link AutonomousStep} to run.
     * @param timeout The maximum amount of time that {@code step} is allowed
     * to run.
     */
    public TimeoutStep(AutonomousStep step, double timeout){
        this.step = step;
        this.timeout = timeout;
        timer = new Timer();
    }
    
    public void start(){
        timer.reset();
        timer.start();
        step.start();
    }
    
    public void run(){
        step.run();
    }
    
    public void stop(){
        step.stop();
    }
    
    public boolean isFinished(){
        return step.isFinished() || (timer.get() >= timeout);
    }
}
