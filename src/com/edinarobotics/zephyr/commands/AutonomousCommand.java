/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edinarobotics.Zephyr.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 * @author GreenMachine
 */
public class AutonomousCommand extends CommandGroup {
    
    private int autoState = 0;
    
    public AutonomousCommand(int autoState){
        this.autoState = autoState;
        
        if (autoState == 0){
            
            addSequential(new DriveXTimeCommand(2, 0.5)); // Drive for 2 seconds, with a velocity of 0.5
            addSequential(new WaitCommand(5)); // Wait 5 seconds
            
            addSequential(new DriveXTimeCommand(3, 0.5)); // Drive for 3 seconds, with a velocity of 0.5
            
             
        }
    }
    
}
