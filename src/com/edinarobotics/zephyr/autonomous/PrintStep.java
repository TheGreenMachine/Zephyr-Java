package com.edinarobotics.zephyr.autonomous;

import com.edinarobotics.utils.autonomous.AutonomousStep;

/**
 *
 */
public class PrintStep extends AutonomousStep{
    String message;
    
    public PrintStep(String message){
        this.message = message;
    }
    
    public void start(){
        System.out.println(message);
    }
    
    
    public boolean isFinished(){
        return true;
    }
}
