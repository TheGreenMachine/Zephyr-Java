/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edinarobotics.Zephyr.commands;

import com.edinarobotics.Zephyr.Components;
import com.edinarobotics.Zephyr.subsystems.Drivetrain;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 * @author GreenMachine
 */
public class DriveXTimeCommand extends Command {

    private Drivetrain drivetrain;
    private double time, speed;
    
    public DriveXTimeCommand(double time, double speed){
        super("DriveXTime");
        drivetrain = Components.getInstance().drivetrain;
        this.time = time;
        this.speed = speed;
        setTimeout(time);
    }
    
    protected void initialize() {
        
    }

    protected void execute() {
        drivetrain.setValues(speed, speed);
    }

    protected boolean isFinished() {
        return isTimedOut();
    }

    protected void end() {

    }

    protected void interrupted() {

    }
    
    
    
}
