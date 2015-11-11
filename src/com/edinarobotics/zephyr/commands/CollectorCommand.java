/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edinarobotics.Zephyr.commands;

import com.edinarobotics.Zephyr.Components;
import com.edinarobotics.Zephyr.subsystems.Collector;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 * @author GreenMachine
 */
public class CollectorCommand extends Command {
    
    private Collector collector;
    private boolean on;
    private boolean rev;

    public CollectorCommand(boolean on, boolean rev) {
        super("Collector");
        collector = Components.getInstance().collector;
        this.on = on;
        this.rev = rev;
        requires(collector);
    }
    
    protected void initialize() {
        collector.setCollectorOn(on, rev);
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
