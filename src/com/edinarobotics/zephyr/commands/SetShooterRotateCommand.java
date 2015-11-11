/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edinarobotics.Zephyr.commands;

import com.edinarobotics.Zephyr.Components;
import com.edinarobotics.Zephyr.subsystems.Shooter;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 * @author GreenMachine
 */
public class SetShooterRotateCommand extends Command {

    private Shooter shooter;
    private Relay.Value value;
    
    public SetShooterRotateCommand(Relay.Value value){
        super("SetShooterRotate");
        shooter = Components.getInstance().shooter;
        this.value = value;
        requires(shooter);
    }
    
    protected void initialize() {
        shooter.setRotation(value);
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
