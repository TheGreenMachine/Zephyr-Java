package com.edinarobotics.zephyr.autonomous;

import com.edinarobotics.utils.autonomous.AutonomousStep;
import com.edinarobotics.zephyr.Zephyr;
import edu.wpi.first.wpilibj.Timer;

/**
 * This class causes the robot to maintain its current state for a specified
 * length of time.
 */
public class IdleWaitStep extends AutonomousStep{
    private double idleTime;
    private Zephyr robot;
    private boolean isFinished;
    private Timer timer;
    
    /**
     * Constructs an IdleWaitStep that idles for a the specified length of time
     * in seconds.
     * @param waitTime The time in seconds to wait.
     * @param robot {@link Zephyr}
     */
    public IdleWaitStep(double waitTime, Zephyr robot){
        this.idleTime = waitTime;
        this.robot = robot;
        isFinished = false;
        timer = new Timer();
    }
    
    public void start(){
        timer.start();
    }
    
    public void run(){
        robot.mechanismSet();
        if(timer.get() >= idleTime){
            isFinished = true;
        }
    }
    
    public boolean isFinished(){
        return isFinished;
    }
}
