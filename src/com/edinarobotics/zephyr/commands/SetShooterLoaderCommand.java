/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edinarobotics.Zephyr.commands;

import com.edinarobotics.Zephyr.Components;
import com.edinarobotics.Zephyr.subsystems.Shooter;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 * @author GreenMachine
 */
public class SetShooterLoaderCommand extends Command {

    private Shooter shooter;
    private DoubleSolenoid.Value value;
    
    public SetShooterLoaderCommand(DoubleSolenoid.Value value){
        super("SetShooterLoader");
        shooter = Components.getInstance().shooter;
        this.value = value;
        requires(shooter);
    }
    
    protected void initialize() {
        shooter.setLoader(value);
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
