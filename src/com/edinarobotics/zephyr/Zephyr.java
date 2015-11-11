/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edinarobotics.Zephyr;

import com.edinarobotics.Zephyr.commands.GamepadDriveCommand;
import com.edinarobotics.Zephyr.subsystems.Collector;
import com.edinarobotics.Zephyr.subsystems.Drivetrain;
import com.edinarobotics.Zephyr.subsystems.Shooter;
import com.edinarobotics.utils.gamepad.Gamepad;
import edu.wpi.first.wpilibj.DriverStationLCD;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Scheduler;

/**
 *
 * @author GreenMachine
 */
public class Zephyr extends IterativeRobot {
    
    public Shooter shooter;
    public Drivetrain drivetrain;
    public Collector collector;
    public DriverStationLCD driverStationLCD = DriverStationLCD.getInstance();
    
    public void robotInit() {    
        Components.getInstance();
        Controls.getInstance();
    }

    public void autonomousPeriodic() { }
    
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        outputData();
    }
    
    public void testPeriodic() { }
    
    public void stop() {
        Components.getInstance().drivetrain.setValues(0, 0);
        Components.getInstance().collector.setCollectorOn(false, false);
        Components.getInstance().shooter.setOn(false);
    }
    
    public void teleopInit() {
        Components.getInstance().drivetrain.setDefaultCommand(new GamepadDriveCommand(Controls.getInstance().gamepad));
    }
    
    public void outputData()
    {
        driverStationLCD.clear();
        driverStationLCD.println(DriverStationLCD.Line.kUser1, 1, Double.toString(Components.getInstance().shooter.getSetpoint()));
        driverStationLCD.println(DriverStationLCD.Line.kUser2, 2, Double.toString(Components.getInstance().shooter.getEncRate()));
        driverStationLCD.updateLCD();
    }
    
    public void disabledInit() { }
    
    public void disabledPeriodic() { }
    
}
