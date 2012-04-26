package com.edinarobotics.zephyr.autonomous;

import com.edinarobotics.utils.autonomous.AutonomousStep;
import com.edinarobotics.utils.autonomous.StringSource;
import com.edinarobotics.utils.autonomous.stringsources.ConstantStringSource;
import edu.wpi.first.wpilibj.DriverStation;

/**
 *
 */
public class PrintStep extends AutonomousStep{
    private StringSource source;
    
    public PrintStep(String message){
        this.source = new ConstantStringSource(message);
    }
    
    public PrintStep(StringSource source){
        this.source = source;
    }
    
    public void start(){
        String timeValue = new Double(DriverStation.getInstance().getMatchTime()).toString();
        System.out.println("@"+timeValue+" seconds: "+source.getString());
    }
    
    
    public boolean isFinished(){
        return true;
    }
}
