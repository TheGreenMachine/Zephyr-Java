/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edinarobotics.zephyr.autonomous;

import com.edinarobotics.utils.autonomous.AutonomousStep;
import com.edinarobotics.zephyr.Zephyr;

/**
 *
 * @author Danny
 */
public class ShooterStep extends AutonomousStep{
    
    Zephyr robot;
   
    public ShooterStep(Zephyr robot)
    {
        this.robot = robot;
    }
    
    public void start()
    {
        robot.shooterSpeed = -0.50;
        robot.mechanismSet();
    }
    
    public boolean isFinished()
    {
        return true;
    }
    
}
