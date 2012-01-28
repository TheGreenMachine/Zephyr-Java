/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edinarobotics.zephyr.autonomous;

import com.edinarobotics.utils.autonomous.AutonomousStep;
import com.edinarobotics.zephyr.Zephyr;
import edu.wpi.first.wpilibj.Timer;

/**
 *
 * @author Danny
 */
public class ShooterStep extends AutonomousStep{
    
    Zephyr robot;
    Timer time;
    boolean isFinished;
   
    public ShooterStep(Zephyr robot)
    {
        this.robot = robot;
        time = new Timer();
        isFinished = false;
    }
    
    public void start()
    {
        robot.shooterSpeed = -0.80;
        robot.mechanismSet();
        time.start();
    }
    
    public void run()
    {
        System.out.println(time.get());
        if(3.0<=time.get()){
            robot.ballLoaderUp = true;
            robot.mechanismSet();
        }
        else{
            robot.mechanismSet();
        }
        
    }
    public boolean isFinished()
    {
        return isFinished;
    }
    
}
