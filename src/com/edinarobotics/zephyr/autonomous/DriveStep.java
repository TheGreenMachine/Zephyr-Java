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
public class DriveStep extends AutonomousStep{
    Zephyr robot;
    
    public DriveStep(Zephyr robot)
    {
        this.robot = robot;
    }
    
   public void start()
   {
       robot.leftDrive = .5;
       robot.rightDrive = .5;
       for(int x = 0; x<2000; x++)
       {
           robot.mechanismSet();
           Timer.delay(.001);
           
       }
   }
   public boolean isFinished()
   {
       return true;
   }
   public void stop()
   {
       robot.stop();
   }
    
}
