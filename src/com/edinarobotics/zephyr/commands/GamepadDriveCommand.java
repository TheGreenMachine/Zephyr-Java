/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edinarobotics.Zephyr.commands;

import com.edinarobotics.Zephyr.Components;
import com.edinarobotics.Zephyr.subsystems.Drivetrain;
import edu.wpi.first.wpilibj.command.Command;
import com.edinarobotics.utils.gamepad.Gamepad;
/**
 *
 * @author GreenMachine
 */

public class GamepadDriveCommand extends Command {

    private Gamepad gamepad;
    private Drivetrain drivetrain;
    
    public GamepadDriveCommand(Gamepad gamepad) {
        super("GamepadDrive");
        this.gamepad = gamepad;
        this.drivetrain = Components.getInstance().drivetrain;
        requires(drivetrain);
    }
    
    protected void initialize() {
    }

    protected void execute() {
        double left = gamepad.getLeftY();
        double right = gamepad.getRightX();
        drivetrain.setValues(left, right);
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
    
}
