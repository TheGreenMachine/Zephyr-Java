package com.edinarobotics.zephyr.autonomous;

import com.edinarobotics.utils.autonomous.AutonomousStep;
import edu.wpi.first.wpilibj.DriverStation;

/**
 *
 */
public class PrintStep extends AutonomousStep{
    String message;
    
    public PrintStep(String message){
        this.message = message;
    }
    
    public void start(){
        String timeValue = new Double(DriverStation.getInstance().getMatchTime()).toString();
        System.out.println("@"+timeValue+" seconds: "+message);
    }
    
    
    public boolean isFinished(){
        return true;
    }
}
