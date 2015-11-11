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
public class SetShooterStateCommand extends Command {

    private Shooter shooter;
    private boolean state;
    
    public SetShooterStateCommand(boolean state){
        super("SetShooterState");
        shooter = Components.getInstance().shooter;
        this.state = state;
        requires(shooter);
    }
    
    protected void initialize() {
        shooter.setOn(state);
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
