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
public class ChangeShooterSpeedCommand extends Command {

    private Shooter shooter;
    private double change;
    
    public ChangeShooterSpeedCommand(double change){
        super("ChangeShooterSpeed");
        shooter = Components.getInstance().shooter;
        this.change = change;
        requires(shooter);
    }
    
    protected void initialize() {
        shooter.changeSetpoint(change);
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
