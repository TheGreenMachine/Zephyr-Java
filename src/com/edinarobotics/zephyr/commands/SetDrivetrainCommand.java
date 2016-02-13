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
public class SetDrivetrainCommand extends Command{

    private Drivetrain drivetrain;
    private double leftValue, rightValue;
    
    public SetDrivetrainCommand(double leftValue, double rightValue){
        super("SetDrivetrain");
        this.drivetrain = Components.getInstance().drivetrain;
        requires(drivetrain);
        this.leftValue = leftValue;
        this.rightValue = rightValue;
    }
    
    protected void initialize() {
        drivetrain.setValues(leftValue, rightValue);
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    }

    protected void interrupted() {
        
    }
    
}