/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edinarobotics.Zephyr.commands;

import com.edinarobotics.Zephyr.Components;
import com.edinarobotics.Zephyr.subsystems.Shooter;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 * @author GreenMachine
 */
public class SetShooterSpeedCommand extends Command {

    private Shooter shooter;
    private double speed;
    
    public SetShooterSpeedCommand(double speed){
        super("SetShooterSpeed");
        shooter = Components.getInstance().shooter;
        this.speed = speed;
        requires(shooter);
    }
    
    protected void initialize() {
        shooter.setSetpoint(speed);
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
